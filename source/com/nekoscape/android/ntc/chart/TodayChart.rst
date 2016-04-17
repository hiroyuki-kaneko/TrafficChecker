.. java:import:: java.util Calendar

.. java:import:: org.achartengine.model TimeSeries

.. java:import:: org.achartengine.model XYMultipleSeriesDataset

.. java:import:: android.content Context

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common DataType

.. java:import:: com.nekoscape.android.ntc.data.operator UserDataManager

TodayChart
==========

.. java:package:: com.nekoscape.android.ntc.chart
   :noindex:

.. java:type:: public class TodayChart extends TrafficChart

Constructors
------------
TodayChart
^^^^^^^^^^

.. java:constructor:: public TodayChart(Context context, int type, int subtype, String ssid)
   :outertype: TodayChart

Methods
-------
getCalendar
^^^^^^^^^^^

.. java:method:: @Override protected Calendar getCalendar()
   :outertype: TodayChart

getGraphCurrentSSIDType
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public DataType getGraphCurrentSSIDType()
   :outertype: TodayChart

getGraphDataType
^^^^^^^^^^^^^^^^

.. java:method:: @Override public DataType getGraphDataType()
   :outertype: TodayChart

getGraphUnit
^^^^^^^^^^^^

.. java:method:: @Override public int getGraphUnit()
   :outertype: TodayChart

getOriginCrrection
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getOriginCrrection()
   :outertype: TodayChart

getXAxisCalMax
^^^^^^^^^^^^^^

.. java:method:: @Override public long getXAxisCalMax()
   :outertype: TodayChart

getXAxisCalMin
^^^^^^^^^^^^^^

.. java:method:: @Override public long getXAxisCalMin()
   :outertype: TodayChart

getXAxisFormat
^^^^^^^^^^^^^^

.. java:method:: @Override public String getXAxisFormat()
   :outertype: TodayChart

getXAxisMax
^^^^^^^^^^^

.. java:method:: @Override public int getXAxisMax()
   :outertype: TodayChart

getXAxisTitle
^^^^^^^^^^^^^

.. java:method:: @Override public String getXAxisTitle()
   :outertype: TodayChart

getXAxisUnit
^^^^^^^^^^^^

.. java:method:: @Override public int getXAxisUnit()
   :outertype: TodayChart

makeSeries
^^^^^^^^^^

.. java:method:: public XYMultipleSeriesDataset makeSeries()
   :outertype: TodayChart

   当日分のデータを作成する この処理は時間がかかるため、ロード画面を作成することを推奨します。

