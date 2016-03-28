package com.nekoscape.android.ntc.data.operator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.nekoscape.android.ntc.common.DataType;

/**
 * グラフ作成を視野に入れたデータ格納クラス。 以下のテーブルを用意する。< br/>
 * ・通信タイプ管理用テーブル（NetworkTypeTable) <br/>
 * ・生データ管理用テーブル(CommunicationTable) １〜60秒間隔のデータをとりあえず入れる場所 <br/>
 * ・圧縮用テーブル <br/>
 * ー　1時間用（HoursTable)　1年保持（データ保持期間は選択次第）
 * 
 * @author someone
 * 
 */
public class DataManager extends SQLiteOpenHelper {
	public static final String TABLE_MINUTES = "MinutesTable";

	public static final String TALBE_HOUR = "HourTable";

	public static final String TABLE_NETWORK_TYPE = "NetworkTypeTable";

	public static final String TABLE_COMMUNICATION = "CommunicationTable";

	private static final String TRIGGER_UPDATE_MINUTESTABLE = "update MinutesTable set msend = msend + new.msend, mrecv = mrecv + new.mrecv, osend = osend + new.osend, orecv = orecv + new.orecv where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours and minutes = new.minutes; ";

	private static final String TRIGGER_INSERT_MINUTESTABLE = "insert into MinutesTable (id, years, months, days, hours, minutes, msend, mrecv, osend, orecv) values (new.id, new.years, new.months, new.days, new.hours, new.minutes, new.msend, new.mrecv, new.osend, new.orecv); ";

	private static final String TRIGGER_UPDATE_HOURTABLE = "update HourTable set msend = msend + new.msend, mrecv = mrecv + new.mrecv, osend = osend + new.osend, orecv = orecv + new.orecv where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours; ";

	private static final String TRIGGER_INSERT_HOURTABLE = "insert into HourTable (id, years, months, days, hours, msend, mrecv, osend, orecv) values (new.id, new.years, new.months, new.days, new.hours, new.msend, new.mrecv, new.osend, new.orecv); ";

	private static final String COLUMN_ORECV_LONG = "orecv long" // その他の通信：受信（Byte)
	;

	private static final String COLUMN_OSEND_LONG = "osend long," // その他の通信：送信（Byte)
	;

	private static final String COLUMN_MRECV_LONG = "mrecv long," // モバイルデータ通信：受信（Byte)
	;

	private static final String COLUMN_MSEND_LONG = "msend long," // モバイルデータ通信：送信（Byte)
	;

	private static final String COLUMN_YEARS_TEXT = "years text default (strftime('%Y','now','localtime'))," // データ登録：年
	;

	private static final String COLUMN_TIMESTAMP_TEXT = "timestamp text default (strftime('%s','now','localtime'))," // 更新日時（秒）
	;

	private static final String COLUMN_ID_INTEGER = "id integer," // 　NetworkTableの主キーを登録する
	;

	volatile private static DataManager INSTANCE = null;

	public static final String DATABASE_NAME = "datamanager";
	private static final int VERSION = 5;

	private SQLiteStatement insertNetworkStatement = null;
	private SQLiteStatement insertCommunicationStatement = null;
	private SQLiteDatabase database = getWritableDatabase();

