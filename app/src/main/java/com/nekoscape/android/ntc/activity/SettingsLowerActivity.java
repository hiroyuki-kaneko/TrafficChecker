package com.nekoscape.android.ntc.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.nekoscape.android.ntc.activity.pref.PreferenceChangedUtil;
import com.nekoscape.android.ntc.activity.pref.PreferenceNetworkTypeList;
import com.nekoscape.android.ntc.common.Util;

public class SettingsLowerActivity extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref_general);
		
		ListPreference listPref= (ListPreference)findPreference("alarm_ssid_type");
		PreferenceNetworkTypeList prefNetwork = new PreferenceNetworkTypeList(this);
		prefNetwork.setContent(listPref);

	}

	@Override
	public void onResume() {
		super.onResume();
		@SuppressWarnings("deprecation")
		final SharedPreferences preferences = getPreferenceManager()
				.getSharedPreferences();
		preferences.registerOnSharedPreferenceChangeListener(this);
		
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
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (sharedPreferences == null || key == null) {
			// 引数がNullだったら何もしない。
			return;
		}

		Log.d("change pref", "something");
		PreferenceChangedUtil.changePreference(this, sharedPreferences, key);

	}
}
