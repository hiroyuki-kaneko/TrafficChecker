.. java:import:: android.support.v4.app Fragment

.. java:import:: android.support.v4.app FragmentManager

.. java:import:: android.support.v4.app FragmentStatePagerAdapter

HistoryYearPagerAdapter
=======================

.. java:package:: com.nekoscape.android.ntc.activity.history
   :noindex:

.. java:type:: public class HistoryYearPagerAdapter extends FragmentStatePagerAdapter

Fields
------
PAGE_NUM
^^^^^^^^

.. java:field:: public static int PAGE_NUM
   :outertype: HistoryYearPagerAdapter

Constructors
------------
HistoryYearPagerAdapter
^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public HistoryYearPagerAdapter(FragmentManager fm)
   :outertype: HistoryYearPagerAdapter

Methods
-------
getCount
^^^^^^^^

.. java:method:: @Override public int getCount()
   :outertype: HistoryYearPagerAdapter

getItem
^^^^^^^

.. java:method:: @Override public Fragment getItem(int i)
   :outertype: HistoryYearPagerAdapter

getPageTitle
^^^^^^^^^^^^

.. java:method:: @Override public CharSequence getPageTitle(int position)
   :outertype: HistoryYearPagerAdapter

