.. java:import:: java.util ArrayList

.. java:import:: java.util Calendar

.. java:import:: java.util List

.. java:import:: android.content Context

.. java:import:: android.database Cursor

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.activity R

.. java:import:: com.nekoscape.android.ntc.common DataType

.. java:import:: com.nekoscape.android.ntc.common NetworkStatus

.. java:import:: com.nekoscape.android.ntc.common Util

.. java:import:: com.nekoscape.android.ntc.data.object SearchDatas

.. java:import:: com.nekoscape.android.ntc.data.object TransferData

UserDataManager
===============

.. java:package:: com.nekoscape.android.ntc.data.operator
   :noindex:

.. java:type:: public class UserDataManager

Constructors
------------
UserDataManager
^^^^^^^^^^^^^^^

.. java:constructor:: public UserDataManager(Context context)
   :outertype: UserDataManager

Methods
-------
deleteOldData
^^^^^^^^^^^^^

.. java:method:: public void deleteOldData()
   :outertype: UserDataManager

getData
^^^^^^^

.. java:method:: public GraphDataIterator getData()
   :outertype: UserDataManager

getSSIDList
^^^^^^^^^^^

.. java:method:: public NetworkStatus[] getSSIDList()
   :outertype: UserDataManager

getSearchData
^^^^^^^^^^^^^

.. java:method:: public SearchDatas getSearchData(DataType type, Calendar calendar)
   :outertype: UserDataManager

getSearchData
^^^^^^^^^^^^^

.. java:method:: public SearchDatas getSearchData(DataType day, Calendar calendar, int type, int subtype, String ssid)
   :outertype: UserDataManager

regsitData
^^^^^^^^^^

.. java:method:: public void regsitData(TransferData data)
   :outertype: UserDataManager

search
^^^^^^

.. java:method:: @Deprecated public TransferData search(TransferData userData)
   :outertype: UserDataManager

upgradeTrafficdata
^^^^^^^^^^^^^^^^^^

.. java:method:: public void upgradeTrafficdata()
   :outertype: UserDataManager

