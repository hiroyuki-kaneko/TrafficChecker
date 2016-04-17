.. java:import:: android.database Cursor

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.database.sqlite SQLiteStatement

.. java:import:: de.greenrobot.dao AbstractDao

.. java:import:: de.greenrobot.dao Property

.. java:import:: de.greenrobot.dao.internal DaoConfig

CommunicationDao
================

.. java:package:: com.nekoscape.android.ntc.dao
   :noindex:

.. java:type:: public class CommunicationDao extends AbstractDao<Communication, Long>

   DAO for table CommunicationTable.

Fields
------
TABLENAME
^^^^^^^^^

.. java:field:: public static final String TABLENAME
   :outertype: CommunicationDao

Constructors
------------
CommunicationDao
^^^^^^^^^^^^^^^^

.. java:constructor:: public CommunicationDao(DaoConfig config)
   :outertype: CommunicationDao

CommunicationDao
^^^^^^^^^^^^^^^^

.. java:constructor:: public CommunicationDao(DaoConfig config, DaoSession daoSession)
   :outertype: CommunicationDao

Methods
-------
bindValues
^^^^^^^^^^

.. java:method:: @Override protected void bindValues(SQLiteStatement stmt, Communication entity)
   :outertype: CommunicationDao

createTable
^^^^^^^^^^^

.. java:method:: public static void createTable(SQLiteDatabase db, boolean ifNotExists)
   :outertype: CommunicationDao

   Creates the underlying database table.

dropTable
^^^^^^^^^

.. java:method:: public static void dropTable(SQLiteDatabase db, boolean ifExists)
   :outertype: CommunicationDao

   Drops the underlying database table.

getKey
^^^^^^

.. java:method:: @Override public Long getKey(Communication entity)
   :outertype: CommunicationDao

isEntityUpdateable
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override protected boolean isEntityUpdateable()
   :outertype: CommunicationDao

readEntity
^^^^^^^^^^

.. java:method:: @Override public Communication readEntity(Cursor cursor, int offset)
   :outertype: CommunicationDao

readEntity
^^^^^^^^^^

.. java:method:: @Override public void readEntity(Cursor cursor, Communication entity, int offset)
   :outertype: CommunicationDao

readKey
^^^^^^^

.. java:method:: @Override public Long readKey(Cursor cursor, int offset)
   :outertype: CommunicationDao

updateKeyAfterInsert
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override protected Long updateKeyAfterInsert(Communication entity, long rowId)
   :outertype: CommunicationDao

