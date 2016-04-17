.. java:import:: android.support.v4.app Fragment

.. java:import:: android.support.v4.app FragmentManager

.. java:import:: android.support.v4.app FragmentStatePagerAdapter

HistoryPagerAdapter
===================

.. java:package:: com.nekoscape.android.ntc.activity.history
   :noindex:

.. java:type:: public class HistoryPagerAdapter extends FragmentStatePagerAdapter

Fields
------
PAGE_NUM
^^^^^^^^

.. java:field:: public static int PAGE_NUM
   :outertype: HistoryPagerAdapter

Constructors
------------
HistoryPagerAdapter
^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public HistoryPagerAdapter(FragmentManager fm)
   :outertype: HistoryPagerAdapter

Methods
-------
getCount
^^^^^^^^

.. java:method:: @Override public int getCount()
   :outertype: HistoryPagerAdapter

getItem
^^^^^^^

.. java:method:: @Override public Fragment getItem(int i)
   :outertype: HistoryPagerAdapter

getPageTitle
^^^^^^^^^^^^

.. java:method:: @Override public CharSequence getPageTitle(int position)
   :outertype: HistoryPagerAdapter

