package com.nekoscape.android.ntc.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.nekoscape.android.ntc.activity.pref.PreferenceChangedUtil;
import com.nekoscape.android.ntc.activity.pref.PreferenceNetworkTypeList;
import com.nekoscape.android.ntc.common.Util;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends Activity {
	@SuppressLint("NewApi")
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		getFragmentManager()
				.beginTransaction()
				.replace(android.R.id.content, new SettingPreferencesFragment())
				.commit();

		
	}

	/** {@inheritDoc} */
	// @Override
	// public boolean onIsMultiPane() {
	// return isXLargeTablet(this) && !isSimplePreferences(this);
	// }

	// /** {@inheritDoc} */
	// @Override
	// @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	// public void onBuildHeaders(List<Header> target) {
	// if (!isSimplePreferences(this)) {
	// loadHeadersFromResource(R.xml.pref_headers, target);
	// }
	// }

	
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
	/**
	 * This fragment shows general preferences only. It is used when the
	 * activity is showing a two-pane settings UI.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class GeneralPreferenceFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref_general);

		}
	}

	@SuppressLint("NewApi")
	public static class SettingPreferencesFragment extends PreferenceFragment
			implements OnSharedPreferenceChangeListener {

		@SuppressLint("NewApi")
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			addPreferencesFromResource(R.xml.pref_general);
			ListPreference listPref= (ListPreference)findPreference("alarm_ssid_type");
			PreferenceNetworkTypeList prefNetwork = new PreferenceNetworkTypeList(getActivity());
			prefNetwork.setContent(listPref);
			
		}

		@SuppressLint("NewApi")
		@Override
		public void onResume() {
			super.onResume();

			final SharedPreferences preferences = getPreferenceManager()
					.getSharedPreferences();
			preferences.registerOnSharedPreferenceChangeListener(this);
			
		}

		@SuppressLint("NewApi")
		@Override
		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			if (sharedPreferences == null || key == null) {
				// 引数がNullだったら何もしない。
				return;
			}
			

			Log.d("change pref", "something");
			Activity activity = getActivity();
			if(activity == null){
				return;
			}
			PreferenceChangedUtil.changePreference(activity, sharedPreferences, key);
		}
	}
}
