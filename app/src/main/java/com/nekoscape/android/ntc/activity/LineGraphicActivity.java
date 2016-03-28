/*
 * Copyright (C) 2013 Nekoscape Project
 *
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nekoscape.android.ntc.activity;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.google.analytics.tracking.android.EasyTracker;
import com.nekoscape.android.ntc.chart.ThisMonthChart;
import com.nekoscape.android.ntc.chart.ThisYearChart;
import com.nekoscape.android.ntc.chart.TodayChart;
import com.nekoscape.android.ntc.chart.TrafficChart;
import com.nekoscape.android.ntc.chart.YesterdayChart;
import com.nekoscape.android.ntc.common.Util;

public class LineGraphicActivity extends ActionBarActivity implements
		ActionBar.OnNavigationListener {

	private ArrayAdapter<CharSequence> spinnerAdapter = null;
	private int type = 0;
	private int subtype = 0;
	private String ssid = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 引数を受け取る
		paint();
		return;
	}

	@Override
	public void onResume() {
		super.onResume();
		paint();
	}

	@Override
	public void onPause() {
		super.onPause();
		LinearLayout layout = (LinearLayout) findViewById(R.id.chart_area);
		layout.removeAllViews();
	}

	@Override
	public void onStart() {
		super.onStart();

		if (Util.canSendAnalystics(this)) {
			Log.d("", "Google analystics通知");
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

	private void paint() {
		Intent intent = getIntent();
		this.type = intent.getIntExtra("TYPE", 0);
		this.subtype = intent.getIntExtra("SUBTYPE", 0);
		this.ssid = intent.getStringExtra("SSID");
		Log.d(this.getClass().getName(),
				String.format("intent args = %s,%s,%s", type, subtype, ssid));

		ActionBar mActionBar = getSupportActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_graph);

		spinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.graph_view_spinner, R.layout.linegraph_dropdownlist);
		mActionBar.setListNavigationCallbacks(spinnerAdapter, this);

	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long arg1) {

		TrafficChart chart = null;
		String item = (String) spinnerAdapter.getItem(itemPosition);
		Log.d("spinnerAdapter", item);
		if (item.equals(getString(R.string.yesterday))) {
			chart = new YesterdayChart(getApplicationContext(), this.type,
					this.subtype, this.ssid);
			Log.d("graph", "yesterday");
		} else if (item.equals(getString(R.string.today))) {
			chart = new TodayChart(getApplicationContext(), this.type,
					this.subtype, this.ssid);
		} else if (item.equals(getString(R.string.month))) {
			chart = new ThisMonthChart(getApplicationContext(), this.type,
					this.subtype, this.ssid);
		} else {
			chart = new ThisYearChart(getApplicationContext(), this.type,
					this.subtype, this.ssid);
		}

		LinearLayout layout = (LinearLayout) findViewById(R.id.chart_area);

		GraphicalView graView = ChartFactory.getTimeChartView(
				getApplicationContext(), chart.makeSeries(),
				chart.makeRenderer(), chart.getXAxisFormat());
		layout.removeAllViewsInLayout();
		layout.addView(graView);

		return true;
	}
}
