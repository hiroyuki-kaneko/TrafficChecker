package com.nekoscape.android.ntc.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.nekoscape.android.ntc.common.Util;

public abstract class BasePeriodicService extends Service {

	private static boolean notificationFlag = true;

	public static boolean isNotificationFlag() {
		return notificationFlag;
	}

	public static void setNotificationFlag(boolean notificationFlag) {
		BasePeriodicService.notificationFlag = notificationFlag;
	}
	

	/**
	 * 定期実行したいタスクの中身（１回分） タスクの実行が完了したら，次回の実行計画を立てること。
	 */
	protected void execTask(){
		// 次回の起動計画
		makeNextPlan();
		
		// タスクの実行
		runTask();
	}
	
	/**
	 * 実行するタスクを記載する
	 */
	protected abstract void runTask();

	/**
	 * 次回の実行計画を立てる。
	 */
	protected abstract void makeNextPlan();

	// ---------- 必須メンバ -----------

	protected final IBinder binder = new Binder() {
		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			return super.onTransact(code, data, reply, flags);
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	// ---------- サービスのライフサイクル -----------

	/**
	 * 常駐を開始
	 */
	public BasePeriodicService startResident(Context context) {
		Intent intent = new Intent(context, this.getClass());
		intent.putExtra("type", "start");
		context.startService(intent);

		return this;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// createNotify("HOGE","TEXT");

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// サービス起動時の処理。
		// サービス起動中に呼ぶと複数回コールされ得る。しかし二重起動はしない
		// @see http://d.hatena.ne.jp/rso/20110911
		super.onStartCommand(intent, flags, startId);

		// タスクを実行
		Handler handler = new Handler();

		handler.post(new Runnable() {

			@Override
			public void run() {
				execTask();

			}
		});

		// NOTE: ここで次回の実行計画を逐次的にコールしていない理由は，
		// タスクが非同期の場合があるから。

		return START_STICKY;
	}

	/**
	 * サービスの次回の起動を予約
	 * 
	 * @param waitTime
	 */
	public void scheduleNextTime(int waitTime) {

		long now = System.currentTimeMillis();

		Log.d("wait time", "" + waitTime);

		// アラームをセット
		PendingIntent alarmSender = PendingIntent.getService(this, 0,
				new Intent(this, this.getClass()), 0);
		AlarmManager am = (AlarmManager) this
				.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC, now + Util.waitingTime(waitTime), alarmSender);
		// 次回登録が完了

	}

	/**
	 * サービスの定期実行を解除し，サービスを停止
	 */
	public void stopResident(Context context) {
		// サービス名を指定
		Intent intent = new Intent(context, this.getClass());

		// アラームを解除
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, // ここを-1にすると解除に成功しない
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
		// @see http://creadorgranoeste.blogspot.com/2011/06/alarmmanager.html

		// サービス自体を停止
		stopSelf();
	}

}
