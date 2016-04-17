.. java:import:: java.util ArrayList

.. java:import:: java.util Calendar

.. java:import:: java.util List

.. java:import:: java.util Locale

.. java:import:: android.content Context

.. java:import:: android.database Cursor

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: com.nekoscape.android.ntc.dao DaoMaster

.. java:import:: com.nekoscape.android.ntc.dao DaoSession

.. java:import:: com.nekoscape.android.ntc.dao Hour

.. java:import:: com.nekoscape.android.ntc.dao HourDao

.. java:import:: com.nekoscape.android.ntc.dao NetworkType

.. java:import:: com.nekoscape.android.ntc.dao NetworkTypeDao

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

CompareSsid
===========

.. java:package:: com.nekoscape.android.ntc.data.operator
   :noindex:

.. java:type:: public class CompareSsid

Constructors
------------
CompareSsid
^^^^^^^^^^^

.. java:constructor:: public CompareSsid(Context context)
   :outertype: CompareSsid

Methods
-------
getAllNetworkType
^^^^^^^^^^^^^^^^^

.. java:method:: public List<NetworkType> getAllNetworkType()
   :outertype: CompareSsid

getDayTraffics
^^^^^^^^^^^^^^

.. java:method:: public List<Hour> getDayTraffics(Calendar cal)
   :outertype: CompareSsid

getMonthTraffics
^^^^^^^^^^^^^^^^

.. java:method:: public List<Hour> getMonthTraffics(Calendar cal)
   :outertype: CompareSsid

getNetworkId
^^^^^^^^^^^^

.. java:method:: public long getNetworkId(NetworkStatus status)
   :outertype: CompareSsid

getNetworkType
^^^^^^^^^^^^^^

.. java:method:: public NetworkType getNetworkType(Long id)
   :outertype: CompareSsid

