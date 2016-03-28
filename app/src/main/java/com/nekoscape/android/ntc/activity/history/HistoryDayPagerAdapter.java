package com.nekoscape.android.ntc.activity.history;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HistoryDayPagerAdapter extends FragmentStatePagerAdapter {
	public static int PAGE_NUM = 2 * 365 + 1;

	public HistoryDayPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		return new HistoryDayFragment().setPosition(i);
	}

	@Override
	public int getCount() {
		return PAGE_NUM;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return HistoryDayFragment.getTitle(position);
	}

}
