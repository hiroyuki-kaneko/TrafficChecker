package com.nekoscape.android.ntc.intent.reciver;

import com.nekoscape.android.ntc.activity.pref.PreferenceConstant;
import com.nekoscape.android.ntc.data.operator.UserDataManager;

import android.content.Context;
import android.content.Intent;

public class DateChangedReciver extends BaseOnBroadcastReciver {

	@Override
	public String getBroadcastIntent() {
		return Intent.ACTION_DATE_CHANGED;
	}

	@Override
	public void execute(Context context) {
		
		// データの削除
		UserDataManager dataManager = new UserDataManager(context);
		dataManager.deleteOldData();
		
		// アラームの呼び出し可否をtrueに
		PreferenceConstant.ALARM_TRAFFIC_TODAY_NOTIFICATION.setPrivateValue(context, true);

	}

}
