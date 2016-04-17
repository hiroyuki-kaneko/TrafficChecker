.. java:import:: java.util Calendar

.. java:import:: org.achartengine.model TimeSeries

.. java:import:: org.achartengine.model XYMultipleSeriesDataset

.. java:import:: android.content Context

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common DataType

.. java:import:: com.nekoscape.android.ntc.data.operator UserDataManager

YesterdayChart
==============

.. java:package:: com.nekoscape.android.ntc.chart
   :noindex:

.. java:type:: public class YesterdayChart extends TrafficChart

Constructors
------------
YesterdayChart
^^^^^^^^^^^^^^

.. java:constructor:: public YesterdayChart(Context context, int type, int subtype, String ssid)
   :outertype: YesterdayChart

Methods
-------
getCalendar
^^^^^^^^^^^

.. java:method:: @Override protected Calendar getCalendar()
   :outertype: YesterdayChart

getGraphCurrentSSIDType
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public DataType getGraphCurrentSSIDType()
   :outertype: YesterdayChart

getGraphDataType
^^^^^^^^^^^^^^^^

.. java:method:: @Override public DataType getGraphDataType()
   :outertype: YesterdayChart

getGraphUnit
^^^^^^^^^^^^

.. java:method:: @Override public int getGraphUnit()
   :outertype: YesterdayChart

getOriginCrrection
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getOriginCrrection()
   :outertype: YesterdayChart

getXAxisCalMax
^^^^^^^^^^^^^^

.. java:method:: @Override public long getXAxisCalMax()
   :outertype: YesterdayChart

getXAxisCalMin
^^^^^^^^^^^^^^

.. java:method:: @Override public long getXAxisCalMin()
   :outertype: YesterdayChart

getXAxisFormat
^^^^^^^^^^^^^^

.. java:method:: @Override public String getXAxisFormat()
   :outertype: YesterdayChart

getXAxisMax
^^^^^^^^^^^

.. java:method:: @Override public int getXAxisMax()
   :outertype: YesterdayChart

getXAxisTitle
^^^^^^^^^^^^^

.. java:method:: @Override public String getXAxisTitle()
   :outertype: YesterdayChart

getXAxisUnit
^^^^^^^^^^^^

.. java:method:: @Override public int getXAxisUnit()
   :outertype: YesterdayChart

makeSeries
^^^^^^^^^^

.. java:method:: @Override public XYMultipleSeriesDataset makeSeries()
   :outertype: YesterdayChart

