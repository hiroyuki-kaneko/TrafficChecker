package com.nekoscape.android.ntc.data.operator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.dao.DaoMaster;
import com.nekoscape.android.ntc.dao.HourDao;
import com.nekoscape.android.ntc.dao.Hour;

import java.util.Calendar;
import java.util.Locale;

public class HistoryHourData {

    private final DaoMaster.DevOpenHelper helper;
    private CompareSsid compareSsid;

    public HistoryHourData(Context context) {
        this.helper = new DaoMaster.DevOpenHelper(context,
                DataManager.DATABASE_NAME, null);
        this.compareSsid = new CompareSsid(context);
    }

    public Hour monthData(NetworkStatus status) {
        Hour hour = new Hour();
        hour.setOrecv(0L);
        hour.setOsend(0L);
        hour.setMrecv(0L);
        hour.setMsend(0L);

        SQLiteDatabase db = helper.getReadableDatabase();

        long id = compareSsid.getNetworkId(status);
        ;
        if (id == -1) {
            return hour;

        }

        Calendar cal = Calendar.getInstance();
        String year = String.format(Locale.US, "%d", cal.get(Calendar.YEAR));
        String month = String.format(Locale.US, "%02d", cal.get(Calendar.MONTH) + 1);

//		Log.d("load data month",String.format("id=%s, year=%s, month=%s",id, year,month));
        Cursor cursor = db.query(HourDao.TABLENAME, new String[]{"id",
                        "timestamp", "years", "months", "days", "SUM(osend)",
                        "SUM(orecv)", "SUM(msend)", "SUM(mrecv)"},
                "id = ? and years = ? and months = ?",
                new String[]{Long.toString(id), year, month}, "years, months", null,
                null);
        while (cursor.moveToNext()) {
            hour.setId(cursor.getLong(0));
            hour.setTimestamp(cursor.getString(1));
            hour.setYears(cursor.getString(2));
            hour.setMonths(cursor.getString(3));
            hour.setDays(cursor.getString(4));
            hour.setOsend(cursor.getLong(5));
            hour.setOrecv(cursor.getLong(6));
            hour.setMsend(cursor.getLong(7));
            hour.setMrecv(cursor.getLong(8));
        }
//		Log.d("load data month", hour.toString());

        return hour;
    }

    public Hour betweenDate(NetworkStatus status, int start, int end) {
        Hour hour = new Hour();
        hour.setOrecv(0L);
        hour.setOsend(0L);
        hour.setMrecv(0L);
        hour.setMsend(0L);

        SQLiteDatabase db = helper.getReadableDatabase();

        long id = compareSsid.getNetworkId(status);
        ;
        if (id == -1) {
            return hour;

        }

        Calendar scal = Calendar.getInstance();
        scal.add(Calendar.DAY_OF_MONTH, start);
        scal.clear(Calendar.AM_PM);
        scal.clear(Calendar.HOUR);
        scal.clear(Calendar.HOUR_OF_DAY);
        scal.clear(Calendar.MINUTE);
        scal.clear(Calendar.SECOND);
        Calendar ecal = Calendar.getInstance();
        ecal.add(Calendar.DAY_OF_MONTH, end);
        ecal.clear(Calendar.AM_PM);
        ecal.clear(Calendar.HOUR);
        ecal.clear(Calendar.HOUR_OF_DAY);
        ecal.clear(Calendar.MINUTE);
        ecal.clear(Calendar.SECOND);

        long offset = 3600 * 9L;
        String sday = (scal.getTime().getTime() / 1000 + offset) + "";
        String eday = (ecal.getTime().getTime() / 1000 + offset) + "";

//		Log.d("HistoryHourData", "id = " + id + " SDAY="
//				+ scal.getTime().toString() + " EDAY="
//				+ ecal.getTime().toString());
        // Log.d("HistoryHourData","id = " + id +" SDAY="+
        // (scal.getTime().getTime()/1000) + " EDAY="+
        // ecal.getTime().getTime()/1000);

        Cursor cursor = db.query(HourDao.TABLENAME, new String[]{"id",
                        "timestamp", "years", "months", "days", "SUM(osend)",
                        "SUM(orecv)", "SUM(msend)", "SUM(mrecv)",
                        "SUM(msend + mrecv + orecv + osend) as sum"},
                "id = ? and timestamp between ? and ?",
                new String[]{Long.toString(id), eday, sday}, "id", null,
                "sum desc");
        while (cursor.moveToNext()) {
            hour.setId(cursor.getLong(0));
            hour.setTimestamp(cursor.getString(1));
            hour.setYears(cursor.getString(2));
            hour.setMonths(cursor.getString(3));
            hour.setDays(cursor.getString(4));
            hour.setOsend(cursor.getLong(5));
            hour.setOrecv(cursor.getLong(6));
            hour.setMsend(cursor.getLong(7));
            hour.setMrecv(cursor.getLong(8));
        }
        Log.d("load data", hour.toString());

        return hour;
    }
}
