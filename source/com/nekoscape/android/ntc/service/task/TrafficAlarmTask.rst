.. java:import:: java.text NumberFormat

.. java:import:: android.app Notification

.. java:import:: android.app NotificationManager

.. java:import:: android.app PendingIntent

.. java:import:: android.content Context

.. java:import:: android.content Intent

.. java:import:: android.support.v4.app NotificationCompat

.. java:import:: android.support.v4.app NotificationCompat.Builder

.. java:import:: com.nekoscape.android.ntc.activity MainActivity

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.activity.pref PreferenceConstant

.. java:import:: com.nekoscape.android.ntc.common ByteUnit

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.common Util

TrafficAlarmTask
================

.. java:package:: com.nekoscape.android.ntc.service.task
   :noindex:

.. java:type:: public class TrafficAlarmTask

Methods
-------
createNotify
^^^^^^^^^^^^

.. java:method:: public static void createNotify(Context context, long totalSize)
   :outertype: TrafficAlarmTask

   :param totalSize:

makeAlarm
^^^^^^^^^

.. java:method:: public static void makeAlarm(Context context, NetworkStatus status, long totalTrafficBytes)
   :outertype: TrafficAlarmTask

