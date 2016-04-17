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

SettingsActivity.GeneralPreferenceFragment
==========================================

.. java:package:: com.nekoscape.android.ntc.activity
   :noindex:

.. java:type:: @TargetApi public static class GeneralPreferenceFragment extends PreferenceFragment
   :outertype: SettingsActivity

   This fragment shows general preferences only. It is used when the activity is showing a two-pane settings UI.

Methods
-------
onCreate
^^^^^^^^

.. java:method:: @Override public void onCreate(Bundle savedInstanceState)
   :outertype: SettingsActivity.GeneralPreferenceFragment

