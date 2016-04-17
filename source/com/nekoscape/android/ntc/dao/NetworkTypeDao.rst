.. java:import:: android.database Cursor

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.database.sqlite SQLiteStatement

.. java:import:: de.greenrobot.dao AbstractDao

.. java:import:: de.greenrobot.dao Property

.. java:import:: de.greenrobot.dao.internal DaoConfig

NetworkTypeDao
==============

.. java:package:: com.nekoscape.android.ntc.dao
   :noindex:

.. java:type:: public class NetworkTypeDao extends AbstractDao<NetworkType, Long>

   DAO for table NetworkTypeTable.

Fields
------
TABLENAME
^^^^^^^^^

.. java:field:: public static final String TABLENAME
   :outertype: NetworkTypeDao

Constructors
------------
NetworkTypeDao
^^^^^^^^^^^^^^

.. java:constructor:: public NetworkTypeDao(DaoConfig config)
   :outertype: NetworkTypeDao

NetworkTypeDao
^^^^^^^^^^^^^^

.. java:constructor:: public NetworkTypeDao(DaoConfig config, DaoSession daoSession)
   :outertype: NetworkTypeDao

Methods
-------
bindValues
^^^^^^^^^^

.. java:method:: @Override protected void bindValues(SQLiteStatement stmt, NetworkType entity)
   :outertype: NetworkTypeDao

createTable
^^^^^^^^^^^

.. java:method:: public static void createTable(SQLiteDatabase db, boolean ifNotExists)
   :outertype: NetworkTypeDao

   Creates the underlying database table.

dropTable
^^^^^^^^^

.. java:method:: public static void dropTable(SQLiteDatabase db, boolean ifExists)
   :outertype: NetworkTypeDao

   Drops the underlying database table.

getKey
^^^^^^

.. java:method:: @Override public Long getKey(NetworkType entity)
   :outertype: NetworkTypeDao

isEntityUpdateable
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override protected boolean isEntityUpdateable()
   :outertype: NetworkTypeDao

readEntity
^^^^^^^^^^

.. java:method:: @Override public NetworkType readEntity(Cursor cursor, int offset)
   :outertype: NetworkTypeDao

readEntity
^^^^^^^^^^

.. java:method:: @Override public void readEntity(Cursor cursor, NetworkType entity, int offset)
   :outertype: NetworkTypeDao

readKey
^^^^^^^

.. java:method:: @Override public Long readKey(Cursor cursor, int offset)
   :outertype: NetworkTypeDao

updateKeyAfterInsert
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override protected Long updateKeyAfterInsert(NetworkType entity, long rowId)
   :outertype: NetworkTypeDao

