.. java:import:: android.content Context

.. java:import:: android.content SharedPreferences

.. java:import:: android.content SharedPreferences.Editor

.. java:import:: android.preference PreferenceManager

PreferenceConstant
==================

.. java:package:: com.nekoscape.android.ntc.activity.pref
   :noindex:

.. java:type:: public enum PreferenceConstant

   Preferenceのデータ取得、設定はここでやることが目標。設定はまずはPrivateからやろう

   :author: someone

Enum Constants
--------------
ALARM_TARGET_SSID
^^^^^^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant ALARM_TARGET_SSID
   :outertype: PreferenceConstant

ALARM_TRAFFIC_SWITCH
^^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant ALARM_TRAFFIC_SWITCH
   :outertype: PreferenceConstant

ALARM_TRAFFIC_TODAY_NOTIFICATION
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant ALARM_TRAFFIC_TODAY_NOTIFICATION
   :outertype: PreferenceConstant

ALARM_TRAFFIC_VIBRATION
^^^^^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant ALARM_TRAFFIC_VIBRATION
   :outertype: PreferenceConstant

ALRAM_TRAFFIC_SIZE
^^^^^^^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant ALRAM_TRAFFIC_SIZE
   :outertype: PreferenceConstant

NETWORK_INIT
^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant NETWORK_INIT
   :outertype: PreferenceConstant

   ネットワークタイプの書き込みの初回登録の実施有無

NETWORK_SSID
^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant NETWORK_SSID
   :outertype: PreferenceConstant

   CONECTIVItY_CHNGED受信前のSSID

NETWORK_SUBTYPE
^^^^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant NETWORK_SUBTYPE
   :outertype: PreferenceConstant

   CONECTIVItY_CHNGED受信前のネットワークサブタイプ

NETWORK_TYPE
^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant NETWORK_TYPE
   :outertype: PreferenceConstant

   CONECTIVItY_CHNGED受信前のネットワークタイプ

SERVICE_WAIT_TIME
^^^^^^^^^^^^^^^^^

.. java:field:: public static final PreferenceConstant SERVICE_WAIT_TIME
   :outertype: PreferenceConstant

   サービス起動までの待機時間

