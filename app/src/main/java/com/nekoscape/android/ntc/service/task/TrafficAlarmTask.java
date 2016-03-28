package com.nekoscape.android.ntc.service.task;

import java.text.NumberFormat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import com.nekoscape.android.ntc.activity.MainActivity;
import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.activity.pref.PreferenceConstant;
import com.nekoscape.android.ntc.common.ByteUnit;
import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.common.Util;

public class TrafficAlarmTask {

	private static volatile Builder builder = null;
	private static volatile Notification notification = null;

	public static void makeAlarm(Context context, NetworkStatus status, long totalTrafficBytes) {

		if (!PreferenceConstant.ALARM_TRAFFIC_SWITCH.getBooleanValue(context,
				false)) {
			// 通知設定OFF
//			Log.d("TrafficAlarmTask","通知設定OFF");
			return;
		}
		
		if(!status.getSSID().equals(PreferenceConstant.ALARM_TARGET_SSID.getStringValue(context, false))){
			//　対象のSSIDではない
//			Log.d("TrafficAlarmTask","異なるSSID："+status.getSSID() + " target:"+PreferenceConstant.ALARM_TARGET_SSID.getStringValue(context, false));
			return;
		}
		
		
		//日付が変わったことをBroadCastReciver（ACTION_DATE_CHANGED）で検出し、当日の通知可否設定をfalse -> trueにする。 
		if(!PreferenceConstant.ALARM_TRAFFIC_TODAY_NOTIFICATION.getBooleanValue(context, true)){
			//今日は通知済み
//			Log.d("TrafficAlarmTask","今日は通知済み");
			return;
		}
		

		int sizeMbytes = Util.getAlarmThreshold(context);
		// しきい値は90%
		double threshold = (double) ((sizeMbytes * 10) - sizeMbytes) / 10;
		if (threshold > ByteUnit.BYTE.toMByte(totalTrafficBytes)) {
			// 何もしない
			return;
		}

		// 通知
		createNotify(context, totalTrafficBytes);

		PreferenceConstant.ALARM_TRAFFIC_TODAY_NOTIFICATION.setPrivateValue(context, false);
	}

	/**
	 * 
	 * @param totalSize
	 */
	public static void createNotify(Context context, long totalSize) {

		Intent notifyIntent = new Intent(context, MainActivity.class);

		PendingIntent contextintent = PendingIntent.getActivity(context, 0,
				notifyIntent, PendingIntent.FLAG_ONE_SHOT);

		if (builder == null) {
			builder = new NotificationCompat.Builder(context);
		}

		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);

		long[] pattern = { 1000, 200, 700, 200, 400, 200 };
		if(!PreferenceConstant.ALARM_TRAFFIC_VIBRATION.getBooleanValue(context, false)){
			pattern = new long[1];
			pattern[0] = 0;
		}
		
		notification = builder // コメント
				.setSmallIcon(R.drawable.ic_traffic_notify)// アイコン
				.setWhen(System.currentTimeMillis())// 設定時刻
				.setTicker(context.getString(R.string.app_name)) //
				.setContentTitle(context.getString(R.string.traffic_threshold)) //
				.setContentText(format.format(ByteUnit.BYTE.toMByte(totalSize)) + " MBytes")//
				.setContentIntent(contextintent) //
				.setVibrate(pattern).build();

		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// マジックナンバー１は、NotificationのID.Layoutで宣言すると綺麗になる。
		manager.notify(2, notification);
	}

}
