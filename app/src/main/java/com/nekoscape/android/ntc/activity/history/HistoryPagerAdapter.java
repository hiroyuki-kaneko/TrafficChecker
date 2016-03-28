package com.nekoscape.android.ntc.activity.history;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HistoryPagerAdapter extends FragmentStatePagerAdapter {
	public static int PAGE_NUM = 2 * 12;

	public HistoryPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		return new HistoryFragment().setPosition(i);
	}

	@Override
	public int getCount() {
		return PAGE_NUM;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return HistoryFragment.getTitle(position);
	}

}
