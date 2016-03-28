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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.achartengine.chart.LineChart;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer.FillOutsideLine;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.Log;

import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.common.ByteUnit;
import com.nekoscape.android.ntc.common.DataType;
import com.nekoscape.android.ntc.data.object.SearchDatas;
import com.nekoscape.android.ntc.data.object.SearchDatas.Entity;
import com.nekoscape.android.ntc.data.operator.UserDataManager;

abstract public class TrafficChart {

	private double yMax = 0;

	private int type = 0;
	private int subtype = 0;
	private String ssid;

	protected enum TYPE {
		TOTAL("#158aea"), SEND("#ff8c00"), RECV("#dc143c"), BEFORE("#a9a9a9"),MOBILE("#ff0000");

		private final String color;

		TYPE(String color) {
			this.color = color;
		}

		public int getColor() {
			return Color.parseColor(color);
		}
	}

	public int getType() {
		return type;
	}

	public int getSubtype() {
		return subtype;
	}

	public String getSsid() {
		return ssid;
	}

	public String[] getTypes() {
		return new String[] { LineChart.TYPE, LineChart.TYPE };
	}

	public double getyMax() {
		return yMax;
	}

	public void setyMax(double yMax) {
		this.yMax = yMax;
	}

	protected void setMax(double y) {
		if (yMax <= y) {
			yMax = y;
		}
	}

