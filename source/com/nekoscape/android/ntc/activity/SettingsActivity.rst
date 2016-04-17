.. java:import:: android.annotation SuppressLint

.. java:import:: android.annotation TargetApi

.. java:import:: android.app Activity

.. java:import:: android.content SharedPreferences

.. java:import:: android.content SharedPreferences.OnSharedPreferenceChangeListener

.. java:import:: android.os Build

.. java:import:: android.os Bundle

.. java:import:: android.preference ListPreference

.. java:import:: android.preference PreferenceActivity

.. java:import:: android.preference PreferenceFragment

.. java:import:: android.util Log

.. java:import:: com.google.analytics.tracking.android EasyTracker

.. java:import:: com.nekoscape.android.ntc.activity.pref PreferenceChangedUtil

.. java:import:: com.nekoscape.android.ntc.activity.pref PreferenceNetworkTypeList

.. java:import:: com.nekoscape.android.ntc.common Util

SettingsActivity
================

.. java:package:: com.nekoscape.android.ntc.activity
   :noindex:

.. java:type:: public class SettingsActivity extends Activity

   A \ :java:ref:`PreferenceActivity`\  that presents a set of application settings. On handset devices, settings are presented as a single list. On tablets, settings are split by category, with category headers shown to the left of the list of settings.

   See \ `Android Design: Settings <http://developer.android.com/design/patterns/settings.html>`_\  for design guidelines and the \ `Settings API Guide <http://developer.android.com/guide/topics/ui/settings.html>`_\  for more information on developing a Settings UI.

Methods
-------
onPostCreate
^^^^^^^^^^^^

.. java:method:: @SuppressLint @Override protected void onPostCreate(Bundle savedInstanceState)
   :outertype: SettingsActivity

onStart
^^^^^^^

.. java:method:: @Override public void onStart()
   :outertype: SettingsActivity

   {@inheritDoc}

onStop
^^^^^^

.. java:method:: @Override public void onStop()
   :outertype: SettingsActivity

