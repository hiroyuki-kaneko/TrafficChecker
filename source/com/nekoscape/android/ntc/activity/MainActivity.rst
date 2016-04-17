.. java:import:: android.app.backup BackupManager

.. java:import:: android.content Context

.. java:import:: android.content Intent

.. java:import:: android.content SharedPreferences

.. java:import:: android.content SharedPreferences.Editor

.. java:import:: android.net ConnectivityManager

.. java:import:: android.net Uri

.. java:import:: android.os Build

.. java:import:: android.os Bundle

.. java:import:: android.support.v7.app ActionBar

.. java:import:: android.support.v7.app ActionBarActivity

.. java:import:: android.util Log

.. java:import:: android.view Menu

.. java:import:: android.view MenuInflater

.. java:import:: android.view MenuItem

.. java:import:: android.widget ArrayAdapter

.. java:import:: android.widget TextView

.. java:import:: com.google.analytics.tracking.android EasyTracker

.. java:import:: com.nekoscape.android.ntc.dao Hour

.. java:import:: com.nekoscape.android.ntc.activity.compare CompareActivity

.. java:import:: com.nekoscape.android.ntc.activity.factory PreferenceFactory

.. java:import:: com.nekoscape.android.ntc.activity.history HistoryActivity

.. java:import:: com.nekoscape.android.ntc.activity.pref PreferenceConstant

.. java:import:: com.nekoscape.android.ntc.common ByteUnit

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.common Util

.. java:import:: com.nekoscape.android.ntc.data.operator HistoryHourData

.. java:import:: com.nekoscape.android.ntc.data.operator UserDataManager

.. java:import:: com.nekoscape.android.ntc.service TrafficCheckService

.. java:import:: java.text NumberFormat

MainActivity
============

.. java:package:: com.nekoscape.android.ntc.activity
   :noindex:

.. java:type:: public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener

   通信量を表示するアクティビティ

   :author: someone

Fields
------
MENU_SELECT_CONFIG
^^^^^^^^^^^^^^^^^^

.. java:field:: public static final int MENU_SELECT_CONFIG
   :outertype: MainActivity

MENU_SELECT_GRAPH
^^^^^^^^^^^^^^^^^

.. java:field:: public static final int MENU_SELECT_GRAPH
   :outertype: MainActivity

Methods
-------
getRecvTraffic
^^^^^^^^^^^^^^

.. java:method:: public long getRecvTraffic(NetworkStatus status, Hour hour)
   :outertype: MainActivity

getSendTraffic
^^^^^^^^^^^^^^

.. java:method:: public long getSendTraffic(NetworkStatus status, Hour hour)
   :outertype: MainActivity

onCreate
^^^^^^^^

.. java:method:: @Override public void onCreate(Bundle savedInstanceState)
   :outertype: MainActivity

   アクティビティ作成時に呼び出される

onCreateOptionsMenu
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean onCreateOptionsMenu(Menu menu)
   :outertype: MainActivity

onDestroy
^^^^^^^^^

.. java:method:: @Override public void onDestroy()
   :outertype: MainActivity

onNavigationItemSelected
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean onNavigationItemSelected(int itemPosition, long arg1)
   :outertype: MainActivity

onOptionsItemSelected
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean onOptionsItemSelected(MenuItem item)
   :outertype: MainActivity

onPause
^^^^^^^

.. java:method:: @Override public void onPause()
   :outertype: MainActivity

onResume
^^^^^^^^

.. java:method:: @Override public void onResume()
   :outertype: MainActivity

onStart
^^^^^^^

.. java:method:: @Override public void onStart()
   :outertype: MainActivity

onStop
^^^^^^

.. java:method:: @Override public void onStop()
   :outertype: MainActivity

