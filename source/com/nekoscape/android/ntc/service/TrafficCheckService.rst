.. java:import:: java.text NumberFormat

.. java:import:: android.content SharedPreferences

.. java:import:: android.preference PreferenceManager

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.activity.pref PreferenceConstant

.. java:import:: com.nekoscape.android.ntc.common ByteUnit

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.common Util

.. java:import:: com.nekoscape.android.ntc.data.operator TrafficManager

.. java:import:: com.nekoscape.android.ntc.service.task NotificationTask

.. java:import:: com.nekoscape.android.ntc.service.task TrafficAlarmTask

TrafficCheckService
===================

.. java:package:: com.nekoscape.android.ntc.service
   :noindex:

.. java:type:: public class TrafficCheckService extends BasePeriodicService

Methods
-------
changeNotificationSetting
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public static void changeNotificationSetting(boolean flag)
   :outertype: TrafficCheckService

   フラグを設定しNoritifationを停止する努力をする

   :param flag:

makeNextPlan
^^^^^^^^^^^^

.. java:method:: @Override public void makeNextPlan()
   :outertype: TrafficCheckService

runTask
^^^^^^^

.. java:method:: @Override protected void runTask()
   :outertype: TrafficCheckService

