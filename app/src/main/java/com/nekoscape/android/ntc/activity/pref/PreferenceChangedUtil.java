package com.nekoscape.android.ntc.activity.pref;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.nekoscape.android.ntc.common.Util;
import com.nekoscape.android.ntc.service.TrafficCheckService;

public class PreferenceChangedUtil {

	private PreferenceChangedUtil() {
		// 実装なし
	}

	public static void changePreference(Context context,
			SharedPreferences sharedPreferences, String key) {
		if ("example_checkbox".equals(key)) {
			boolean val = sharedPreferences.getBoolean(key, true);
			Log.d("change pref", Boolean.toString(val));

			NotificationManager manager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			if (!val) {
				manager.cancel(1);
			}

		} else if (PreferenceConstant.SERVICE_WAIT_TIME.getName().equals(key)) {
			int waitingTime = Integer.parseInt(sharedPreferences.getString(key,
					PreferenceConstant.SERVICE_WAIT_TIME.getDefaultString()));
			Log.d("wait time", "" + waitingTime);

			AlarmManager manager = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);
			PendingIntent alarmSender = PendingIntent.getService(context, 0,
					new Intent(context, TrafficCheckService.class), 0);
			manager.cancel(alarmSender);

			manager.set(AlarmManager.RTC,
					System.currentTimeMillis() + Util.waitingTime(waitingTime),
					alarmSender);
		} else if ("widget_background_color".equals(key)) {
			// Do nothing
		} else if ("widget_text_color".equals(key)) {
			// Do nothing
		} else if("widget_alpha".endsWith(key)){
			// Do nothing
		} else if(PreferenceConstant.ALARM_TARGET_SSID.getName().equals(key)){
			//SSIDを変えたら通知はリセット
			PreferenceConstant.ALARM_TRAFFIC_TODAY_NOTIFICATION.setPrivateValue(context, true);
		} else if(PreferenceConstant.ALRAM_TRAFFIC_SIZE.getName().equals(key)){
			//通知のサイズを変えたら通知はリセット
			PreferenceConstant.ALARM_TRAFFIC_TODAY_NOTIFICATION.setPrivateValue(context, true);
			
		}
		

	}
	
}
