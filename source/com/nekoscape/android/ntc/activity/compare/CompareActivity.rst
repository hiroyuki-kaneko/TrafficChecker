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

.. java:import:: com.google.analytics.tracking.android EasyTracker

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common DummyTabFactory

.. java:import:: com.nekoscape.android.ntc.common Util

CompareActivity
===============

.. java:package:: com.nekoscape.android.ntc.activity.compare
   :noindex:

.. java:type:: public class CompareActivity extends FragmentActivity implements TabHost.OnTabChangeListener

Fields
------
viewPager
^^^^^^^^^

.. java:field::  ViewPager viewPager
   :outertype: CompareActivity

Methods
-------
onCreate
^^^^^^^^

.. java:method:: @Override protected void onCreate(Bundle savedInstanceState)
   :outertype: CompareActivity

onCreateOptionsMenu
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean onCreateOptionsMenu(Menu menu)
   :outertype: CompareActivity

onOptionsItemSelected
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean onOptionsItemSelected(MenuItem item)
   :outertype: CompareActivity

onResume
^^^^^^^^

.. java:method:: @Override public void onResume()
   :outertype: CompareActivity

onStart
^^^^^^^

.. java:method:: @Override public void onStart()
   :outertype: CompareActivity

onStop
^^^^^^

.. java:method:: @Override public void onStop()
   :outertype: CompareActivity

onTabChanged
^^^^^^^^^^^^

.. java:method:: @Override public void onTabChanged(String tabId)
   :outertype: CompareActivity

