package com.nekoscape.android.ntc.chart;

import java.util.Calendar;

import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;

import android.content.Context;
import android.util.Log;

import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.common.DataType;
import com.nekoscape.android.ntc.data.operator.UserDataManager;

public class ThisYearChart extends TrafficChart {

	private Calendar calendar = Calendar.getInstance();

	public ThisYearChart(Context applicationContext, int type, int subtype,
			String ssid) {
		super(applicationContext);
		setType(type);
		setSubtype(subtype);
		setSsid(ssid);
	}

	@Override
	public int getXAxisMax() {
		return 12;
	}

	@Override
	public int getXAxisUnit() {
		return Calendar.MONTH;
	}

	@Override
	public DataType getGraphDataType() {
		return DataType.YEAR;
	}

	@Override
	public int getGraphUnit() {
		return Calendar.YEAR;
	}

	@Override
	public DataType getGraphCurrentSSIDType() {
		return DataType.YEAR_SSID;
	}

	@Override
	public String getXAxisFormat() {
		return "M";
	}

	@Override
	public int getOriginCrrection() {
		return 0;
	}

	@Override
	public long getXAxisCalMin() {
		calendar.clear(Calendar.DATE);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(this.calendar.get(Calendar.YEAR), 0, 0, 1, 0, 0);

		calendar.add(Calendar.YEAR, 0);
		Log.d(this.getClass().getName(), calendar.getTime().toString());
		return calendar.getTimeInMillis();
	}

	@Override
	public long getXAxisCalMax() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(this.calendar.get(Calendar.YEAR), 0, 0, 1, 0, 0);

		calendar.add(Calendar.MONTH, 11);
		Log.d(this.getClass().getName(), calendar.getTime().toString());
		return calendar.getTimeInMillis();

	}

	@Override
	public XYMultipleSeriesDataset makeSeries() {
		XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();

		TimeSeries mobileSeries = new TimeSeries(
				context.getString(R.string.mobile_traffic));
		TimeSeries totalSeries = new TimeSeries(
				context.getString(R.string.total_traffic));
		TimeSeries beforetotalSeries = new TimeSeries(
				context.getString(R.string.month_before));
		TimeSeries currentSeries = new TimeSeries(getSsid());

		myData.addSeries(beforetotalSeries);
		myData.addSeries(mobileSeries);
		myData.addSeries(totalSeries);
		myData.addSeries(currentSeries);

		UserDataManager dataManager = new UserDataManager(context);

		calcMobileData(mobileSeries, dataManager, 0);
		calcTrafficData(beforetotalSeries, dataManager, -1);
		calcTrafficData(totalSeries, dataManager, 0);
		searchCurrentSSIDTraffic(currentSeries, dataManager, 0);
		return myData;
	}

	@Override
	protected Calendar getCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear(Calendar.MONTH);
		calendar.clear(Calendar.MONTH);
		calendar.clear(Calendar.MONTH);
		calendar.clear(Calendar.DATE);
		calendar.clear(Calendar.WEEK_OF_MONTH);
		calendar.clear(Calendar.DAY_OF_MONTH);
		calendar.clear(Calendar.DAY_OF_WEEK);
		calendar.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		calendar.clear();
		calendar.set(this.calendar.get(Calendar.YEAR), 0, 0, 0, 0, 0);

		return calendar;
	}

	@Override
	public String getXAxisTitle() {
		return context.getString(R.string.year_x_axis);
	}
	
	@Override
	public int getOffset(){
		return 1;
	}

}
