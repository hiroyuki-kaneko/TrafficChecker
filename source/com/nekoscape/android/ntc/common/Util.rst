.. java:import:: java.util Arrays

.. java:import:: java.util Date

.. java:import:: java.util HashSet

.. java:import:: java.util List

.. java:import:: java.util Set

.. java:import:: java.util.concurrent TimeUnit

.. java:import:: android.content Context

.. java:import:: android.content SharedPreferences

.. java:import:: android.content SharedPreferences.Editor

.. java:import:: android.net ConnectivityManager

.. java:import:: android.preference PreferenceManager

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.dao NetworkType

.. java:import:: com.nekoscape.android.ntc.dao NetworkTypeDao

.. java:import:: com.nekoscape.android.ntc.dao NetworkTypeDao.Properties

.. java:import:: com.nekoscape.android.ntc.activity.pref PreferenceConstant

.. java:import:: com.nekoscape.android.ntc.data.operator CompareSsid

Util
====

.. java:package:: com.nekoscape.android.ntc.common
   :noindex:

.. java:type:: public class Util

Methods
-------
canSendAnalystics
^^^^^^^^^^^^^^^^^

.. java:method:: public static boolean canSendAnalystics(Context context)
   :outertype: Util

changeNetworkPreference
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public static void changeNetworkPreference(Context context)
   :outertype: Util

createNetworkType
^^^^^^^^^^^^^^^^^

.. java:method:: public static NetworkType createNetworkType(NetworkStatus status)
   :outertype: Util

getAlarmThreshold
^^^^^^^^^^^^^^^^^

.. java:method:: public static int getAlarmThreshold(Context context)
   :outertype: Util

getNetworkSsidList
^^^^^^^^^^^^^^^^^^

.. java:method:: public static List<String> getNetworkSsidList(Context context)
   :outertype: Util

getNetworkStatus
^^^^^^^^^^^^^^^^

.. java:method:: public static NetworkStatus getNetworkStatus(Context context)
   :outertype: Util

ignoreSsid
^^^^^^^^^^

.. java:method:: public static boolean ignoreSsid(int type, String ssid)
   :outertype: Util

serchNetworkType
^^^^^^^^^^^^^^^^

.. java:method:: public static NetworkType serchNetworkType(NetworkTypeDao networkTypeDao, NetworkStatus status)
   :outertype: Util

waitingTime
^^^^^^^^^^^

.. java:method:: public static long waitingTime(int waitTime)
   :outertype: Util

