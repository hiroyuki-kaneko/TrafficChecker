.. java:import:: android.content Context

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.database.sqlite SQLiteDatabase.CursorFactory

.. java:import:: android.database.sqlite SQLiteOpenHelper

.. java:import:: android.util Log

.. java:import:: de.greenrobot.dao AbstractDaoMaster

.. java:import:: de.greenrobot.dao.identityscope IdentityScopeType

DaoMaster.DevOpenHelper
=======================

.. java:package:: com.nekoscape.android.ntc.dao
   :noindex:

.. java:type:: public static class DevOpenHelper extends OpenHelper
   :outertype: DaoMaster

   WARNING: Drops all table on Upgrade! Use only during development.

Constructors
------------
DevOpenHelper
^^^^^^^^^^^^^

.. java:constructor:: public DevOpenHelper(Context context, String name, CursorFactory factory)
   :outertype: DaoMaster.DevOpenHelper

Methods
-------
onUpgrade
^^^^^^^^^

.. java:method:: @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
   :outertype: DaoMaster.DevOpenHelper

