package com.nekoscape.android.ntc.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.nekoscape.android.ntc.activity.MainActivity;
import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.data.operator.TrafficManager;

public class UpgradeTrafficdataTable extends AsyncTask<Void, Void, Boolean> {
	ProgressDialog dialog;
	private Context context;
	private TrafficManager manager;
	private boolean processing = false;

	public UpgradeTrafficdataTable(Context context, ProgressDialog dialog,
			TrafficManager manager) {
		this.context = context;
		this.manager = manager;
		// this.dialog = dialog;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialog(this.context);
		Log.d("", "onPreExecute : ダイアログ表示");
		dialog.setTitle(this.context.getString(R.string.dialog_upgrade_0_9_5));
		dialog.setMessage(this.context
				.getString(R.string.dialog_upgrade_0_9_5_message));
		dialog.setIndeterminate(true);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setCancelable(false);
		dialog.show();
		processing = true;
		// dialog = ProgressDialog.show(this.context,
		// this.context.getString(R.string.dialog_upgrade_0_9_5),
		// this.context.getString(R.string.dialog_upgrade_0_9_5_message));
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		Log.d("", "処理開始：アップグレード");

		manager.upgradeTrafficdataTable();
		Log.d("", "処理完了：アップグレード");

		return Boolean.TRUE;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		dismissDialog();
		context.startActivity(new Intent(context, MainActivity.class));
		Log.d("", "onPostExecute呼び出し管理ょ湯");
	}

	public void dismissDialog(){
		if(dialog == null){
			return;
		}
		
		if(processing && dialog.isShowing()){
			dialog.dismiss();
		}
	}
}
