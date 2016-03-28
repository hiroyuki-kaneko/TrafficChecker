package com.nekoscape.android.ntc.data.operator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.common.DataType;
import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.common.Util;
import com.nekoscape.android.ntc.data.object.SearchDatas;
import com.nekoscape.android.ntc.data.object.TransferData;

public class UserDataManager {

	/* DBのバージョン番号 */
	private static final int VERSION = 3;

	@Deprecated
	private TrafficDataManager database;

	private DataManager dataManager;
	private String currentNetwork;

	// private final TrafficDataManager disk;

	public UserDataManager(Context context) {
		this.database = new TrafficDataManager(context, "trafficmanager2",
				null, VERSION);

		this.dataManager = DataManager.getInstance(context);

		currentNetwork = context.getString(R.string.current_network);
	}

	public void regsitData(TransferData data) {
		int id = this.dataManager.getNetworkID(data.getType(),
				data.getSubtype(), data.getSSID());
		this.dataManager.registCommunicationTable(id, data.getMsendSize(),
				data.getMrecvSize(), data.getOsendSize(), data.getOrecvSize());
		this.database.registUserData(data);

	}

	public void deleteOldData() {
		Log.d("UserDataManager","deleteOldData");
		this.dataManager.expiration(DataManager.TALBE_HOUR, 366*2);
		this.dataManager.expiration(DataManager.TABLE_COMMUNICATION, 1);
		this.dataManager.expiration(DataManager.TABLE_MINUTES, 1);
	}

	@Deprecated
	public TransferData search(TransferData userData) {
		TransferData result = this.database.searchUserData(userData);

		Log.d("search Data", result.toString());

		return result;
	}

	public GraphDataIterator getData() {
		Cursor cursor = this.dataManager.searchSample();
		return new GraphDataIterator(cursor);
	}

	public SearchDatas getSearchData(DataType type, Calendar calendar) {
		Cursor cursor = this.dataManager.search(type, calendar);
		return new SearchDatas(cursor);
	}

	public SearchDatas getSearchData(DataType day, Calendar calendar, int type,
			int subtype, String ssid) {
		Cursor cursor = this.dataManager.search(day, calendar, type, subtype,
				ssid);
		return new SearchDatas(cursor);
	}

	public void upgradeTrafficdata() {
		this.database.upgradeTo3();
	}

	public NetworkStatus[] getSSIDList() {
		Cursor cursor = this.dataManager.searchSsid();
		List<NetworkStatus> list = new ArrayList<NetworkStatus>();
//		Log.d("spinner dropdown ", " getSSIDList");
		list.add(new NetworkStatus(-1,-1,currentNetwork));
		if (!cursor.moveToFirst()) {
			return list.toArray(new NetworkStatus[1]);
		}
		do {
			int idxType = cursor.getColumnIndex("type");
			int idxSsid = cursor.getColumnIndex("ssid");
			int idxSubtype = cursor.getColumnIndex("subtype");

			int type = cursor.getInt(idxType);
			int subtype = cursor.getInt(idxSubtype);
			String ssid = cursor.getString(idxSsid);
			

			if (ssid == null || ssid.length() == 0) {
				continue;
			}
			
			if(Util.ignoreSsid(type, ssid)){
				continue;
			}

			list.add(new NetworkStatus(type,subtype,ssid));
		} while (cursor.moveToNext());

		return list.toArray(new NetworkStatus[1]);
	}
}
