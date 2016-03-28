package com.nekoscape.android.ntc.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetworkStatus {
	private Context context;
	private int type = 0;
	private int subtype = 0;
	private String ssid = "None Network";
	private boolean isConnected = false;
	private boolean isWifiConnected = false;

	/**
	 * 接続中のネットワークの種別を取得するコンストラクタ
	 * 
	 * @param context
	 */
	public NetworkStatus(Context context) {
		this.context = context;
		// 通信が可能な状態
		isConnected = checkStatus();
	}

	/**
	 * ネットワークのタイプを指定した時に呼び出すコンストラクタ
	 * 
	 * @param type
	 * @param subtype
	 * @param ssid
	 */
	public NetworkStatus(int type, int subtype, String ssid) {
		this.type = type;
		this.subtype = subtype;
		this.ssid = ssid;

		if (ssid == null) {
			return;
		}
		isConnected = true;
		if (type == ConnectivityManager.TYPE_WIFI) {
			this.isWifiConnected = true;
		}
	}

	private boolean checkStatus() {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) this.context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo network = connectivity.getActiveNetworkInfo();

			if (null == network) {
				return false;
			}
			if (!network.isConnectedOrConnecting()) {
				return false;
			}

			type = network.getType();
			subtype = network.getSubtype();

			WifiManager manager = (WifiManager) this.context
					.getSystemService(Context.WIFI_SERVICE);
			if (!manager.isWifiEnabled()) {
				// WifiがオフならSSIDはタイプネーム
				ssid = network.getTypeName() + ":" + network.getSubtypeName();
			} else {
				ssid = manager.getConnectionInfo().getSSID();
				isWifiConnected = true;
			}
			Log.d(this.getClass().getName(), String.format("%s : %s : %s",
					network.getTypeName(), network.getSubtypeName(), ssid));

			return true;
		} finally {
			finished();
		}
	}

	private void finished() {
		this.context = null;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public boolean isWifiConnected() {
		return isWifiConnected;
	}

	public int getType() {
		return this.type;
	}

	public int getSubtype() {
		return this.subtype;
	}

	public String getSSID() {
		return this.ssid;
	}

	@Override
	public String toString() {
		String res = null;
		switch (type) {
		case ConnectivityManager.TYPE_WIFI:
			res = "Wi-Fi:" + ssid;
			break;
		case ConnectivityManager.TYPE_MOBILE:
			res = ssid;
			break;
		default:
			res = ssid;
			break;
		}
		return res;
	}
}
