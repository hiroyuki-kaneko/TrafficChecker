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

SettingsActivity.SettingPreferencesFragment
===========================================

.. java:package:: com.nekoscape.android.ntc.activity
   :noindex:

.. java:type:: @SuppressLint public static class SettingPreferencesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener
   :outertype: SettingsActivity

Methods
-------
onCreate
^^^^^^^^

.. java:method:: @SuppressLint @Override public void onCreate(Bundle savedInstanceState)
   :outertype: SettingsActivity.SettingPreferencesFragment

onResume
^^^^^^^^

.. java:method:: @SuppressLint @Override public void onResume()
   :outertype: SettingsActivity.SettingPreferencesFragment

onSharedPreferenceChanged
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @SuppressLint @Override public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
   :outertype: SettingsActivity.SettingPreferencesFragment

