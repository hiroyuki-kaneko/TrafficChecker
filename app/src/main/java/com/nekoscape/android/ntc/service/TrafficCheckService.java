package com.nekoscape.android.ntc.service;

import java.text.NumberFormat;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.nekoscape.android.ntc.activity.pref.PreferenceConstant;
import com.nekoscape.android.ntc.common.ByteUnit;
import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.common.Util;
import com.nekoscape.android.ntc.data.operator.TrafficManager;
import com.nekoscape.android.ntc.service.task.NotificationTask;
import com.nekoscape.android.ntc.service.task.TrafficAlarmTask;

public class TrafficCheckService extends BasePeriodicService {

	/**
	 * フラグを設定しNoritifationを停止する努力をする
	 * 
	 * @param flag
	 */
	public static void changeNotificationSetting(boolean flag) {
		setNotificationFlag(flag);
	}

	@Override
	protected void runTask() {

		// ※もし毎回の処理が重い場合は，メインスレッドを妨害しないために
		// ここから下を別スレッドで実行する。
		NetworkStatus status = Util.getNetworkStatus(this);

		if (status == null || !status.isConnected()) {
			Log.d("runTask", "no network");
			return;
		}

		// TODO 通信量の記録　タスクは理想かな。この処理の後で使うデータサイズをどうやって取るか。

		TrafficManager trafficManager = TrafficManager
				.getInstance(getApplicationContext());
		trafficManager.exec(status);

		if (!trafficManager.hasCurrrentTraffic()) {
			// 処理することがない。削除くらいはやったほうがいいかも・・・？
			return;
		}

		// 通信量のアラーム通知
		TrafficAlarmTask.makeAlarm(getApplicationContext(), status,
				trafficManager.getTotalTrafficSize(status));

		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);

		// 通知バーの表示更新
		NotificationTask.createNotify(getApplicationContext(),
				status.getSSID(), format.format(ByteUnit.BYTE
						.toMByte(trafficManager.getTotalTrafficSize(status))));

		// createNotify(status.getSSID(), format.format(ByteUnit.BYTE
		// .toMByte(trafficManager.getTotalTrafficSize(status))));

	}

	@Override
	public void makeNextPlan() {
		SharedPreferences preMane = (SharedPreferences) PreferenceManager
				.getDefaultSharedPreferences(this);
		int waitTime = Integer.parseInt(preMane.getString(
				PreferenceConstant.SERVICE_WAIT_TIME.getName(),
				PreferenceConstant.SERVICE_WAIT_TIME.getDefaultString()));
		this.scheduleNextTime(waitTime);
	}

}
