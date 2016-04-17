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

CurrentNetwork
==============

.. java:package:: com.nekoscape.android.ntc.widget
   :noindex:

.. java:type:: public abstract class CurrentNetwork extends AppWidgetProvider

Methods
-------
getHight
^^^^^^^^

.. java:method:: abstract int getHight()
   :outertype: CurrentNetwork

getWidth
^^^^^^^^

.. java:method:: abstract int getWidth()
   :outertype: CurrentNetwork

onDisabled
^^^^^^^^^^

.. java:method:: @Override public void onDisabled(Context context)
   :outertype: CurrentNetwork

onUpdate
^^^^^^^^

.. java:method:: @Override public void onUpdate(Context context, AppWidgetManager awm, int[] awi)
   :outertype: CurrentNetwork

