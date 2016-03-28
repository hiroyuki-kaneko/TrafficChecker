package com.nekoscape.android.ntc.data.operator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.nekoscape.android.ntc.data.object.TransferData;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
//import android.util.Log;

public class TrafficDataManager extends SQLiteOpenHelper {

	private static final String WHEN_CONDITIONS = "( select * from dailytable where date(substr(updatetime,1,10), 'unixepoch', 'localtime') = date(substr(new.updatetime,1,10), 'unixepoch', 'localtime') and type = new.type and subtype = new.subtype and ssid = new.ssid ) ";
	volatile private static TrafficDataManager instance = null;
	volatile private static boolean first_install_flag = true;

	public TrafficDataManager(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public static synchronized TrafficDataManager getInstance(Context context,
			String name, CursorFactory factory, int version) {
		if (instance == null) {
			instance = new TrafficDataManager(context, name, factory, version);
		}
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {	
//		Log.d("","はじめてのデータベース");
		createDailyTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		Log.d(this.getClass().getName(),String.format("%s,%s",oldVersion,newVersion));
		switch (oldVersion) {
		case 1:
		case 2:
		case 3:
			// 移行先テーブルの作成
			first_install_flag = false;
			createDailyTable(db);
			break;

		}
	}
	
	public void upgradeTo3(){
		if(first_install_flag){
			// 初めてのインストール時には、この処理は必要ない。
			return;
		}
		SQLiteDatabase db = getWritableDatabase();
		// データの移行
		copyOld2NewTable(db);
		
		// 移行元のテーブル削除
		dropTrafficdata(db);

	}
	
	private void copyOld2NewTable(SQLiteDatabase db){
		db.execSQL("insert into rawtable select * from trafficdata");
	}
	
	private void dropTrafficdata(SQLiteDatabase db){
		db.execSQL("drop table trafficdata");
	}
	
	private void createDailyTable(SQLiteDatabase db){
		// 生データ
		db.execSQL("create table rawtable ( updatetime text, date text, type text , subtype text , ssid text, sendbytes long, recvbytes long, primary key(updatetime, type, subtype, ssid))");
		db.execSQL("create index idx_rawtable_updaetime on rawtable (updatetime) ");
		// 日毎に格納するテーブル
		db.execSQL("create table dailytable ( updatetime text, date text, type text , subtype text , ssid text, sendbytes long, recvbytes long, primary key(updatetime, type, subtype, ssid))");
		db.execSQL("create index idx_daily_updaetime on dailytable (updatetime) ");
		// dailytableへのデータ格納用
		db.execSQL("create trigger InsertDailyTable after insert on rawtable "
				+ "when not exists " + WHEN_CONDITIONS
				+ "BEGIN " //
				+ "insert into dailytable values (new.updatetime, new.date, new.type, new.subtype, new.ssid, new.sendbytes, new.recvbytes) ;" //
				+ "END ;");
		db.execSQL("create trigger UpdateDailyTable after insert on rawtable "
				+ "when exists  " + WHEN_CONDITIONS
				+ "BEGIN " //
				+ "update dailytable set sendbytes = new.sendbytes + sendbytes, recvbytes = new.recvbytes + recvbytes where date(substr(updatetime,1,10), 'unixepoch', 'localtime') = date(substr(new.updatetime,1,10), 'unixepoch', 'localtime') and type = new.type and subtype = new.subtype and ssid = new.ssid ;" //
				+ "END ;");
		
		// dailytableのデータ削除用
		db.execSQL("create trigger dailytable_delete after insert on dailytable "
				+ "BEGIN "
				+ "delete from dailytable where updatetime < strftime('%s000','now', '-1 months'); "
				+ "END ");
		db.execSQL("create trigger rawtable_delete after insert on dailytable "
				+ "BEGIN "
				+ "delete from rawtable where updatetime < strftime('%s000','now', '-1 hours'); "
				+ "END ");
	
	}
	
	/**
	 * 指定した値を保存する。
	 * 
	 * @param userData
	 *            データ記録日（時間は記録されません。）
	 */
	public void registUserData(TransferData userData) {
		if(userData.getSSID() == null){
			return;
		}
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		try {
			// テーブル名を変更
			SQLiteStatement statement = db
					.compileStatement("insert or ignore into rawtable (updatetime, date, type, subtype, ssid, sendbytes, recvbytes ) values(?,?,?,?,?,?,?);");

			int idxDate = 1;
			int idxType = 3;
			int idxSubtype = 4;
			int idxSsid = 5;
			int idxDatasize = 6;
			int idxRecvsize = 7;

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(userData.getDate());

			statement.bindLong(idxDate, userData.getDate().getTime());
			statement.bindLong(idxType, userData.getType());
			statement.bindLong(idxSubtype, userData.getSubtype());
			statement.bindString(idxSsid, userData.getSSID());
			statement.bindLong(idxDatasize, userData.getSendSize());
			statement.bindLong(idxRecvsize, userData.getRevSize());

			statement.execute();
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	public TransferData searchThisMonthUsreData(TransferData userData) {
		SQLiteDatabase db = getReadableDatabase();
		TransferData result = null;
		try {
			result = (TransferData) userData.clone();
		} catch (CloneNotSupportedException e) {
			// 未サポートということはありえない。
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(parseDatetimeToDate(userData.getDate())));
//		Log.d("calender", calendar.getTime().toString());

		// 今月の開始日　XX年XX月XX日　00:00:00
		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		Log.d("calender", calendar.getTime().toString());
		long today = parseDatetimeToDate(calendar.getTime());

		// 次の月の開始日 　XX年XX月XX日　00:00:00
		calendar.add(Calendar.MONTH, 1);
//		Log.d("calender", calendar.getTime().toString());
		long tommorow = parseDatetimeToDate(calendar.getTime());

		Cursor cursor = db
				.query("dailytable",
						new String[] { "SUM(sendbytes), SUM(recvbytes)" },
						"updatetime >= ? and updatetime < ? and type = ? and subtype = ? and ssid = ?",
						new String[] { Long.toString(today),
								Long.toString(tommorow),
								Long.toString(userData.getType()),
								Long.toString(userData.getSubtype()),
								userData.getSSID() }, "type, subtype, ssid",
						null, null);

		try {
			if (!cursor.moveToFirst()) {
//				Log.d("database", "NO DATA ?");
				result.setSendSize(0L);
				;
				result.setRevSize(0L);
			} else {
				// 1件しかないはずなので。
				result.setSendSize(cursor.getLong(0));
				result.setRevSize(cursor.getLong(1));
			}
		} finally {
			cursor.close();
		}

		return result;

	}

	public TransferData searchUserData(TransferData userData) {
		SQLiteDatabase db = getWritableDatabase();
		TransferData result = null;
		try {
			result = (TransferData) userData.clone();
		} catch (CloneNotSupportedException e) {
			// 未サポートということはありえない。
			return null;
		}

		long today = parseDatetimeToDate(userData.getDate());
		long tommorow = today + 24 * 3600 * 1000;

		Cursor cursor = db
				.query("dailytable",
						new String[] { "SUM(sendbytes), SUM(recvbytes)" },
						"updatetime >= ? and updatetime < ? and type = ? and subtype = ? and ssid = ?",
						new String[] { Long.toString(today),
								Long.toString(tommorow),
								Long.toString(userData.getType()),
								Long.toString(userData.getSubtype()),
								userData.getSSID() }, "type, subtype, ssid",
						null, null);
//		Log.d("saerch condition", String.format(
//				"updatetime >= %s and updatetime < %s", new Date(today),
//				new Date(tommorow)));
		try {
			if (!cursor.moveToFirst()) {
//				Log.d("database", "NO DATA");
			} else {
				// 1件しかないはずなので。
				result.setSendSize(cursor.getLong(0));
				result.setRevSize(cursor.getLong(1));
			}
		} finally {
			cursor.close();
		}

		return result;
	}

	/**
	 * 返却するのはミリ秒
	 * @param date
	 * @return
	 */
	private long parseDatetimeToDate(Date date) {
		return date.getTime()
				- ((date.getTime() + TimeZone.getDefault().getRawOffset()) % (3600 * 24 * 1000));
//		- ((date.getTime()) % (3600 * 24 * 1000));
	}

	public List<TransferData> dump(String table) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query(table, new String[] { "updatetime", "type",
				"subtype", "ssid", "sendbytes", "recvbytes" }, null, null,
				null, null, null);

		List<TransferData> datas = new ArrayList<TransferData>();
		try {
			if (!cursor.moveToFirst()) {
//				Log.d("database", "NODATA");
				return datas;
			}
			do {
				TransferData data = new TransferData(new Date(cursor.getLong(0)),
						cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
				data.setSendSize(cursor.getLong(4));
				data.setRevSize(cursor.getLong(5));
//				Log.d("database", String.format("%s,%s,%s,%s,%s", data
//						.getDate().getTime(), data.getType(),
//						data.getSubtype(), data.getSSID(), data.getDataSize()));
				datas.add(data);
			} while (cursor.moveToNext());

		} finally {
			cursor.close();
		}

		return datas;

	}
}
