.. java:import:: android.app AlarmManager

.. java:import:: android.app PendingIntent

.. java:import:: android.app Service

.. java:import:: android.content Context

.. java:import:: android.content Intent

.. java:import:: android.os Binder

.. java:import:: android.os Handler

.. java:import:: android.os IBinder

.. java:import:: android.os Parcel

.. java:import:: android.os RemoteException

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.common Util

BasePeriodicService
===================

.. java:package:: com.nekoscape.android.ntc.service
   :noindex:

.. java:type:: public abstract class BasePeriodicService extends Service

Fields
------
binder
^^^^^^

.. java:field:: protected final IBinder binder
   :outertype: BasePeriodicService

Methods
-------
execTask
^^^^^^^^

.. java:method:: protected void execTask()
   :outertype: BasePeriodicService

   定期実行したいタスクの中身（１回分） タスクの実行が完了したら，次回の実行計画を立てること。

isNotificationFlag
^^^^^^^^^^^^^^^^^^

.. java:method:: public static boolean isNotificationFlag()
   :outertype: BasePeriodicService

makeNextPlan
^^^^^^^^^^^^

.. java:method:: protected abstract void makeNextPlan()
   :outertype: BasePeriodicService

   次回の実行計画を立てる。

onBind
^^^^^^

.. java:method:: @Override public IBinder onBind(Intent intent)
   :outertype: BasePeriodicService

onCreate
^^^^^^^^

.. java:method:: @Override public void onCreate()
   :outertype: BasePeriodicService

onStartCommand
^^^^^^^^^^^^^^

.. java:method:: @Override public int onStartCommand(Intent intent, int flags, int startId)
   :outertype: BasePeriodicService

runTask
^^^^^^^

.. java:method:: protected abstract void runTask()
   :outertype: BasePeriodicService

   実行するタスクを記載する

scheduleNextTime
^^^^^^^^^^^^^^^^

.. java:method:: public void scheduleNextTime(int waitTime)
   :outertype: BasePeriodicService

   サービスの次回の起動を予約

   :param waitTime:

setNotificationFlag
^^^^^^^^^^^^^^^^^^^

.. java:method:: public static void setNotificationFlag(boolean notificationFlag)
   :outertype: BasePeriodicService

startResident
^^^^^^^^^^^^^

.. java:method:: public BasePeriodicService startResident(Context context)
   :outertype: BasePeriodicService

   常駐を開始

stopResident
^^^^^^^^^^^^

.. java:method:: public void stopResident(Context context)
   :outertype: BasePeriodicService

   サービスの定期実行を解除し，サービスを停止