	private DataManager(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	 * データ管理用インスタンスを生成します。
	 * 
	 * @param context
	 * @return
	 */
	public static synchronized DataManager getInstance(Context context) {
		if (null == INSTANCE) {
			INSTANCE = new DataManager(context, DATABASE_NAME, null, VERSION);
		}
		return INSTANCE;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createNetworkTable(db);
		createCommunicationTable(db);
		createHourTable(db);
		createMinutesTable(db);
		createTimestampIndex(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 1:
			// FALLTHROUGH
			createMinutesTable(db);
		case 2:
		case 3:
			// FALLTHROUGH
			recreateTrigger(db);
		case 4:
			createTimestampIndex(db);
			break;
		default:
			throw new UnsupportedOperationException(String.format(
					"Unexpected version = %s", oldVersion));
		}
	}

	private void createTimestampIndex(SQLiteDatabase db) {
		db.execSQL("create index idx_com_timestamp on CommunicationTable (timestamp) ");
		db.execSQL("create index idx_min_timestamp on MinutesTable (timestamp) ");
		db.execSQL("create index idx_hou_timestamp on HourTable (timestamp) ");
	}

	private SQLiteStatement getRegistNetworkTypeSQL(SQLiteDatabase db) {
		if (this.insertNetworkStatement == null) {
			this.insertNetworkStatement = db
					.compileStatement("insert into NetworkTypeTable (type, subtype, ssid) values (?, ?, ?)");
		}
		return this.insertNetworkStatement;
	}

	private SQLiteStatement getRegistCommunicationTableSQL(SQLiteDatabase db) {
		if (this.insertCommunicationStatement == null) {
			this.insertCommunicationStatement = db
					.compileStatement("insert into CommunicationTable (id, msend, mrecv, osend, orecv) values (?, ?, ?, ?, ?)");
		}
		return this.insertCommunicationStatement;
	}

	/**
	 * 通信タイプ管理用テーブル
	 * 
	 * @param db
	 */
	private void createNetworkTable(SQLiteDatabase db) {
		db.execSQL("create table NetworkTypeTable (" // テーブル作成
				+ "id integer primary key autoincrement," // 主キー：ネットワークタイプ毎の値
				+ "type integer," // 通信タイプ（Wifiやモバイルデータ通信、ブルートゥースを示す
				+ "subtype integer," // サブタイプ
				+ "ssid text)"); // SSID
		db.execSQL("create index idx_net_ssid on NetworkTypeTable (type, subtype, ssid)");
	}

	/**
	 * ネットワークの主キーを取得する。なければ追加、あれば取る。
	 * 
	 * @param type
	 * @param subtype
	 * @param ssid
	 * @return
	 */
	public int getNetworkID(int type, int subtype, String ssid) {

		Cursor cursor = this.database.query(
				TABLE_NETWORK_TYPE,
				new String[] { "id" },
				"type = ? and subtype = ? and ssid = ?",
				new String[] { Integer.toString(type),
						Integer.toString(subtype), ssid }, null, null, null);

		// 登録済みであれば、値を返却
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			// Log.d("ssid", "" + cursor.getCount());
			return cursor.getInt(0);
		}

		registNetworkType(type, subtype, ssid);

		// 再帰呼び出し　まずい？
		return getNetworkID(type, subtype, ssid);
	}

	public void printNetworkIDs() {

		Cursor cursor = this.database.query(TABLE_NETWORK_TYPE,
				new String[] { "id, type, subtype, ssid" }, null, null, null,
				null, null, null);

		// 登録済みであれば、値を返却
		if (!cursor.moveToFirst()) {
			return;
		}
		do {
			// Log.d("Network id", String.format("nwtworkids = %s,%s,%s,%s",
			// cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
			// cursor.getString(3)));
		} while (cursor.moveToNext());

	}

	private void registNetworkType(int type, int subtype, String ssid) {
		SQLiteStatement statement = getRegistNetworkTypeSQL(this.database);

		this.database.beginTransaction();
		try {
			statement.bindLong(1, type);
			statement.bindLong(2, subtype);
			statement.bindString(3, ssid);

			statement.execute();

			this.database.setTransactionSuccessful();
		} finally {
			this.database.endTransaction();
		}
	}

	/**
	 * 生データ格納用テーブル
	 * 
	 * @param db
	 */
	private void createCommunicationTable(SQLiteDatabase db) {
		db.execSQL("create table CommunicationTable (" // テーブル作成
				+ COLUMN_ID_INTEGER
				+ COLUMN_TIMESTAMP_TEXT
				+ COLUMN_YEARS_TEXT
				+ "months text default (strftime('%m','now','localtime'))," // データ登録：月
				+ "days text default (strftime('%d','now','localtime'))," // データ登録：日
				+ "hours text default (strftime('%H','now','localtime'))," // データ登録：時
				+ "minutes text default (strftime('%M','now','localtime'))," // データ登録：分
				+ "seconds text default (strftime('%S','now','localtime'))," // データ登録：秒
				+ COLUMN_MSEND_LONG + COLUMN_MRECV_LONG
				+ COLUMN_OSEND_LONG
				+ COLUMN_ORECV_LONG + ")");
		db.execSQL("create index idx_com_day on CommunicationTable (years, months, days)");
	}

