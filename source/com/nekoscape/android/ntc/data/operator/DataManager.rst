.. java:import:: java.util ArrayList

.. java:import:: java.util Calendar

.. java:import:: java.util List

.. java:import:: java.util Locale

.. java:import:: android.content Context

.. java:import:: android.database Cursor

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.database.sqlite SQLiteDatabase.CursorFactory

.. java:import:: android.database.sqlite SQLiteOpenHelper

.. java:import:: android.database.sqlite SQLiteStatement

.. java:import:: com.nekoscape.android.ntc.common DataType

DataManager
===========

.. java:package:: com.nekoscape.android.ntc.data.operator
   :noindex:

.. java:type:: public class DataManager extends SQLiteOpenHelper

   グラフ作成を視野に入れたデータ格納クラス。 以下のテーブルを用意する。 ・通信タイプ管理用テーブル（NetworkTypeTable)  ・生データ管理用テーブル(CommunicationTable) １〜60秒間隔のデータをとりあえず入れる場所  ・圧縮用テーブル  ー　1時間用（HoursTable)　1年保持（データ保持期間は選択次第）

   :author: someone

Fields
------
DATABASE_NAME
^^^^^^^^^^^^^

.. java:field:: public static final String DATABASE_NAME
   :outertype: DataManager

TABLE_COMMUNICATION
^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final String TABLE_COMMUNICATION
   :outertype: DataManager

TABLE_MINUTES
^^^^^^^^^^^^^

.. java:field:: public static final String TABLE_MINUTES
   :outertype: DataManager

TABLE_NETWORK_TYPE
^^^^^^^^^^^^^^^^^^

.. java:field:: public static final String TABLE_NETWORK_TYPE
   :outertype: DataManager

TALBE_HOUR
^^^^^^^^^^

.. java:field:: public static final String TALBE_HOUR
   :outertype: DataManager

Methods
-------
expiration
^^^^^^^^^^

.. java:method:: public void expiration(String tableName, int days)
   :outertype: DataManager

   保存期限の切れたデータの削除

   :param days:

getInstance
^^^^^^^^^^^

.. java:method:: public static synchronized DataManager getInstance(Context context)
   :outertype: DataManager

   データ管理用インスタンスを生成します。

   :param context:

getNetworkID
^^^^^^^^^^^^

.. java:method:: public int getNetworkID(int type, int subtype, String ssid)
   :outertype: DataManager

   ネットワークの主キーを取得する。なければ追加、あれば取る。

   :param type:
   :param subtype:
   :param ssid:

onCreate
^^^^^^^^

.. java:method:: @Override public void onCreate(SQLiteDatabase db)
   :outertype: DataManager

onUpgrade
^^^^^^^^^

.. java:method:: @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
   :outertype: DataManager

printNetworkIDs
^^^^^^^^^^^^^^^

.. java:method:: public void printNetworkIDs()
   :outertype: DataManager

registCommunicationTable
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public void registCommunicationTable(int id, long msend, long mrecv, long osend, long orecv)
   :outertype: DataManager

search
^^^^^^

.. java:method:: public Cursor search(DataType type, Calendar calendar)
   :outertype: DataManager

search
^^^^^^

.. java:method:: public Cursor search(DataType datatype, Calendar calendar, int type, int subtype, String ssid)
   :outertype: DataManager

searchSample
^^^^^^^^^^^^

.. java:method:: public Cursor searchSample()
   :outertype: DataManager

searchSsid
^^^^^^^^^^

.. java:method:: public Cursor searchSsid()
   :outertype: DataManager

