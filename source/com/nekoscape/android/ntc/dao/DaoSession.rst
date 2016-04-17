.. java:import:: android.database.sqlite SQLiteDatabase

.. java:import:: java.util Map

.. java:import:: de.greenrobot.dao AbstractDao

.. java:import:: de.greenrobot.dao AbstractDaoSession

.. java:import:: de.greenrobot.dao.identityscope IdentityScopeType

.. java:import:: de.greenrobot.dao.internal DaoConfig

DaoSession
==========

.. java:package:: com.nekoscape.android.ntc.dao
   :noindex:

.. java:type:: public class DaoSession extends AbstractDaoSession

   {@inheritDoc}

   **See also:** :java:ref:`de.greenrobot.dao.AbstractDaoSession`

Constructors
------------
DaoSession
^^^^^^^^^^

.. java:constructor:: public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap)
   :outertype: DaoSession

Methods
-------
clear
^^^^^

.. java:method:: public void clear()
   :outertype: DaoSession

getCommunicationDao
^^^^^^^^^^^^^^^^^^^

.. java:method:: public CommunicationDao getCommunicationDao()
   :outertype: DaoSession

getHourDao
^^^^^^^^^^

.. java:method:: public HourDao getHourDao()
   :outertype: DaoSession

getNetworkTypeDao
^^^^^^^^^^^^^^^^^

.. java:method:: public NetworkTypeDao getNetworkTypeDao()
   :outertype: DaoSession

