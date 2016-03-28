package com.nekoscape.android.ntc.activity.factory;

import android.content.Intent;

import com.nekoscape.android.ntc.activity.MainActivity;
import com.nekoscape.android.ntc.activity.SettingsActivity;
import com.nekoscape.android.ntc.activity.SettingsLowerActivity;

public class PreferenceFactory {

	public static Intent createInstance(MainActivity activity, int version){
		if(version < 11){
			return new Intent(activity, SettingsLowerActivity.class);
		}else{
			return new Intent(activity, SettingsActivity.class);
			
		}
	}
}
