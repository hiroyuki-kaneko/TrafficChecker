package com.nekoscape.android.ntc.activity.pref;

import java.util.List;

import android.content.Context;
import android.preference.ListPreference;

import com.nekoscape.android.ntc.common.Util;

public class PreferenceNetworkTypeList {
	
	Context context = null;
	
	
	public PreferenceNetworkTypeList(Context context){
		this.context = context;
	}
	
	
	public void setContent(ListPreference listPref){
		List<String> ssids = Util.getNetworkSsidList(this.context);

		listPref.setEntries(ssids.toArray(new String[1]));
		listPref.setEntryValues(ssids.toArray(new String[1]));
		
	}

}
