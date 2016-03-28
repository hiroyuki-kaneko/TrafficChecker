package com.nekoscape.android.ntc.activity.compare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CompareMonthPagerAdapter extends FragmentStatePagerAdapter {
	public static int PAGE_NUM = 2 * 12;

	public CompareMonthPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		return new CompareMonthFragment().setPosition(i);
	}

	@Override
	public int getCount() {
		return PAGE_NUM;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return CompareMonthFragment.getTitle(position);
	}

}
