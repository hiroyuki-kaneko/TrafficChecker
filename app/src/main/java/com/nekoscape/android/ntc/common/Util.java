package com.nekoscape.android.ntc.common;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.nekoscape.android.ntc.dao.NetworkType;
import com.nekoscape.android.ntc.dao.NetworkTypeDao;
import com.nekoscape.android.ntc.dao.NetworkTypeDao.Properties;
import com.nekoscape.android.ntc.activity.pref.PreferenceConstant;
import com.nekoscape.android.ntc.data.operator.CompareSsid;

public class Util {

	private static String[] ignoreSsids = { "None Network", "No Wi-Fi", "" };

	public static long waitingTime(int waitTime) {
		// 10秒の起点を00:00:00にする
		long delay = new Date().getTime()
				% (TimeUnit.SECONDS.toMillis(waitTime));
		return TimeUnit.SECONDS.toMillis(waitTime) - delay;

	}

	public static List<String> getNetworkSsidList(Context context) {
		CompareSsid compare = new CompareSsid(context);
		List<NetworkType> list = compare.getAllNetworkType();
		Set<String> ssids = new HashSet<String>();
		for (NetworkType type : list) {
			if (ignoreSsid(type.getType(), type.getSsid())) {
				continue;
			}
			ssids.add(type.getSsid());
		}

		return Arrays.asList(ssids.toArray(new String[1]));
	}

	public static boolean ignoreSsid(int type, String ssid) {
		List<String> ignores = Arrays.asList(ignoreSsids);
		if (ignores.contains(ssid)) {
			return true;
		}

		if (type == ConnectivityManager.TYPE_MOBILE
				&& !ssid.startsWith("mobile:")) {
			// Log.d("ignoreSsid",String.format("type=%s, ssid=%s",type, ssid));
			return true;
		}
		return false;
	}

	public static boolean canSendAnalystics(Context context) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return preferences.getBoolean("google_analystic", true);
	}

	public static int getAlarmThreshold(Context context) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return Integer.parseInt(preferences.getString(
				PreferenceConstant.ALRAM_TRAFFIC_SIZE.getName(),
				PreferenceConstant.ALRAM_TRAFFIC_SIZE.getDefaultString()));
	}

	public static NetworkType createNetworkType(NetworkStatus status) {
		NetworkType networkType = new NetworkType();
		networkType.setType(status.getType());
		networkType.setSubtype(status.getSubtype());
		networkType.setSsid(status.getSSID());
		return networkType;
	}

	public static NetworkType serchNetworkType(NetworkTypeDao networkTypeDao,
			NetworkStatus status) {
		NetworkType type = networkTypeDao
				.queryBuilder()
				.where(Properties.Type.eq(status.getType()),
						Properties.Subtype.eq(status.getSubtype()),
						Properties.Ssid.eq(status.getSSID())).unique();
		return type;
	}

	public static NetworkStatus getNetworkStatus(Context context) {
		synchronized (ignoreSsids) {

			SharedPreferences preMane = context.getSharedPreferences(
					PreferenceConstant.PRIVATE_PREF_NAME, Context.MODE_PRIVATE);
			int type = preMane.getInt(
					PreferenceConstant.NETWORK_TYPE.getName(),
					PreferenceConstant.NETWORK_TYPE.getDefaultInt());
			int subtype = preMane.getInt(
					PreferenceConstant.NETWORK_SUBTYPE.getName(),
					PreferenceConstant.NETWORK_SUBTYPE.getDefaultInt());
			String ssid = preMane.getString(
					PreferenceConstant.NETWORK_SSID.getName(),
					PreferenceConstant.NETWORK_SSID.getDefaultString());

			if (ssid == null
					|| type == PreferenceConstant.NETWORK_TYPE.getDefaultInt()
					|| subtype == PreferenceConstant.NETWORK_SUBTYPE
							.getDefaultInt()) {
				return null;
			} else {
				Log.d("NetworkStatus creater", "type = " + type + " subtype = "
						+ subtype + " ssid = " + ssid);
				return new NetworkStatus(type, subtype, ssid);
			}
		}
	}

	public static void changeNetworkPreference(Context context) {
		synchronized (ignoreSsids) {
			NetworkStatus newStatus = new NetworkStatus(context);
			SharedPreferences preferences = context.getSharedPreferences(
					PreferenceConstant.PRIVATE_PREF_NAME, Context.MODE_PRIVATE);
			Editor editor = preferences.edit();
			if (newStatus.isConnected()) {
				editor.putInt(PreferenceConstant.NETWORK_TYPE.getName(),
						newStatus.getType());
				editor.putInt(PreferenceConstant.NETWORK_SUBTYPE.getName(),
						newStatus.getSubtype());
				editor.putString(PreferenceConstant.NETWORK_SSID.getName(),
						newStatus.getSSID());
			} else {
				editor.putInt(PreferenceConstant.NETWORK_TYPE.getName(), -1);
				editor.putInt(PreferenceConstant.NETWORK_SUBTYPE.getName(), -1);
				editor.putString(PreferenceConstant.NETWORK_SSID.getName(),
						null);
			}

			// TODO 書き込みは同時にできないため、一箇所で管理する必要がある。
			editor.commit();
		}
	}

}
