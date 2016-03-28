package com.nekoscape.android.ntc.activity.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Preferenceのデータ取得、設定はここでやることが目標。設定はまずはPrivateからやろう
 * 
 * @author someone
 * 
 */
public enum PreferenceConstant {
	/**
	 * Private　Preference
	 */

	/**
	 * サービス起動までの待機時間
	 */
	SERVICE_WAIT_TIME("waiting_time") {
		@Override
		public String getDefaultString() {
			return "30";
		}
	},
	/**
	 * CONECTIVItY_CHNGED受信前のネットワークタイプ
	 */
	NETWORK_TYPE("network.type") {
		@Override
		public int getDefaultInt() {
			return -1;
		}
	},
	/**
	 * CONECTIVItY_CHNGED受信前のネットワークサブタイプ
	 */
	NETWORK_SUBTYPE("network.subtype") {
		@Override
		public int getDefaultInt() {
			return -1;
		}

	},
	/**
	 * CONECTIVItY_CHNGED受信前のSSID
	 */
	NETWORK_SSID("network.ssid") {
		@Override
		public String getDefaultString() {
			return null;
		}
	},
	/**
	 * ネットワークタイプの書き込みの初回登録の実施有無
	 */
	NETWORK_INIT("network.init")

	/**
	 * User Preference
	 */
	, ALRAM_TRAFFIC_SIZE("traffic_alarm_size") {
		@Override
		public String getDefaultString() {
			// Integer.Max
			return "2147483647";
		}
	}
	/**
	 * 通知のON/OFF
	 */
	,
	ALARM_TRAFFIC_SWITCH("traffic_alarm_switch") {
		@Override
		public boolean getDefaultBoolean() {
			return false;
		}
	}
	/**
	 * 通知時に振動するかどうか
	 */
	,
	ALARM_TRAFFIC_VIBRATION("traffic_vibration"){
		@Override
		public boolean getDefaultBoolean(){
			return true;
		}
	}
	/**
	 * 上限の通知を当日行えるかどうか
	 */
	,
	ALARM_TRAFFIC_TODAY_NOTIFICATION("alarm_traffic_today_notice") {
		@Override
		public boolean getDefaultBoolean() {
			return true;
		}
	}
	/**
	 * アラーム対象のSSID
	 */
	,ALARM_TARGET_SSID("alarm_ssid_type"){
		@Override
		public String getDefaultString(){
			return "";
		}
	}
	;

	public static final String PRIVATE_PREF_NAME = "system_pref";
	private final String key;

	private PreferenceConstant(String key) {
		this.key = key;
	}

	public int getDefaultInt() {
		return -1;
	}

	public long getDefaultLong() {
		return -1L;
	}

	public String getDefaultString() {
		return null;
	}

	public String getName() {
		return key;
	}

	public boolean getDefaultBoolean() {
		return false;
	}

	public boolean getBooleanValue(Context context, boolean privatef) {
		SharedPreferences preferences = getPreferenceManager(context, privatef);
		return preferences.getBoolean(getName(), getDefaultBoolean());
	}
	
	public String getStringValue(Context context, boolean privatef){
		SharedPreferences preferences = getPreferenceManager(context, privatef);
		return preferences.getString(getName(), getDefaultString());
		
	}

	private SharedPreferences getPreferenceManager(Context context,
			boolean privatef) {
		if (privatef) {
			return context.getSharedPreferences(
					PreferenceConstant.PRIVATE_PREF_NAME, Context.MODE_PRIVATE);
		} else {
			return PreferenceManager.getDefaultSharedPreferences(context);
		}

	}

	public long getLongBalue(Context context, boolean privatef) {
		SharedPreferences preferences = getPreferenceManager(context, privatef);
		return preferences.getLong(getName(), getDefaultLong());

	}

	public void setPrivateValue(Context context, boolean flag) {
		SharedPreferences preferences = context.getSharedPreferences(
				PreferenceConstant.PRIVATE_PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean(getName(), flag);
		editor.commit();
	}

}
