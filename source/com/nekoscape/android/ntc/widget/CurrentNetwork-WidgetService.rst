.. java:import:: android.app AlarmManager

.. java:import:: android.app PendingIntent

.. java:import:: android.app Service

.. java:import:: android.appwidget AppWidgetManager

.. java:import:: android.appwidget AppWidgetProvider

.. java:import:: android.content ComponentName

.. java:import:: android.content Context

.. java:import:: android.content Intent

.. java:import:: android.content SharedPreferences

.. java:import:: android.graphics Bitmap

.. java:import:: android.graphics Canvas

.. java:import:: android.graphics Color

.. java:import:: android.graphics Paint

.. java:import:: android.graphics RectF

.. java:import:: android.os IBinder

.. java:import:: android.os PowerManager

.. java:import:: android.preference PreferenceManager

.. java:import:: android.util Log

.. java:import:: android.widget RemoteViews

.. java:import:: com.nekoscape.android.ntc.activity MainActivity

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common ByteUnit

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.common TextDrawHelper

.. java:import:: com.nekoscape.android.ntc.common Util

.. java:import:: com.nekoscape.android.ntc.data.operator TrafficManager

.. java:import:: java.text NumberFormat

CurrentNetwork.WidgetService
============================

.. java:package:: com.nekoscape.android.ntc.widget
   :noindex:

.. java:type:: public static class WidgetService extends Service
   :outertype: CurrentNetwork

Methods
-------
onBind
^^^^^^

.. java:method:: @Override public IBinder onBind(Intent intent)
   :outertype: CurrentNetwork.WidgetService

onDestroy
^^^^^^^^^

.. java:method:: @Override public void onDestroy()
   :outertype: CurrentNetwork.WidgetService

onStartCommand
^^^^^^^^^^^^^^

.. java:method:: @Override public int onStartCommand(Intent intent, int flags, int startId)
   :outertype: CurrentNetwork.WidgetService

