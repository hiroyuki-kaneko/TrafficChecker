.. java:import:: java.util ArrayList

.. java:import:: java.util Calendar

.. java:import:: java.util List

.. java:import:: org.achartengine.chart LineChart

.. java:import:: org.achartengine.model TimeSeries

.. java:import:: org.achartengine.model XYMultipleSeriesDataset

.. java:import:: org.achartengine.model XYSeries

.. java:import:: org.achartengine.renderer XYMultipleSeriesRenderer

.. java:import:: org.achartengine.renderer XYSeriesRenderer

.. java:import:: org.achartengine.renderer XYSeriesRenderer.FillOutsideLine

.. java:import:: android.content Context

.. java:import:: android.graphics Color

.. java:import:: android.graphics Paint.Align

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common ByteUnit

.. java:import:: com.nekoscape.android.ntc.common DataType

.. java:import:: com.nekoscape.android.ntc.data.object SearchDatas

.. java:import:: com.nekoscape.android.ntc.data.object SearchDatas.Entity

.. java:import:: com.nekoscape.android.ntc.data.operator UserDataManager

TrafficChart
============

.. java:package:: com.nekoscape.android.ntc.chart
   :noindex:

.. java:type:: public abstract class TrafficChart

Fields
------
context
^^^^^^^

.. java:field:: protected Context context
   :outertype: TrafficChart

Constructors
------------
TrafficChart
^^^^^^^^^^^^

.. java:constructor:: public TrafficChart(Context context)
   :outertype: TrafficChart

TrafficChart
^^^^^^^^^^^^

.. java:constructor:: public TrafficChart()
   :outertype: TrafficChart

Methods
-------
calcMobileData
^^^^^^^^^^^^^^

.. java:method:: protected void calcMobileData(TimeSeries mobileSeries, UserDataManager dataManager, int addDays)
   :outertype: TrafficChart

calcTrafficData
^^^^^^^^^^^^^^^

.. java:method:: protected void calcTrafficData(TimeSeries totalSeries, UserDataManager dataManager, int addDays)
   :outertype: TrafficChart

getCalendar
^^^^^^^^^^^

.. java:method:: protected abstract Calendar getCalendar()
   :outertype: TrafficChart

getGraphCurrentSSIDType
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public abstract DataType getGraphCurrentSSIDType()
   :outertype: TrafficChart

getGraphDataType
^^^^^^^^^^^^^^^^

.. java:method:: public abstract DataType getGraphDataType()
   :outertype: TrafficChart

getGraphUnit
^^^^^^^^^^^^

.. java:method:: public abstract int getGraphUnit()
   :outertype: TrafficChart

getInitializedList
^^^^^^^^^^^^^^^^^^

.. java:method:: protected List<Double> getInitializedList(int num)
   :outertype: TrafficChart

getOffset
^^^^^^^^^

.. java:method:: public int getOffset()
   :outertype: TrafficChart

getOriginCrrection
^^^^^^^^^^^^^^^^^^

.. java:method:: public abstract int getOriginCrrection()
   :outertype: TrafficChart

getSsid
^^^^^^^

.. java:method:: public String getSsid()
   :outertype: TrafficChart

getSubtype
^^^^^^^^^^

.. java:method:: public int getSubtype()
   :outertype: TrafficChart

getType
^^^^^^^

.. java:method:: public int getType()
   :outertype: TrafficChart

getTypes
^^^^^^^^

.. java:method:: public String[] getTypes()
   :outertype: TrafficChart

getXAxisCalMax
^^^^^^^^^^^^^^

.. java:method:: public abstract long getXAxisCalMax()
   :outertype: TrafficChart

getXAxisCalMin
^^^^^^^^^^^^^^

.. java:method:: public abstract long getXAxisCalMin()
   :outertype: TrafficChart

getXAxisFormat
^^^^^^^^^^^^^^

.. java:method:: public abstract String getXAxisFormat()
   :outertype: TrafficChart

getXAxisMax
^^^^^^^^^^^

.. java:method:: public abstract int getXAxisMax()
   :outertype: TrafficChart

getXAxisTitle
^^^^^^^^^^^^^

.. java:method:: public abstract String getXAxisTitle()
   :outertype: TrafficChart

getXAxisUnit
^^^^^^^^^^^^

.. java:method:: public abstract int getXAxisUnit()
   :outertype: TrafficChart

getyMax
^^^^^^^

.. java:method:: public double getyMax()
   :outertype: TrafficChart

makeRenderer
^^^^^^^^^^^^

.. java:method:: public XYMultipleSeriesRenderer makeRenderer()
   :outertype: TrafficChart

   グラフ描画用の設定を返却する。

makeSeries
^^^^^^^^^^

.. java:method:: public abstract XYMultipleSeriesDataset makeSeries()
   :outertype: TrafficChart

searchCurrentSSIDTraffic
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void searchCurrentSSIDTraffic(TimeSeries currentSeries, UserDataManager dataManager, int addUnit)
   :outertype: TrafficChart

setMax
^^^^^^

.. java:method:: protected void setMax(double y)
   :outertype: TrafficChart

setSsid
^^^^^^^

.. java:method:: public void setSsid(String ssid)
   :outertype: TrafficChart

setSubtype
^^^^^^^^^^

.. java:method:: public void setSubtype(int subtype)
   :outertype: TrafficChart

setType
^^^^^^^

.. java:method:: public void setType(int type)
   :outertype: TrafficChart

setZeroClear
^^^^^^^^^^^^

.. java:method:: protected void setZeroClear(XYSeries series)
   :outertype: TrafficChart

setyMax
^^^^^^^

.. java:method:: public void setyMax(double yMax)
   :outertype: TrafficChart

