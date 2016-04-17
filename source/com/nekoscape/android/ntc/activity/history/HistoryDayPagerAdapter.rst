.. java:import:: android.support.v4.app Fragment

.. java:import:: android.support.v4.app FragmentManager

.. java:import:: android.support.v4.app FragmentStatePagerAdapter

HistoryDayPagerAdapter
======================

.. java:package:: com.nekoscape.android.ntc.activity.history
   :noindex:

.. java:type:: public class HistoryDayPagerAdapter extends FragmentStatePagerAdapter

Fields
------
PAGE_NUM
^^^^^^^^

.. java:field:: public static int PAGE_NUM
   :outertype: HistoryDayPagerAdapter

Constructors
------------
HistoryDayPagerAdapter
^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public HistoryDayPagerAdapter(FragmentManager fm)
   :outertype: HistoryDayPagerAdapter

Methods
-------
getCount
^^^^^^^^

.. java:method:: @Override public int getCount()
   :outertype: HistoryDayPagerAdapter

getItem
^^^^^^^

.. java:method:: @Override public Fragment getItem(int i)
   :outertype: HistoryDayPagerAdapter

getPageTitle
^^^^^^^^^^^^

.. java:method:: @Override public CharSequence getPageTitle(int position)
   :outertype: HistoryDayPagerAdapter

