.. java:import:: android.app ProgressDialog

.. java:import:: android.content Context

.. java:import:: android.content Intent

.. java:import:: android.os AsyncTask

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.activity MainActivity

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.data.operator TrafficManager

UpgradeTrafficdataTable
=======================

.. java:package:: com.nekoscape.android.ntc.common
   :noindex:

.. java:type:: public class UpgradeTrafficdataTable extends AsyncTask<Void, Void, Boolean>

Fields
------
dialog
^^^^^^

.. java:field::  ProgressDialog dialog
   :outertype: UpgradeTrafficdataTable

Constructors
------------
UpgradeTrafficdataTable
^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public UpgradeTrafficdataTable(Context context, ProgressDialog dialog, TrafficManager manager)
   :outertype: UpgradeTrafficdataTable

Methods
-------
dismissDialog
^^^^^^^^^^^^^

.. java:method:: public void dismissDialog()
   :outertype: UpgradeTrafficdataTable

doInBackground
^^^^^^^^^^^^^^

.. java:method:: @Override protected Boolean doInBackground(Void... params)
   :outertype: UpgradeTrafficdataTable

onPostExecute
^^^^^^^^^^^^^

.. java:method:: @Override protected void onPostExecute(Boolean result)
   :outertype: UpgradeTrafficdataTable

onPreExecute
^^^^^^^^^^^^

.. java:method:: @Override protected void onPreExecute()
   :outertype: UpgradeTrafficdataTable

