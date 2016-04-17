.. java:import:: android.database Cursor

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.database.sqlite SQLiteStatement

.. java:import:: de.greenrobot.dao AbstractDao

.. java:import:: de.greenrobot.dao Property

.. java:import:: de.greenrobot.dao.internal DaoConfig

HourDao
=======

.. java:package:: com.nekoscape.android.ntc.dao
   :noindex:

.. java:type:: public class HourDao extends AbstractDao<Hour, Long>

   DAO for table HourTable.

Fields
------
TABLENAME
^^^^^^^^^

.. java:field:: public static final String TABLENAME
   :outertype: HourDao

Constructors
------------
HourDao
^^^^^^^

.. java:constructor:: public HourDao(DaoConfig config)
   :outertype: HourDao

HourDao
^^^^^^^

.. java:constructor:: public HourDao(DaoConfig config, DaoSession daoSession)
   :outertype: HourDao

Methods
-------
bindValues
^^^^^^^^^^

.. java:method:: @Override protected void bindValues(SQLiteStatement stmt, Hour entity)
   :outertype: HourDao

createTable
^^^^^^^^^^^

.. java:method:: public static void createTable(SQLiteDatabase db, boolean ifNotExists)
   :outertype: HourDao

   Creates the underlying database table.

dropTable
^^^^^^^^^

.. java:method:: public static void dropTable(SQLiteDatabase db, boolean ifExists)
   :outertype: HourDao

   Drops the underlying database table.

getKey
^^^^^^

.. java:method:: @Override public Long getKey(Hour entity)
   :outertype: HourDao

isEntityUpdateable
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override protected boolean isEntityUpdateable()
   :outertype: HourDao

readEntity
^^^^^^^^^^

.. java:method:: @Override public Hour readEntity(Cursor cursor, int offset)
   :outertype: HourDao

readEntity
^^^^^^^^^^

.. java:method:: @Override public void readEntity(Cursor cursor, Hour entity, int offset)
   :outertype: HourDao

readKey
^^^^^^^

.. java:method:: @Override public Long readKey(Cursor cursor, int offset)
   :outertype: HourDao

updateKeyAfterInsert
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override protected Long updateKeyAfterInsert(Hour entity, long rowId)
   :outertype: HourDao

