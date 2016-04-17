.. java:import:: android.content Context

.. java:import:: android.net ConnectivityManager

.. java:import:: android.net NetworkInfo

.. java:import:: android.net.wifi WifiManager

.. java:import:: android.util Log

NetworkStatus
=============

.. java:package:: com.nekoscape.android.ntc.common
   :noindex:

.. java:type:: public class NetworkStatus

Constructors
------------
NetworkStatus
^^^^^^^^^^^^^

.. java:constructor:: public NetworkStatus(Context context)
   :outertype: NetworkStatus

   接続中のネットワークの種別を取得するコンストラクタ

   :param context:

NetworkStatus
^^^^^^^^^^^^^

.. java:constructor:: public NetworkStatus(int type, int subtype, String ssid)
   :outertype: NetworkStatus

   ネットワークのタイプを指定した時に呼び出すコンストラクタ

   :param type:
   :param subtype:
   :param ssid:

Methods
-------
getSSID
^^^^^^^

.. java:method:: public String getSSID()
   :outertype: NetworkStatus

getSubtype
^^^^^^^^^^

.. java:method:: public int getSubtype()
   :outertype: NetworkStatus

getType
^^^^^^^

.. java:method:: public int getType()
   :outertype: NetworkStatus

isConnected
^^^^^^^^^^^

.. java:method:: public boolean isConnected()
   :outertype: NetworkStatus

isWifiConnected
^^^^^^^^^^^^^^^

.. java:method:: public boolean isWifiConnected()
   :outertype: NetworkStatus

toString
^^^^^^^^

.. java:method:: @Override public String toString()
   :outertype: NetworkStatus

