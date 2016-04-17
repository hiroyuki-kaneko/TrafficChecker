.. java:import:: android.content Context

.. java:import:: android.net ConnectivityManager

.. java:import:: android.net NetworkInfo

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.common Util

.. java:import:: com.nekoscape.android.ntc.data.operator TrafficManager

.. java:import:: com.nekoscape.android.ntc.service TrafficCheckService

NetworkChangedReciver
=====================

.. java:package:: com.nekoscape.android.ntc.intent.reciver
   :noindex:

.. java:type:: public class NetworkChangedReciver extends BaseOnBroadcastReciver

   ネットワークの状態変更を検出した場合の処理を担当

   :author: someone

Methods
-------
execute
^^^^^^^

.. java:method:: @Override public synchronized void execute(Context context)
   :outertype: NetworkChangedReciver

   同時に呼び出された場合を考慮し、排他を取る。（バックグラウンドで動作するため問題はないはず）

getBroadcastIntent
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public String getBroadcastIntent()
   :outertype: NetworkChangedReciver

