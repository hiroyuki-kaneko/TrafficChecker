package com.nekoscape.android.ntc.widget.service;

import java.text.NumberFormat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.common.ByteUnit;
import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.common.TextDrawHelper;
import com.nekoscape.android.ntc.common.Util;
import com.nekoscape.android.ntc.data.operator.TrafficManager;
import com.nekoscape.android.ntc.widget.CurrentNetwork_3x1;

public abstract class WidgetService extends Service{
	private static final int waitTime = 30;
    @Override
	public int onStartCommand(Intent intent, int flags, int startId) {
    	super.onStartCommand(intent, flags, startId);
    	
    	// 次回起動の予約
		nextPlanScheduled();
    	// 処理の実行
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
		am.set(AlarmManager.RTC, now + Util.waitingTime(waitTime), alarmSender);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onDestroy(){
		PendingIntent alarmSender = PendingIntent.getService(this, 0,
				new Intent(this, this.getClass()), 0);
		AlarmManager am = (AlarmManager) this
				.getSystemService(Context.ALARM_SERVICE);
		am.cancel(alarmSender);
		
	}
	
	private void paintWidget(Context context) {
		// 描画
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widghet_currentnetwork);
		Bitmap bitmap = Bitmap.createBitmap(200, 100,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		Paint rectPaint = new Paint();
		rectPaint.setAntiAlias(true);
		rectPaint.setARGB(255, 0, 0, 0);

		canvas.drawRoundRect(new RectF(0, 0, 200, 100), 20, 20,
				rectPaint);

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setARGB(255, 255, 255, 255);
		paint.setTextSize(20);

		NetworkStatus status = new NetworkStatus(context);

		TrafficManager trafficManager = TrafficManager.getInstance(context);
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);

		TextDrawHelper ssidHelper = new TextDrawHelper(200 / 2, 21,
				status.getSSID(), paint);
		String str = String.format("%s MBytes", format.format(ByteUnit.BYTE
				.toMByte(trafficManager.getTotalTrafficSize(status))));

		TextDrawHelper trafficHelper = new TextDrawHelper(200/2, 51, str, paint);

		canvas.drawText(ssidHelper.getText(), ssidHelper.getX(),
				ssidHelper.getY(), paint);
		canvas.drawText(trafficHelper.getText(), trafficHelper.getX(), trafficHelper.getY(), paint);

		remoteViews.setImageViewBitmap(R.id.widget_canvas, bitmap);

		ComponentName thisWidget = new ComponentName(context, CurrentNetwork_3x1.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(thisWidget, remoteViews);
	}

	
}