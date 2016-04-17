.. java:import:: java.util Iterator

.. java:import:: java.util NoSuchElementException

.. java:import:: android.database Cursor

.. java:import:: android.util Log

.. java:import:: com.nekoscape.android.ntc.data.operator GraphDataIterator.Entity

GraphDataIterator
=================

.. java:package:: com.nekoscape.android.ntc.data.operator
   :noindex:

.. java:type:: public class GraphDataIterator implements Iterator<Entity>

Constructors
------------
GraphDataIterator
^^^^^^^^^^^^^^^^^

.. java:constructor:: public GraphDataIterator(Cursor cursor)
   :outertype: GraphDataIterator

Methods
-------
hasNext
^^^^^^^

.. java:method:: @Override public boolean hasNext()
   :outertype: GraphDataIterator

next
^^^^

.. java:method:: @Override public Entity next() throws NoSuchElementException
   :outertype: GraphDataIterator

remove
^^^^^^

.. java:method:: @Override public void remove()
   :outertype: GraphDataIterator

