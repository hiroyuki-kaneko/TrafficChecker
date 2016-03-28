package com.nekoscape.android.ntc.data.operator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nekoscape.android.ntc.dao.DaoMaster;
import com.nekoscape.android.ntc.dao.DaoSession;
import com.nekoscape.android.ntc.dao.Hour;
import com.nekoscape.android.ntc.dao.HourDao;
import com.nekoscape.android.ntc.dao.NetworkType;
import com.nekoscape.android.ntc.dao.NetworkTypeDao;
import com.nekoscape.android.ntc.common.NetworkStatus;

public class CompareSsid {

	private DaoMaster.DevOpenHelper helper = null;

	public CompareSsid(Context context) {
		this.helper = new DaoMaster.DevOpenHelper(context,
				DataManager.DATABASE_NAME, null);
	}

	public List<NetworkType> getAllNetworkType() {
		SQLiteDatabase db = helper.getReadableDatabase();
		DaoMaster master = new DaoMaster(db);
		DaoSession session = master.newSession();

		NetworkTypeDao networkTypeDao = session.getNetworkTypeDao();

		return networkTypeDao.loadAll();
	}

	public long getNetworkId(NetworkStatus status) {
		List<NetworkType> types = getAllNetworkType();

		for (NetworkType type : types) {
			// Log.d("CompareSSID",String.format("id=%s, type=%s, subtype=%s, ssid=%s.",
			// type.getId(), type.getType(), type.getSubtype(),
			// type.getSsid()));
			// Log.d("CompareSSID",String.format("id=%s, type=%s, subtype=%s, ssid=%s.",
			// "?", status.getType(), status.getSubtype(), status.getSSID()));
			if (type.getType() != status.getType()) {
				continue;
			}

			if (type.getSubtype() != status.getSubtype()) {
				continue;
			}

			if (!type.getSsid().equals(status.getSSID())) {
				continue;
			}

			return type.getId();
		}
		// Log.d("CompareSSID","Not found : " + status.getSSID());
		return -1;
	}

	public NetworkType getNetworkType(Long id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		DaoMaster master = new DaoMaster(db);
		DaoSession session = master.newSession();

		NetworkTypeDao networkTypeDao = session.getNetworkTypeDao();

		return networkTypeDao.load(id);
	}

	public List<Hour> getDayTraffics(Calendar cal) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.format(Locale.US, "%02d",
				cal.get(Calendar.MONTH) + 1);
		String day = String.format(Locale.US, "%02d",
				cal.get(Calendar.DAY_OF_MONTH));
		Cursor cursor = db.query(HourDao.TABLENAME, new String[] { "id",
				"timestamp", "years", "months", "days", "SUM(osend)",
				"SUM(orecv)", "SUM(msend)", "SUM(mrecv)",
				"SUM(msend + mrecv + orecv + osend) as sum" },
				"years = ? and months = ? and days = ?", new String[] { year,
						month, day }, "id, years, months, days", null,
				"sum desc");
		List<Hour> hours = new ArrayList<Hour>();
		while (cursor.moveToNext()) {
			Hour hour = new Hour();
			hour.setId(cursor.getLong(0));
			hour.setTimestamp(cursor.getString(1));
			hour.setYears(cursor.getString(2));
			hour.setMonths(cursor.getString(3));
			hour.setDays(cursor.getString(4));
			hour.setOsend(cursor.getLong(5));
			hour.setOrecv(cursor.getLong(6));
			hour.setMsend(cursor.getLong(7));
			hour.setMrecv(cursor.getLong(8));
			hours.add(hour);
		}
		return hours;
	}

	public List<Hour> getMonthTraffics(Calendar cal) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.format(Locale.US, "%02d",
				cal.get(Calendar.MONTH) + 1);
		Cursor cursor = db.query(HourDao.TABLENAME, new String[] { "id",
				"timestamp", "years", "months", "days", "SUM(osend)",
				"SUM(orecv)", "SUM(msend)", "SUM(mrecv)",
				"SUM(osend + orecv + msend + mrecv) as sum" },
				"years = ? and months = ?", new String[] { year, month },
				"id, years, months", null, "sum desc");
		List<Hour> hours = new ArrayList<Hour>();
		while (cursor.moveToNext()) {
			Hour hour = new Hour();
			hour.setId(cursor.getLong(0));
			hour.setTimestamp(cursor.getString(1));
			hour.setYears(cursor.getString(2));
			hour.setMonths(cursor.getString(3));
			hour.setDays(cursor.getString(4));
			hour.setOsend(cursor.getLong(5));
			hour.setOrecv(cursor.getLong(6));
			hour.setMsend(cursor.getLong(7));
			hour.setMrecv(cursor.getLong(8));
			hours.add(hour);
		}
		return hours;
	}
}
