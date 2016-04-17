.. java:import:: java.util Calendar

.. java:import:: org.achartengine.model TimeSeries

.. java:import:: org.achartengine.model XYMultipleSeriesDataset

.. java:import:: android.content Context

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common DataType

.. java:import:: com.nekoscape.android.ntc.data.operator UserDataManager

ThisMonthChart
==============

.. java:package:: com.nekoscape.android.ntc.chart
   :noindex:

.. java:type:: public class ThisMonthChart extends TrafficChart

Constructors
------------
ThisMonthChart
^^^^^^^^^^^^^^

.. java:constructor:: public ThisMonthChart(Context applicationContext, int type, int subtype, String ssid)
   :outertype: ThisMonthChart

Methods
-------
getCalendar
^^^^^^^^^^^

.. java:method:: @Override protected Calendar getCalendar()
   :outertype: ThisMonthChart

getGraphCurrentSSIDType
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public DataType getGraphCurrentSSIDType()
   :outertype: ThisMonthChart

getGraphDataType
^^^^^^^^^^^^^^^^

.. java:method:: @Override public DataType getGraphDataType()
   :outertype: ThisMonthChart

getGraphUnit
^^^^^^^^^^^^

.. java:method:: @Override public int getGraphUnit()
   :outertype: ThisMonthChart

getOriginCrrection
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getOriginCrrection()
   :outertype: ThisMonthChart

getXAxisCalMax
^^^^^^^^^^^^^^

.. java:method:: @Override public long getXAxisCalMax()
   :outertype: ThisMonthChart

getXAxisCalMin
^^^^^^^^^^^^^^

.. java:method:: @Override public long getXAxisCalMin()
   :outertype: ThisMonthChart

getXAxisFormat
^^^^^^^^^^^^^^

.. java:method:: @Override public String getXAxisFormat()
   :outertype: ThisMonthChart

getXAxisMax
^^^^^^^^^^^

.. java:method:: @Override public int getXAxisMax()
   :outertype: ThisMonthChart

getXAxisTitle
^^^^^^^^^^^^^

.. java:method:: @Override public String getXAxisTitle()
   :outertype: ThisMonthChart

getXAxisUnit
^^^^^^^^^^^^

.. java:method:: @Override public int getXAxisUnit()
   :outertype: ThisMonthChart

makeSeries
^^^^^^^^^^

.. java:method:: @Override public XYMultipleSeriesDataset makeSeries()
   :outertype: ThisMonthChart

