.. java:import:: java.text NumberFormat

.. java:import:: java.util ArrayList

.. java:import:: java.util Calendar

.. java:import:: java.util List

.. java:import:: android.content Intent

.. java:import:: android.graphics Color

.. java:import:: android.net ConnectivityManager

.. java:import:: android.os Bundle

.. java:import:: android.support.v4.app Fragment

.. java:import:: android.view LayoutInflater

.. java:import:: android.view View

.. java:import:: android.view ViewGroup

.. java:import:: android.widget TableLayout

.. java:import:: android.widget TableRow

.. java:import:: android.widget TextView

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.dao Hour

.. java:import:: com.nekoscape.android.ntc.common ByteUnit

.. java:import:: com.nekoscape.android.ntc.common DataType

.. java:import:: com.nekoscape.android.ntc.data.object SearchDatas

.. java:import:: com.nekoscape.android.ntc.data.object SearchDatas.Entity

.. java:import:: com.nekoscape.android.ntc.data.operator UserDataManager

HistoryYearFragment
===================

.. java:package:: com.nekoscape.android.ntc.activity.history
   :noindex:

.. java:type:: public class HistoryYearFragment extends Fragment

Fields
------
tl
^^

.. java:field::  TableLayout tl
   :outertype: HistoryYearFragment

Methods
-------
getInitializedList
^^^^^^^^^^^^^^^^^^

.. java:method:: protected List<Hour> getInitializedList(int num)
   :outertype: HistoryYearFragment

getTitle
^^^^^^^^

.. java:method:: public static String getTitle(int position)
   :outertype: HistoryYearFragment

onCreateView
^^^^^^^^^^^^

.. java:method:: @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
   :outertype: HistoryYearFragment

setPosition
^^^^^^^^^^^

.. java:method:: public HistoryYearFragment setPosition(int i)
   :outertype: HistoryYearFragment

