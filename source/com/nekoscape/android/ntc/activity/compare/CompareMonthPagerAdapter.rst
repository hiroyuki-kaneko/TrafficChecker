.. java:import:: android.support.v4.app Fragment

.. java:import:: android.support.v4.app FragmentManager

.. java:import:: android.support.v4.app FragmentStatePagerAdapter

CompareMonthPagerAdapter
========================

.. java:package:: com.nekoscape.android.ntc.activity.compare
   :noindex:

.. java:type:: public class CompareMonthPagerAdapter extends FragmentStatePagerAdapter

Fields
------
PAGE_NUM
^^^^^^^^

.. java:field:: public static int PAGE_NUM
   :outertype: CompareMonthPagerAdapter

Constructors
------------
CompareMonthPagerAdapter
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public CompareMonthPagerAdapter(FragmentManager fm)
   :outertype: CompareMonthPagerAdapter

Methods
-------
getCount
^^^^^^^^

.. java:method:: @Override public int getCount()
   :outertype: CompareMonthPagerAdapter

getItem
^^^^^^^

.. java:method:: @Override public Fragment getItem(int i)
   :outertype: CompareMonthPagerAdapter

getPageTitle
^^^^^^^^^^^^

.. java:method:: @Override public CharSequence getPageTitle(int position)
   :outertype: CompareMonthPagerAdapter