	protected void setZeroClear(XYSeries series) {
		for (int i = 0; i < 33; i++) {
			series.add(i, 0);
		}
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSubtype(int subtype) {
		this.subtype = subtype;
	}

	abstract public int getXAxisMax();

	abstract public int getXAxisUnit();

	abstract public DataType getGraphDataType();

	abstract public DataType getGraphCurrentSSIDType();

	abstract public int getGraphUnit();

	abstract public String getXAxisFormat();

	abstract public long getXAxisCalMin();

	abstract public long getXAxisCalMax();

	abstract public String getXAxisTitle();
	
	abstract public int getOriginCrrection();

	public void setSsid(String ssid) {
		if (ssid == null) {
			this.ssid = "";
		} else {
			this.ssid = ssid;
		}
	}

	protected Context context;

	public TrafficChart(Context context) {
		this.context = context;
	}

	public TrafficChart() {
		super();
	}

	abstract public XYMultipleSeriesDataset makeSeries();

	protected void searchCurrentSSIDTraffic(TimeSeries currentSeries,
			UserDataManager dataManager, int addUnit) {
		List<Double> list = getInitializedList(getXAxisMax()+getOffset());

		Calendar cal = getCalendar();
		Calendar calendar = Calendar.getInstance();

		calendar.add(getGraphUnit(), addUnit);
		SearchDatas dataIterator = dataManager.getSearchData(
				getGraphCurrentSSIDType(), calendar, getType(), getSubtype(),
				getSsid());
		Log.d("get data", "" + dataIterator.count());
		while (dataIterator.hasNext()) {
			Entity entity = dataIterator.next();
			list.add((int) entity.getX() + getOriginCrrection(), Double.valueOf(ByteUnit.BYTE
					.toMByte(entity.getWifiTotal())));

			setMax(ByteUnit.BYTE.toMByte(entity.getWifiTotal()));
		}
		for (int i = 0; i < getXAxisMax(); i++) {
			currentSeries.add(cal.getTime(), list.get(i+getOffset()).doubleValue());
			cal.add(getXAxisUnit(), 1);
		}

	}

	protected List<Double> getInitializedList(int num) {
		List<Double> list = new ArrayList<Double>();

		// リスト初期化
		for (int i = 0; i < num; i++) {
			list.add(Double.valueOf(0));
		}
		return list;
	}
	
	protected void calcMobileData(TimeSeries mobileSeries, UserDataManager dataManager, int addDays){
		List<Double> list = getInitializedList(getXAxisMax()+getOffset());

		Calendar cal = getCalendar();
		Calendar calendar = Calendar.getInstance();
		calendar.add(getGraphUnit(), addDays);
		SearchDatas dataIterator = dataManager.getSearchData(
				getGraphDataType(), calendar);
		while (dataIterator.hasNext()) {
			Entity entity = dataIterator.next();
			list.add((int) entity.getX() + getOriginCrrection(), Double.valueOf(ByteUnit.BYTE
					.toMByte(entity.getMobileTotal())));

			setMax(ByteUnit.BYTE.toMByte(entity.getMobileTotal()));
		}

		for (int i = 0; i < getXAxisMax(); i++) {
			mobileSeries.add(cal.getTime(), list.get(i+getOffset()).doubleValue());
			cal.add(getXAxisUnit(), 1);
		}
	}
	
	protected void calcTrafficData(TimeSeries totalSeries,
			UserDataManager dataManager, int addDays) {
		List<Double> list = getInitializedList(getXAxisMax()+getOffset());

		Calendar cal = getCalendar();
		Calendar calendar = Calendar.getInstance();
		calendar.add(getGraphUnit(), addDays);
		SearchDatas dataIterator = dataManager.getSearchData(
				getGraphDataType(), calendar);
		while (dataIterator.hasNext()) {
			Entity entity = dataIterator.next();

			list.add((int) entity.getX() + getOriginCrrection(), Double.valueOf(ByteUnit.BYTE
					.toMByte(entity.getWifiTotal())));

			setMax(ByteUnit.BYTE.toMByte(entity.getWifiTotal()));
		}

		for (int i = 0; i < getXAxisMax(); i++) {
			totalSeries.add(cal.getTime(), list.get(i+getOffset()).doubleValue());
			cal.add(getXAxisUnit(), 1);
		}
	}

	/**
	 * グラフ描画用の設定を返却する。
	 * 
	 * @return
	 */
	public XYMultipleSeriesRenderer makeRenderer() {
		XYSeriesRenderer mobileRenderer = new XYSeriesRenderer();
		XYSeriesRenderer totalRenderer = new XYSeriesRenderer();
		XYSeriesRenderer beforeRenderer = new XYSeriesRenderer();
		XYSeriesRenderer currentRenderer = new XYSeriesRenderer();

		// 棒グラフの色
		beforeRenderer.setColor(TYPE.BEFORE.getColor());
		beforeRenderer.setLineWidth(3);
		FillOutsideLine fill = new FillOutsideLine(FillOutsideLine.Type.BELOW);
		fill.setColor(Color.parseColor("#a9a9a9"));
		beforeRenderer.addFillOutsideLine(fill);
		
		totalRenderer.setColor(TYPE.TOTAL.getColor());
		totalRenderer.setLineWidth(5);
		currentRenderer.setColor(TYPE.SEND.getColor());
		currentRenderer.setLineWidth(3);

		mobileRenderer.setColor(TYPE.MOBILE.getColor());
		mobileRenderer.setLineWidth(3);

		XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
		myRenderer.addSeriesRenderer(beforeRenderer);
		myRenderer.addSeriesRenderer(mobileRenderer);
		myRenderer.addSeriesRenderer(totalRenderer);
		myRenderer.addSeriesRenderer(currentRenderer);

		// XY（初期表示の？）最大最小値
		myRenderer.setXAxisMin(getXAxisCalMin());
		myRenderer.setXAxisMax(getXAxisCalMax());
		myRenderer.setYAxisMin(0);

		if (yMax == 0) {
			myRenderer.setYAxisMax(20);
		} else {
			myRenderer.setYAxisMax((double) (yMax) + yMax / 10);
		}

		// グリッド表示
		myRenderer.setShowGrid(true);
		// グリッド色
		myRenderer.setGridColor(Color.parseColor("#c9c9c9"));

		// スクロール許可(X,Y)
		myRenderer.setPanEnabled(false, false);
		// スクロール幅（X最少, X最大, Y最少, Y最大）
		myRenderer.setPanLimits(new double[] { getXAxisCalMin(),
				getXAxisCalMax(), 0, getyMax() });

		// 凡例表示
		myRenderer.setShowLegend(true);

		// ラベル表示
		myRenderer.setXLabels(10);
		myRenderer.setYLabels(12);
		myRenderer.setLabelsTextSize(20);
		myRenderer.setYLabelsAlign(Align.RIGHT);

		myRenderer.setChartTitleTextSize(20);
		myRenderer.setAxisTitleTextSize(20);
		myRenderer.setYTitle(context.getString(R.string.y_axsis));
		myRenderer.setXTitle(getXAxisTitle());

		myRenderer.setLabelsColor(Color.BLACK);
		myRenderer.setXLabelsColor(Color.BLACK);
		myRenderer.setYLabelsColor(0, Color.BLACK);

		// 凡例のサイズ
		myRenderer.setLegendTextSize(20);

		// XY軸表示
		myRenderer.setShowAxes(false);
		// バー間の間隔
		myRenderer.setBarSpacing(0);
		// ズーム許可
		myRenderer.setZoomEnabled(false, false);
		// 余白
		int[] margin = { 20, 70, 50, 30 };
		myRenderer.setMargins(margin);
		// 余白背景色
		myRenderer.setMarginsColor(Color.parseColor("#FFFFFF"));

		return myRenderer;
	}

	abstract protected Calendar getCalendar();
	
	public int getOffset(){
		return 0;
	}

}