.. java:import:: android.content BroadcastReceiver

.. java:import:: android.content Context

.. java:import:: android.content Intent

BaseOnBroadcastReciver
======================

.. java:package:: com.nekoscape.android.ntc.intent.reciver
   :noindex:

.. java:type:: public abstract class BaseOnBroadcastReciver extends BroadcastReceiver

Methods
-------
execute
^^^^^^^

.. java:method:: public abstract void execute(Context context)
   :outertype: BaseOnBroadcastReciver

   BroadcastIntentを受信した際に呼び出す処理

   :param context:

getBroadcastIntent
^^^^^^^^^^^^^^^^^^

.. java:method:: public abstract String getBroadcastIntent()
   :outertype: BaseOnBroadcastReciver

   BroadcastIntentの文字列を返却する。一致するインテントを受信した場合、同クラスのexecuteメソッドが呼び出される。

   :return: BroadcatIntentの文字列

onReceive
^^^^^^^^^

.. java:method:: @Override public void onReceive(Context context, Intent intent)
   :outertype: BaseOnBroadcastReciver

