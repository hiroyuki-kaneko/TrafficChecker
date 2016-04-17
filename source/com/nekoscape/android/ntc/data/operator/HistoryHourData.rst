.. java:import:: android.content Context

.. java:import:: android.database Cursor

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.dao DaoMaster

.. java:import:: com.nekoscape.android.ntc.dao HourDao

.. java:import:: com.nekoscape.android.ntc.dao Hour

.. java:import:: java.util Calendar

.. java:import:: java.util Locale

HistoryHourData
===============

.. java:package:: com.nekoscape.android.ntc.data.operator
   :noindex:

.. java:type:: public class HistoryHourData

Constructors
------------
HistoryHourData
^^^^^^^^^^^^^^^

.. java:constructor:: public HistoryHourData(Context context)
   :outertype: HistoryHourData

Methods
-------
betweenDate
^^^^^^^^^^^

.. java:method:: public Hour betweenDate(NetworkStatus status, int start, int end)
   :outertype: HistoryHourData

monthData
^^^^^^^^^

.. java:method:: public Hour monthData(NetworkStatus status)
   :outertype: HistoryHourData

