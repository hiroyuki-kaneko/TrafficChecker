package com.nekoscape.android.ntc.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.analytics.tracking.android.EasyTracker;
import com.nekoscape.android.ntc.common.UpgradeTrafficdataTable;
import com.nekoscape.android.ntc.common.Util;
import com.nekoscape.android.ntc.data.operator.TrafficManager;

public class UpgradeActivity extends Activity {
	private UpgradeTrafficdataTable task = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upgrade);
		final TrafficManager manager = TrafficManager.getInstance(this);

		upgrade_0_9_5(manager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.upgrade, menu);
		return true;
	}

	@Override
	public void onStart() {
		super.onStart();

		if (Util.canSendAnalystics(this)) {
			Log.d("","Google analystics通知");
			EasyTracker.getInstance(this).activityStart(this);
		}
	}

	@Override
	public void onStop() {
		super.onStop();

		if (Util.canSendAnalystics(this)) {
			EasyTracker.getInstance(this).activityStop(this);
		}
	}

	@Override
	public void onPause(){
		super.onPause();
		if(task != null){
			task.dismissDialog();
		}
	}
	
	@Override
	public void onUserLeaveHint() {
		super.onUserLeaveHint();
		if(task != null){
			task.dismissDialog();
		}
		
	}

	private synchronized void upgrade_0_9_5(final TrafficManager manager) {
		SharedPreferences preferences = getSharedPreferences("system_pref", Context.MODE_PRIVATE);
		boolean flag = preferences.getBoolean("upgrade_table_trafficdata", false);
		if(!flag){
			final ProgressDialog dialog = new ProgressDialog(this);
			task = new UpgradeTrafficdataTable(this,dialog,manager);
			
			task.execute(new Void[0]);
			
			// 処理完了
			Log.d("","処理完了");
		}else{
			startActivity(new Intent(this, MainActivity.class));
		}
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean("upgrade_table_trafficdata", true);
		editor.commit();
	}

}
