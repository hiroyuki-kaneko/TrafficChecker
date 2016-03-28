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
package com.nekoscape.android.ntc.chart;

import java.util.Calendar;

import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;

import android.content.Context;

import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.common.DataType;
import com.nekoscape.android.ntc.data.operator.UserDataManager;

public class YesterdayChart extends TrafficChart {
	
	private static final int CONSTANT_GRAPH_AXIS_DIV = Calendar.HOUR_OF_DAY;
	private static final DataType CONSTANT_GRAPH_TYPE = DataType.DAY;
	private static final int CONSTANT_CALC_TYPE = Calendar.DAY_OF_MONTH;
	private static final int CONSTANT_GRAPH_DIV_MAX = 24;


	public YesterdayChart(Context context, int type, int subtype, String ssid){
		super(context);
		setType(type);
		setSubtype(subtype);
		setSsid(ssid);

	}

	@Override
	public int getXAxisMax(){
		return CONSTANT_GRAPH_DIV_MAX;
	}
	
	@Override
	public int getXAxisUnit(){
		return CONSTANT_GRAPH_AXIS_DIV;
	}
	
	@Override
	public DataType getGraphDataType(){
		return CONSTANT_GRAPH_TYPE;
	}
	
	@Override
	public int getGraphUnit(){
		return CONSTANT_CALC_TYPE;
	}
	@Override
	public DataType getGraphCurrentSSIDType() {
		return DataType.DAY_SSID;
	}
	@Override
	public String getXAxisFormat(){
		return "HH";
	}
	@Override
	public int getOriginCrrection(){
		return 0;
	}

	@Override
	public long getXAxisCalMin() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);

		return calendar.getTimeInMillis();
	}

	@Override
	public long getXAxisCalMax() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);

		calendar.add(Calendar.HOUR_OF_DAY, 23);
		return calendar.getTimeInMillis();

	}

	
	@Override
	public XYMultipleSeriesDataset makeSeries() {
		XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();

		TimeSeries mobileSeries = new TimeSeries(context.getString(R.string.mobile_traffic));
		TimeSeries totalSeries = new TimeSeries(context.getString(R.string.total_traffic));
		TimeSeries beforetotalSeries = new TimeSeries(context.getString(R.string.day_before));
		TimeSeries currentSeries = new TimeSeries(getSsid());

		myData.addSeries(beforetotalSeries);
		myData.addSeries(mobileSeries);
		myData.addSeries(totalSeries);
		myData.addSeries(currentSeries);
	
		UserDataManager dataManager = new UserDataManager(context);
		
		calcMobileData(mobileSeries, dataManager, -1);
		calcTrafficData(beforetotalSeries, dataManager,-2);
		calcTrafficData(totalSeries, dataManager,-1);
		searchCurrentSSIDTraffic(currentSeries, dataManager, -1);

		return myData;
	}

	@Override
	protected Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.HOUR);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		return cal;
	}

	@Override
	public String getXAxisTitle() {
		return context.getString(R.string.x_axsis);
	}


}
