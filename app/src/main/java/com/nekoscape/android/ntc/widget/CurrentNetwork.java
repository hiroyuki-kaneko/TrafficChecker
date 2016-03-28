package com.nekoscape.android.ntc.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.nekoscape.android.ntc.activity.MainActivity;
import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.common.ByteUnit;
import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.common.TextDrawHelper;
import com.nekoscape.android.ntc.common.Util;
import com.nekoscape.android.ntc.data.operator.TrafficManager;

import java.text.NumberFormat;

public abstract class CurrentNetwork extends AppWidgetProvider {

	private static int width = 250;
	private static int hight = 100;

	abstract int getWidth();

	abstract int getHight();

	@Override
	public void onUpdate(Context context, AppWidgetManager awm, int[] awi) {
		super.onUpdate(context, awm, awi);
		if (context == null) {
			return;
		}

		// サービスの開始
		Intent intent = new Intent(context, WidgetService.class);
		context.startService(intent);

	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		if (context == null) {
			return;
		}
		// サービスの停止
		Intent intent = new Intent(context, WidgetService.class);
		context.stopService(intent);
	}

	public static class WidgetService extends Service {
		private static final int waitTime = 10;

		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			super.onStartCommand(intent, flags, startId);

			// 次回起動の予約
			nextPlanScheduled();

			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			if (pm != null && !pm.isScreenOn()) {
				return START_STICKY;
			}

			// 処理の実行
			Log.d(this.getPackageName(), "service　起動");
			paintWidget(getApplicationContext());

			return START_STICKY;
		}

		private void nextPlanScheduled() {
			long now = System.currentTimeMillis();
			// アラームをセット
			PendingIntent alarmSender = PendingIntent.getService(this, 0,
					new Intent(this, this.getClass()), 0);
			AlarmManager am = (AlarmManager) this
					.getSystemService(Context.ALARM_SERVICE);
			am.set(AlarmManager.RTC, now + Util.waitingTime(waitTime),
					alarmSender);
		}

		@Override
		public IBinder onBind(Intent intent) {
			return null;
		}

		@Override
		public void onDestroy() {
			PendingIntent alarmSender = PendingIntent.getService(this, 0,
					new Intent(this, this.getClass()), 0);
			AlarmManager am = (AlarmManager) this
					.getSystemService(Context.ALARM_SERVICE);
			if (am != null) {
				am.cancel(alarmSender);
			}
		}

		private void paintWidget(Context context) {
			if(context == null){
				return ;
			}
			// 描画
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widghet_currentnetwork);
			Bitmap bitmap = Bitmap.createBitmap(width, hight,
					Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);

			Paint rectPaint = new Paint();
			rectPaint.setAntiAlias(true);
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(this);
			if(preferences == null){
				return;
			}
			int background = preferences.getInt("widget_background_color",
					Color.BLACK);
			int alpha = preferences.getInt("widget_alpha", 100);
			rectPaint.setARGB(alpha, Color.red(background),
					Color.green(background), Color.blue(background));

			canvas.drawRoundRect(new RectF(0, 0, width, hight), 20, 20,
					rectPaint);

			int textColor = preferences
					.getInt("widget_text_color", Color.WHITE);
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setARGB(255, Color.red(textColor), Color.green(textColor),
					Color.blue(textColor));
			paint.setTextSize(20);

			NetworkStatus status = new NetworkStatus(context);

			TrafficManager trafficManager = TrafficManager.getInstance(context);
			NumberFormat format = NumberFormat.getInstance();
			format.setMaximumFractionDigits(2);
			format.setMinimumFractionDigits(2);

			TextDrawHelper ssidHelper = new TextDrawHelper(width / 2, 31,
					status.getSSID(), paint);
			double trafficSize = 0f;
			if (status.isConnected()) {
				if (status.isWifiConnected()) {
					trafficSize = trafficManager.getTotalTrafficSize(status);
				} else {
					trafficSize = trafficManager.getMobileTrafficSize();

				}
			}
			String str = String.format("%s MBytes",
					format.format(ByteUnit.BYTE.toMByte(trafficSize)));

			TextDrawHelper trafficHelper = new TextDrawHelper(width / 2, 69,
					str, paint);

			canvas.drawText(ssidHelper.getText(), ssidHelper.getX(),
					ssidHelper.getY(), paint);
			canvas.drawText(trafficHelper.getText(), trafficHelper.getX(),
					trafficHelper.getY(), paint);

			remoteViews.setImageViewBitmap(R.id.widget_canvas, bitmap);

			// タップしたら画面を開く
			Intent mainIntent = new Intent(context, MainActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
					mainIntent, 0);
			remoteViews.setOnClickPendingIntent(R.id.widgetButton,
					pendingIntent);

			ComponentName thisWidget = new ComponentName(context,
					CurrentNetwork_2x1.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			if(manager == null){
				return ;
			}
			manager.updateAppWidget(thisWidget, remoteViews);
		}
	}
}
