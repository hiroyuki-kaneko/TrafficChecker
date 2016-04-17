.. java:import:: android.annotation TargetApi

.. java:import:: android.os Build

.. java:import:: android.os Bundle

.. java:import:: android.support.v4.app FragmentActivity

.. java:import:: android.support.v4.app NavUtils

.. java:import:: android.support.v4.view ViewPager

.. java:import:: android.util Log

.. java:import:: android.view Menu

.. java:import:: android.view MenuItem

.. java:import:: android.widget TabHost

.. java:import:: android.widget TabHost.TabSpec

.. java:import:: android.widget TextView

.. java:import:: com.google.analytics.tracking.android EasyTracker

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common DummyTabFactory

.. java:import:: com.nekoscape.android.ntc.common Util

HistoryActivity
===============

.. java:package:: com.nekoscape.android.ntc.activity.history
   :noindex:

.. java:type:: public class HistoryActivity extends FragmentActivity implements TabHost.OnTabChangeListener

Methods
-------
onCreate
^^^^^^^^

.. java:method:: @Override protected void onCreate(Bundle savedInstanceState)
   :outertype: HistoryActivity

onCreateOptionsMenu
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean onCreateOptionsMenu(Menu menu)
   :outertype: HistoryActivity

onOptionsItemSelected
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean onOptionsItemSelected(MenuItem item)
   :outertype: HistoryActivity

onResume
^^^^^^^^

.. java:method:: @Override public void onResume()
   :outertype: HistoryActivity

onStart
^^^^^^^

.. java:method:: @Override public void onStart()
   :outertype: HistoryActivity

onStop
^^^^^^

.. java:method:: @Override public void onStop()
   :outertype: HistoryActivity

onTabChanged
^^^^^^^^^^^^

.. java:method:: @Override public void onTabChanged(String tabId)
   :outertype: HistoryActivity