	public void registCommunicationTable(int id, long msend, long mrecv,
			long osend, long orecv) {
		SQLiteStatement statement = getRegistCommunicationTableSQL(this.database);

		this.database.beginTransaction();
		try {
			statement.bindLong(1, id);
			statement.bindLong(2, msend);
			statement.bindLong(3, mrecv);
			statement.bindLong(4, osend);
			statement.bindLong(5, orecv);

			statement.execute();
			this.database.setTransactionSuccessful();
		} finally {
			this.database.endTransaction();
		}
	}

	public Cursor searchSample() {

		Calendar calendar = Calendar.getInstance();
		String year = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.YEAR));
		String month = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.MONTH) + 1);
		String day = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.DAY_OF_MONTH));

		return this.database.query(TALBE_HOUR, new String[] { "hours",
				"SUM(osend)+SUM(orecv)-SUM(msend)-SUM(mrecv)" },
				"years = ? and months = ? and days = ?", new String[] { year,
						month, day }, "years, months, days, hours", null, null);
	}

	public Cursor search(DataType type, Calendar calendar) {

		// Log.d("searchData", type.toString());

		String year = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.YEAR));
		String month = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.MONTH) + 1);
		String day = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.DAY_OF_MONTH));

		// Log.d("Search Date", String.format("search %s-%s-%s", year, month,
		// day));

		List<String> selections = new ArrayList<String>();

		switch (type) {
		case YEAR:
			selections.add(year);
			break;
		case YEAR_SUM:
			selections.add(year);
			break;
		case MONTH:
			selections.add(year);
			selections.add(month);
			break;
		case MONTH_SUM:
			selections.add(year);
			selections.add(month);
			break;
		case DAY:
			selections.add(year);
			selections.add(month);
			selections.add(day);
			break;
		case DAY_SUM:
			selections.add(year);
			selections.add(month);
			selections.add(day);
			break;
		default:
			throw new IllegalAccessError();
		}

		return this.database.query(TALBE_HOUR, type.getColumns(),
				type.getSelection(), selections.toArray(new String[1]),
				type.getGroupBy(), null, null);
	}

	public Cursor search(DataType datatype, Calendar calendar, int type,
			int subtype, String ssid) {

		printNetworkIDs();

		int id = getNetworkID(type, subtype, ssid);
		// Log.d("network id ", "" + id);
		//
		// Log.d("searchData", datatype.toString());

		String year = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.YEAR));
		String month = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.MONTH) + 1);
		String day = String.format(Locale.US, "%1$02d",
				calendar.get(Calendar.DAY_OF_MONTH));

		List<String> selections = new ArrayList<String>();

		switch (datatype) {
		case YEAR_SSID:
			selections.add(year);
			break;
		case MONTH_SSID:
			selections.add(year);
			selections.add(month);
			break;
		case DAY_SSID:
			selections.add(year);
			selections.add(month);
			selections.add(day);
			break;
		default:
			throw new IllegalAccessError();
		}
		selections.add(Integer.toString(id));

		return this.database.query(TALBE_HOUR, datatype.getColumns(),
				datatype.getSelection(), selections.toArray(new String[1]),
				datatype.getGroupBy(), null, null);
	}

	/**
	 * 1時間毎の圧縮テーブル
	 */
	private void createHourTable(SQLiteDatabase db) {
		db.execSQL("create table HourTable (" // テーブル作成
				+ COLUMN_ID_INTEGER + COLUMN_TIMESTAMP_TEXT
				+ "years text," // データ登録：年
				+ "months text," // データ登録：月
				+ "days text," // データ登録：日
				+ "hours text," // データ登録：時
				+ COLUMN_MSEND_LONG + COLUMN_MRECV_LONG
				+ COLUMN_OSEND_LONG
				+ "orecv long)" // その他の通信：受信（Byte)
		);
		db.execSQL("create index idx_hour_months on HourTable (years, months)");
		db.execSQL("create index idx_hour_days on HourTable (years, days)");

		// テーブル更新用のトリガ
		db.execSQL("create trigger InsertHourTable after insert on CommunicationTable "
				+ "when not exists  ( select * from HourTable where id = new.id and  years = new.years and months = new.months and days = new.days and hours = new.hours ) "
				+ "BEGIN " + TRIGGER_INSERT_HOURTABLE + "END ");
		db.execSQL("create trigger UpdateHourTable after insert on CommunicationTable "
				+ "when exists  ( select * from HourTable where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours ) "
				+ "BEGIN " + TRIGGER_UPDATE_HOURTABLE + "END ");
	}

	private void createMinutesTable(SQLiteDatabase db) {
		db.execSQL("create table MinutesTable(" // テーブル作成
				+ COLUMN_ID_INTEGER + COLUMN_TIMESTAMP_TEXT
				+ "years text," // データ登録：年
				+ "months text," // データ登録：月
				+ "days text," // データ登録：日
				+ "hours text," // データ登録：時
				+ "minutes text," // データ登録：分
				+ COLUMN_MSEND_LONG + COLUMN_MRECV_LONG
				+ COLUMN_OSEND_LONG
				+ "orecv long)" // その他の通信：受信（Byte)
		);
		db.execSQL("create index idx_minutes_days on MinutesTable(years, months, days)");

		// テーブル更新用のトリガ
		db.execSQL("create trigger InsertMinutesTable after insert on CommunicationTable "
				+ "when not exists  ( select * from MinutesTable where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours and minutes = new.minutes ) "
				+ "BEGIN " + TRIGGER_INSERT_MINUTESTABLE + "END ");
		db.execSQL("create trigger UpdateMinutesTable after insert on CommunicationTable "
				+ "when exists  ( select * from MinutesTable where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours and minutes = new.minutes) "
				+ "BEGIN " + TRIGGER_UPDATE_MINUTESTABLE + "END ");
	}

	private void recreateTrigger(SQLiteDatabase db) {
		// 古いTRIGGERのドロップ
		db.execSQL("drop trigger InsertHourTable");
		db.execSQL("drop trigger UpdateHourTable");
		db.execSQL("drop trigger InsertMinutesTable");
		db.execSQL("drop trigger UpdateMinutesTable");

		// テーブル更新用のトリガ
		db.execSQL("create trigger InsertHourTable after insert on CommunicationTable "
				+ "when not exists  ( select * from HourTable where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours ) "
				+ "BEGIN " + TRIGGER_INSERT_HOURTABLE + "END ");
		db.execSQL("create trigger UpdateHourTable after insert on CommunicationTable "
				+ "when exists  ( select * from HourTable where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours ) "
				+ "BEGIN " + TRIGGER_UPDATE_HOURTABLE + "END ");

		// テーブル更新用のトリガ
		db.execSQL("create trigger InsertMinutesTable after insert on CommunicationTable "
				+ "when not exists  ( select * from MinutesTable where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours and minutes = new.minutes ) "
				+ "BEGIN " + TRIGGER_INSERT_MINUTESTABLE + "END ");
		db.execSQL("create trigger UpdateMinutesTable after insert on CommunicationTable "
				+ "when exists  ( select * from MinutesTable where id = new.id and years = new.years and months = new.months and days = new.days and hours = new.hours and minutes = new.minutes) "
				+ "BEGIN " + TRIGGER_UPDATE_MINUTESTABLE + "END ");
	}

	/**
	 * 保存期限の切れたデータの削除
	 * 
	 * @param days
	 */
	public void expiration(String tableName, int days) {

		this.database.beginTransaction();
		try {
			this.database.delete(tableName,
					"timestamp < strftime('%s','now','localtime','-" + days
							+ " days')", null);

			// Log.d("Delete Count", "" + count);

			this.database.setTransactionSuccessful();
		} finally {
			this.database.endTransaction();
		}
	}

	public Cursor searchSsid() {
		return this.database.query(TABLE_NETWORK_TYPE,
				new String[] { "id, type, subtype, ssid" }, null, null, null,
				null, "type, ssid", null);
	}

}
