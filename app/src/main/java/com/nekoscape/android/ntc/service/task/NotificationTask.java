package com.nekoscape.android.ntc.service.task;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

import com.nekoscape.android.ntc.activity.MainActivity;
import com.nekoscape.android.ntc.activity.R;

public class NotificationTask {
	private static volatile Notification notification = null;
	private static boolean notificationFlag = true;
	private static volatile Builder builder = null;

	public static boolean isNotificationFlag() {
		return notificationFlag;
	}

	public static void setNotificationFlag(boolean notificationFlag) {
		NotificationTask.notificationFlag = notificationFlag;
	}

	/**
	 * 
	 * @param ssid
	 * @param totalSize
	 */
	public static void createNotify(Context context, String ssid,
			String totalSize) {
		if(context == null){
			// たまに動かなくなる処理があるという。どこかがnullポインタなどの異常となっている。
			return ;
		}
		
		
		// 画面がOFFの場合は更新しない。
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		if (!pm.isScreenOn()) {
			return;
		}

		SharedPreferences preMane = (SharedPreferences) PreferenceManager
				.getDefaultSharedPreferences(context);
		if(preMane == null){
			// nullチェック
			return;
		}
		
		
		boolean flag = preMane.getBoolean("example_checkbox", true);
		Log.d("preference flag", Boolean.toString(flag));

		setNotificationFlag(flag);

		if (!flag) {
			return;
		}

		Intent notifyIntent = new Intent(context, MainActivity.class);

		PendingIntent contextintent = PendingIntent.getActivity(context, 0,
				notifyIntent, PendingIntent.FLAG_ONE_SHOT);

		if (builder == null) {
			builder = new NotificationCompat.Builder(context);
		}

		notification = builder // コメント
				.setSmallIcon(R.drawable.ic_launcher)// アイコン
				.setWhen(System.currentTimeMillis())// 設定時刻
				.setTicker(context.getString(R.string.app_name)) //
				.setContentTitle(ssid) //
				.setContentText(totalSize + " MBytes")//
				.setContentIntent(contextintent) //
				.setAutoCancel(false)// キャンセル
				.setOngoing(true)// オンゴーイング
				.build();

		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// マジックナンバー１は、NotificationのID.Layoutで宣言すると綺麗になる。
		manager.notify(1, notification);
	}

}
