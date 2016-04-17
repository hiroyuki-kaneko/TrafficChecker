.. java:import:: android.content SharedPreferences

.. java:import:: android.content SharedPreferences.OnSharedPreferenceChangeListener

.. java:import:: android.os Bundle

.. java:import:: android.preference ListPreference

.. java:import:: android.preference PreferenceActivity

.. java:import:: android.util Log

.. java:import:: com.google.analytics.tracking.android EasyTracker

.. java:import:: com.nekoscape.android.ntc.activity.pref PreferenceChangedUtil

.. java:import:: com.nekoscape.android.ntc.activity.pref PreferenceNetworkTypeList

.. java:import:: com.nekoscape.android.ntc.common Util

SettingsLowerActivity
=====================

.. java:package:: com.nekoscape.android.ntc.activity
   :noindex:

.. java:type:: public class SettingsLowerActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener

Methods
-------
onCreate
^^^^^^^^

.. java:method:: @SuppressWarnings @Override public void onCreate(Bundle savedInstanceState)
   :outertype: SettingsLowerActivity

onResume
^^^^^^^^

.. java:method:: @Override public void onResume()
   :outertype: SettingsLowerActivity

onSharedPreferenceChanged
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
   :outertype: SettingsLowerActivity

onStart
^^^^^^^

.. java:method:: @Override public void onStart()
   :outertype: SettingsLowerActivity

onStop
^^^^^^

.. java:method:: @Override public void onStop()
   :outertype: SettingsLowerActivity

