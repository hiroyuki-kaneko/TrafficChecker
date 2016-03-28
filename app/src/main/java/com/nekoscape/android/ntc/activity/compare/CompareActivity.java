package com.nekoscape.android.ntc.activity.compare;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.google.analytics.tracking.android.EasyTracker;
import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.common.DummyTabFactory;
import com.nekoscape.android.ntc.common.Util;

public class CompareActivity extends FragmentActivity implements
        TabHost.OnTabChangeListener {
    ViewPager viewPager;
    private String mLastTabId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        // Show the Up button in the action bar.
        setupActionBar();

        // タブ設定
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        setTitle(getString(R.string.compare_title));
        tabHost.addTab(createTab(tabHost, "tab_day",
                getString(R.string.tab_day)));
        tabHost.addTab(createTab(tabHost, "tab_month",
                getString(R.string.tab_month)));

        tabHost.setOnTabChangedListener(this);
        tabHost.setCurrentTab(1);

    }

    private TabSpec createTab(TabHost tabHost, String tabName, String indicator) {
        TabSpec tab1 = tabHost.newTabSpec(tabName);
        tab1.setIndicator(indicator);
        tab1.setContent(new DummyTabFactory(this));
        return tab1;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getActionBar() != null) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabChanged(String tabId) {
        if (!mLastTabId.equals(tabId)) {
            viewPager = (ViewPager) findViewById(R.id.compare_pager);
            if ("tab_day".equals(tabId)) {
                viewPager.setAdapter(new ComparePagerAdapter(
                        getSupportFragmentManager()));
                viewPager.setCurrentItem(ComparePagerAdapter.PAGE_NUM);
            } else if ("tab_month".equals(tabId)) {
                viewPager.setAdapter(new CompareMonthPagerAdapter(
                        getSupportFragmentManager()));
                viewPager.setCurrentItem(CompareMonthPagerAdapter.PAGE_NUM);

            } else {
                // 変なのが来たら無視
                return;
            }
            mLastTabId = tabId;
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        if (Util.canSendAnalystics(this)) {
            Log.d("", "Google analystics通知");
            EasyTracker.getInstance(this).activityStart(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (Util.canSendAnalystics(this)) {
            EasyTracker.getInstance(this).activityStop(this);
        }
    }

}
