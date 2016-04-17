.. java:import:: java.util Calendar

.. java:import:: org.achartengine.model TimeSeries

.. java:import:: org.achartengine.model XYMultipleSeriesDataset

.. java:import:: android.content Context

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common DataType

.. java:import:: com.nekoscape.android.ntc.data.operator UserDataManager

ThisYearChart
=============

.. java:package:: com.nekoscape.android.ntc.chart
   :noindex:

.. java:type:: public class ThisYearChart extends TrafficChart

Constructors
------------
ThisYearChart
^^^^^^^^^^^^^

.. java:constructor:: public ThisYearChart(Context applicationContext, int type, int subtype, String ssid)
   :outertype: ThisYearChart

Methods
-------
getCalendar
^^^^^^^^^^^

.. java:method:: @Override protected Calendar getCalendar()
   :outertype: ThisYearChart

getGraphCurrentSSIDType
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public DataType getGraphCurrentSSIDType()
   :outertype: ThisYearChart

getGraphDataType
^^^^^^^^^^^^^^^^

.. java:method:: @Override public DataType getGraphDataType()
   :outertype: ThisYearChart

getGraphUnit
^^^^^^^^^^^^

.. java:method:: @Override public int getGraphUnit()
   :outertype: ThisYearChart

getOffset
^^^^^^^^^

.. java:method:: @Override public int getOffset()
   :outertype: ThisYearChart

getOriginCrrection
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getOriginCrrection()
   :outertype: ThisYearChart

getXAxisCalMax
^^^^^^^^^^^^^^

.. java:method:: @Override public long getXAxisCalMax()
   :outertype: ThisYearChart

getXAxisCalMin
^^^^^^^^^^^^^^

.. java:method:: @Override public long getXAxisCalMin()
   :outertype: ThisYearChart

getXAxisFormat
^^^^^^^^^^^^^^

.. java:method:: @Override public String getXAxisFormat()
   :outertype: ThisYearChart

getXAxisMax
^^^^^^^^^^^

.. java:method:: @Override public int getXAxisMax()
   :outertype: ThisYearChart

getXAxisTitle
^^^^^^^^^^^^^

.. java:method:: @Override public String getXAxisTitle()
   :outertype: ThisYearChart

getXAxisUnit
^^^^^^^^^^^^

.. java:method:: @Override public int getXAxisUnit()
   :outertype: ThisYearChart

makeSeries
^^^^^^^^^^

.. java:method:: @Override public XYMultipleSeriesDataset makeSeries()
   :outertype: ThisYearChart

