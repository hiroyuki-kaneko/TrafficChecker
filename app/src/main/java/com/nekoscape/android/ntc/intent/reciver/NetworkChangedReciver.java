package com.nekoscape.android.ntc.intent.reciver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.common.Util;
import com.nekoscape.android.ntc.data.operator.TrafficManager;
import com.nekoscape.android.ntc.service.TrafficCheckService;

/**
 * ネットワークの状態変更を検出した場合の処理を担当
 * 
 * @author someone
 * 
 */
public class NetworkChangedReciver extends BaseOnBroadcastReciver {

	@Override
	public String getBroadcastIntent() {
		return ConnectivityManager.CONNECTIVITY_ACTION;
	}

	/**
	 * 同時に呼び出された場合を考慮し、排他を取る。（バックグラウンドで動作するため問題はないはず）
	 */
	@Override
	public synchronized void execute(Context context) {
		if(context == null){
			return;
		}
		Log.d(this.getClass().getName(), "NetworkChanged");
		// 変更前のネットワーク情報で、通信量の書き込みを行っておく。デフォルトの動作を30秒に一度にする代わりに、ネットワークの変更のたびに書き込みを行うようにして精度を保つ。（バッテリー対策）
		NetworkStatus status = Util.getNetworkStatus(context);
		if (status != null && status.isConnected()) {
			TrafficManager manager = TrafficManager.getInstance(context);
			manager.exec(status);
		}

		// 新しいネットワーク情報に書き換える。
		Util.changeNetworkPreference(context);
		

		// ネットワークの状態がOFFになった場合は、次呼び出されるまで、サービスも起動しない。ONになった場合はサービス開始
		ConnectivityManager service = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = service.getActiveNetworkInfo();
		if (info == null) {
			// 接続なし
			 new TrafficCheckService().stopResident(context);
			 return;
		}

		if (info.isConnected()) {
			// 何かに接続中
			 new TrafficCheckService().startResident(context);
		} else {
			// 接続なし
			 new TrafficCheckService().stopResident(context);

		}
	}
}
