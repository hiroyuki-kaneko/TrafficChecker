.. java:import:: android.app Notification

.. java:import:: android.app NotificationManager

.. java:import:: android.app PendingIntent

.. java:import:: android.content Context

.. java:import:: android.content Intent

.. java:import:: android.content SharedPreferences

.. java:import:: android.os PowerManager

.. java:import:: android.preference PreferenceManager

.. java:import:: android.support.v4.app NotificationCompat

.. java:import:: android.support.v4.app NotificationCompat.Builder

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.activity MainActivity

.. java:import:: com.nekoscape.android.ntc.activity R

NotificationTask
================

.. java:package:: com.nekoscape.android.ntc.service.task
   :noindex:

.. java:type:: public class NotificationTask

Methods
-------
createNotify
^^^^^^^^^^^^

.. java:method:: public static void createNotify(Context context, String ssid, String totalSize)
   :outertype: NotificationTask

   :param ssid:
   :param totalSize:

isNotificationFlag
^^^^^^^^^^^^^^^^^^

.. java:method:: public static boolean isNotificationFlag()
   :outertype: NotificationTask

setNotificationFlag
^^^^^^^^^^^^^^^^^^^

.. java:method:: public static void setNotificationFlag(boolean notificationFlag)
   :outertype: NotificationTask

