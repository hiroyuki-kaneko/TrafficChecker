package com.nekoscape.android.ntc.common;

import android.content.Context;
import android.view.View;
import android.widget.TabHost.TabContentFactory;

public class DummyTabFactory implements TabContentFactory {

	private final Context context;

	public DummyTabFactory(Context context) {
		this.context = context;
	}

	@Override
	public View createTabContent(String tag) {
		return new View(this.context);
	}

}
