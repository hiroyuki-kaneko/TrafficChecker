package com.nekoscape.android.ntc.intent.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class BaseOnBroadcastReciver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent intent) {
		if (getBroadcastIntent().equals(intent.getAction())) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					execute(context);
				}
			}).start();

		}
	}
	
	/**
	 * BroadcastIntentの文字列を返却する。一致するインテントを受信した場合、同クラスのexecuteメソッドが呼び出される。
	 * @return BroadcatIntentの文字列
	 */
	abstract public String getBroadcastIntent();
	
	/**
	 * BroadcastIntentを受信した際に呼び出す処理
	 * @param context
	 */
	abstract public void execute(Context context);

}
