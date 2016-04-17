.. java:import:: java.util Iterator

.. java:import:: java.util NoSuchElementException

.. java:import:: android.database Cursor

.. java:import:: com.nekoscape.android.ntc.data.object SearchDatas.Entity

SearchDatas
===========

.. java:package:: com.nekoscape.android.ntc.data.object
   :noindex:

.. java:type:: public class SearchDatas implements Iterator<Entity>

Constructors
------------
SearchDatas
^^^^^^^^^^^

.. java:constructor:: public SearchDatas(Cursor cursor)
   :outertype: SearchDatas

Methods
-------
count
^^^^^

.. java:method:: public int count()
   :outertype: SearchDatas

getNetworkID
^^^^^^^^^^^^

.. java:method:: @Deprecated public int getNetworkID()
   :outertype: SearchDatas

hasNext
^^^^^^^

.. java:method:: @Override public boolean hasNext()
   :outertype: SearchDatas

next
^^^^

.. java:method:: @Override public Entity next() throws NoSuchElementException
   :outertype: SearchDatas

remove
^^^^^^

.. java:method:: @Override public void remove()
   :outertype: SearchDatas

