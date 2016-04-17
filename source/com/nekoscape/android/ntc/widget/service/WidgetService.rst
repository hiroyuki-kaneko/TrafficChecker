.. java:import:: java.text NumberFormat

.. java:import:: android.app AlarmManager

.. java:import:: android.app PendingIntent

.. java:import:: android.app Service

.. java:import:: android.appwidget AppWidgetManager

.. java:import:: android.content ComponentName

.. java:import:: android.content Context

.. java:import:: android.content Intent

.. java:import:: android.graphics Bitmap

.. java:import:: android.graphics Canvas

.. java:import:: android.graphics Paint

.. java:import:: android.graphics RectF

.. java:import:: android.os IBinder

.. java:import:: android.widget RemoteViews

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common ByteUnit

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.common TextDrawHelper

.. java:import:: com.nekoscape.android.ntc.common Util

.. java:import:: com.nekoscape.android.ntc.data.operator TrafficManager

.. java:import:: com.nekoscape.android.ntc.widget CurrentNetwork_3x1

WidgetService
=============

.. java:package:: com.nekoscape.android.ntc.widget.service
   :noindex:

.. java:type:: public abstract class WidgetService extends Service

Methods
-------
onBind
^^^^^^

.. java:method:: @Override public IBinder onBind(Intent intent)
   :outertype: WidgetService

onDestroy
^^^^^^^^^

.. java:method:: @Override public void onDestroy()
   :outertype: WidgetService

onStartCommand
^^^^^^^^^^^^^^

.. java:method:: @Override public int onStartCommand(Intent intent, int flags, int startId)
   :outertype: WidgetService

