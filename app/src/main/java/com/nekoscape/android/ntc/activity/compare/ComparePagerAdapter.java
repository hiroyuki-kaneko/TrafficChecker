package com.nekoscape.android.ntc.activity.compare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ComparePagerAdapter extends FragmentStatePagerAdapter {
	public static int PAGE_NUM = 2 * 365 + 1;

	public ComparePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		return new CompareFragment().setPosition(i);
	}

	@Override
	public int getCount() {
		return PAGE_NUM;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return CompareFragment.getTitle(position);
	}

}
