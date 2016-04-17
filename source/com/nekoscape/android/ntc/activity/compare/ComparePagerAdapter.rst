.. java:import:: android.support.v4.app Fragment

.. java:import:: android.support.v4.app FragmentManager

.. java:import:: android.support.v4.app FragmentStatePagerAdapter

ComparePagerAdapter
===================

.. java:package:: com.nekoscape.android.ntc.activity.compare
   :noindex:

.. java:type:: public class ComparePagerAdapter extends FragmentStatePagerAdapter

Fields
------
PAGE_NUM
^^^^^^^^

.. java:field:: public static int PAGE_NUM
   :outertype: ComparePagerAdapter

Constructors
------------
ComparePagerAdapter
^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public ComparePagerAdapter(FragmentManager fm)
   :outertype: ComparePagerAdapter

Methods
-------
getCount
^^^^^^^^

.. java:method:: @Override public int getCount()
   :outertype: ComparePagerAdapter

getItem
^^^^^^^

.. java:method:: @Override public Fragment getItem(int i)
   :outertype: ComparePagerAdapter

getPageTitle
^^^^^^^^^^^^

.. java:method:: @Override public CharSequence getPageTitle(int position)
   :outertype: ComparePagerAdapter

