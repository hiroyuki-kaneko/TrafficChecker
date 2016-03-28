package com.nekoscape.android.ntc.intent.reciver;

import com.nekoscape.android.ntc.service.TrafficCheckService;

import android.content.Context;
import android.content.Intent;

public class ServiceBootReceiver extends BaseOnBroadcastReciver {

	@Override
	public String getBroadcastIntent() {
		return Intent.ACTION_BOOT_COMPLETED;
	}

	@Override
	public void execute(Context context) {
		// サンプルのサービス常駐を開始
		new TrafficCheckService().startResident(context);
	}

}
