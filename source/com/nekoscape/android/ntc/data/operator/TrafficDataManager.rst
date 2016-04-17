.. java:import:: java.util ArrayList

.. java:import:: java.util Calendar

.. java:import:: java.util Date

.. java:import:: java.util List

.. java:import:: java.util TimeZone

.. java:import:: com.nekoscape.android.ntc.data.object TransferData

.. java:import:: android.content Context

.. java:import:: android.database Cursor

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.database.sqlite SQLiteDatabase.CursorFactory

.. java:import:: android.database.sqlite SQLiteOpenHelper

.. java:import:: android.database.sqlite SQLiteStatement

TrafficDataManager
==================

.. java:package:: com.nekoscape.android.ntc.data.operator
   :noindex:

.. java:type:: public class TrafficDataManager extends SQLiteOpenHelper

Constructors
------------
TrafficDataManager
^^^^^^^^^^^^^^^^^^

.. java:constructor:: public TrafficDataManager(Context context, String name, CursorFactory factory, int version)
   :outertype: TrafficDataManager

Methods
-------
dump
^^^^

.. java:method:: public List<TransferData> dump(String table)
   :outertype: TrafficDataManager

getInstance
^^^^^^^^^^^

.. java:method:: public static synchronized TrafficDataManager getInstance(Context context, String name, CursorFactory factory, int version)
   :outertype: TrafficDataManager

onCreate
^^^^^^^^

.. java:method:: @Override public void onCreate(SQLiteDatabase db)
   :outertype: TrafficDataManager

onUpgrade
^^^^^^^^^

.. java:method:: @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
   :outertype: TrafficDataManager

registUserData
^^^^^^^^^^^^^^

.. java:method:: public void registUserData(TransferData userData)
   :outertype: TrafficDataManager

   指定した値を保存する。

   :param userData: データ記録日（時間は記録されません。）

searchThisMonthUsreData
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public TransferData searchThisMonthUsreData(TransferData userData)
   :outertype: TrafficDataManager

searchUserData
^^^^^^^^^^^^^^

.. java:method:: public TransferData searchUserData(TransferData userData)
   :outertype: TrafficDataManager

upgradeTo3
^^^^^^^^^^

.. java:method:: public void upgradeTo3()
   :outertype: TrafficDataManager

