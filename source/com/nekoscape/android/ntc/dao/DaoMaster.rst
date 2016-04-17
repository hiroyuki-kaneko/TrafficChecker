.. java:import:: android.content Context

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.database.sqlite SQLiteDatabase.CursorFactory

.. java:import:: android.database.sqlite SQLiteOpenHelper

.. java:import:: android.util Log

.. java:import:: de.greenrobot.dao AbstractDaoMaster

.. java:import:: de.greenrobot.dao.identityscope IdentityScopeType

DaoMaster
=========

.. java:package:: com.nekoscape.android.ntc.dao
   :noindex:

.. java:type:: public class DaoMaster extends AbstractDaoMaster

   Master of DAO (schema version 5): knows all DAOs.

Fields
------
SCHEMA_VERSION
^^^^^^^^^^^^^^

.. java:field:: public static final int SCHEMA_VERSION
   :outertype: DaoMaster

Constructors
------------
DaoMaster
^^^^^^^^^

.. java:constructor:: public DaoMaster(SQLiteDatabase db)
   :outertype: DaoMaster

Methods
-------
createAllTables
^^^^^^^^^^^^^^^

.. java:method:: public static void createAllTables(SQLiteDatabase db, boolean ifNotExists)
   :outertype: DaoMaster

   Creates underlying database table using DAOs.

dropAllTables
^^^^^^^^^^^^^

.. java:method:: public static void dropAllTables(SQLiteDatabase db, boolean ifExists)
   :outertype: DaoMaster

   Drops underlying database table using DAOs.

newSession
^^^^^^^^^^

.. java:method:: public DaoSession newSession()
   :outertype: DaoMaster

newSession
^^^^^^^^^^

.. java:method:: public DaoSession newSession(IdentityScopeType type)
   :outertype: DaoMaster

