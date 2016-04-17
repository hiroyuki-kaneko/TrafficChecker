.. java:import:: android.content Context

.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: android.database.sqlite SQLiteDatabase.CursorFactory

.. java:import:: android.database.sqlite SQLiteOpenHelper

.. java:import:: android.util Log

.. java:import:: de.greenrobot.dao AbstractDaoMaster

.. java:import:: de.greenrobot.dao.identityscope IdentityScopeType

DaoMaster.OpenHelper
====================

.. java:package:: com.nekoscape.android.ntc.dao
   :noindex:

.. java:type:: public static abstract class OpenHelper extends SQLiteOpenHelper
   :outertype: DaoMaster

Constructors
------------
OpenHelper
^^^^^^^^^^

.. java:constructor:: public OpenHelper(Context context, String name, CursorFactory factory)
   :outertype: DaoMaster.OpenHelper

Methods
-------
onCreate
^^^^^^^^

.. java:method:: @Override public void onCreate(SQLiteDatabase db)
   :outertype: DaoMaster.OpenHelper

