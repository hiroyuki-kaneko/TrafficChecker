package com.nekoscape.android.ntc.activity.history;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HistoryYearPagerAdapter extends FragmentStatePagerAdapter {
	public static int PAGE_NUM = 2;

	public HistoryYearPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		return new HistoryYearFragment().setPosition(i);
	}

	@Override
	public int getCount() {
		return PAGE_NUM;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return HistoryYearFragment.getTitle(position);
	}

}
