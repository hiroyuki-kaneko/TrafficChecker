.. java:import:: java.util Calendar

.. java:import:: java.util Date

.. java:import:: android.content Context

.. java:import:: android.net TrafficStats

.. java:import:: com.nekoscape.android.ntc.common DataType

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.data.object SearchDatas

.. java:import:: com.nekoscape.android.ntc.data.object SearchDatas.Entity

.. java:import:: com.nekoscape.android.ntc.data.object TransferData

TrafficManager
==============

.. java:package:: com.nekoscape.android.ntc.data.operator
   :noindex:

.. java:type:: public class TrafficManager

   3G, WIFIの通信量を記録する。 作成するアプリはWifiを計測することがメインであるが、将来的に有料版でMobile通信もチェックできるようにするので

   :author: someone

Methods
-------
exec
^^^^

.. java:method:: public void exec(NetworkStatus status)
   :outertype: TrafficManager

   通信量を記録。

   :param status:

getInstance
^^^^^^^^^^^

.. java:method:: public static synchronized TrafficManager getInstance(Context context)
   :outertype: TrafficManager

getMobileTrafficSize
^^^^^^^^^^^^^^^^^^^^

.. java:method:: public double getMobileTrafficSize()
   :outertype: TrafficManager

getTotalTrafficSize
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Deprecated public long getTotalTrafficSize(NetworkStatus status)
   :outertype: TrafficManager

hasCurrrentTraffic
^^^^^^^^^^^^^^^^^^

.. java:method:: public boolean hasCurrrentTraffic()
   :outertype: TrafficManager

upgradeTrafficdataTable
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public void upgradeTrafficdataTable()
   :outertype: TrafficManager

