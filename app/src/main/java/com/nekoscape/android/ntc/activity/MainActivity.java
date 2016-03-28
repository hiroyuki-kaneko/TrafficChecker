package com.nekoscape.android.ntc.activity;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;
import com.nekoscape.android.ntc.dao.Hour;
import com.nekoscape.android.ntc.activity.compare.CompareActivity;
import com.nekoscape.android.ntc.activity.factory.PreferenceFactory;
import com.nekoscape.android.ntc.activity.history.HistoryActivity;
import com.nekoscape.android.ntc.activity.pref.PreferenceConstant;
import com.nekoscape.android.ntc.common.ByteUnit;
import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.common.Util;
import com.nekoscape.android.ntc.data.operator.HistoryHourData;
import com.nekoscape.android.ntc.data.operator.UserDataManager;
import com.nekoscape.android.ntc.service.TrafficCheckService;

import java.text.NumberFormat;

/**
 * 通信量を表示するアクティビティ
 *
 * @author someone
 */
public class MainActivity extends ActionBarActivity implements
        ActionBar.OnNavigationListener {

    public static final int MENU_SELECT_CONFIG = 0;
    public static final int MENU_SELECT_GRAPH = 1;

    private String ssid = "No Wi-Fi";
    private int type = 0;
    private int subtype = 0;

    private ArrayAdapter<NetworkStatus> spinnerAdapter = null;
    private NetworkStatus item = null;

    /**
     * アクティビティ作成時に呼び出される
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // サービスを起動　する

        setContentView(R.layout.activity_main);

        drawGraphic();
        startService(new Intent(getBaseContext(), TrafficCheckService.class));

        SharedPreferences preferences = getSharedPreferences("system_pref",
                Context.MODE_PRIVATE);
        boolean network_flag = false;
        if (preferences != null) {
            network_flag = preferences.getBoolean(
                    PreferenceConstant.NETWORK_INIT.getName(), false);
        }
        if (!network_flag) {
            Util.changeNetworkPreference(this);
            Editor editor = preferences.edit();
            editor.putBoolean(PreferenceConstant.NETWORK_INIT.getName(), true);
            editor.commit();
        }
        boolean flag = false;
        if (preferences != null) {
            flag = preferences.getBoolean("upgrade_table_trafficdata", false);
        }
        if (!flag) {
            startActivity(new Intent(this, UpgradeActivity.class));
        }

        Log.d("file", "call dataChanged");
        BackupManager bm = new BackupManager(this);
        bm.dataChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        setNoWiFiSSIDName();
        if (item != null) {
            setCurrentNetwork(item);
        }
        drawGraphic();
    }

    @Override
    public void onPause() {
        super.onPause();
        ssid = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ssid = null;

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

    private void drawGraphic() {

        if (spinnerAdapter == null) {
            UserDataManager dataManager = new UserDataManager(this);
            NetworkStatus[] array = dataManager.getSSIDList();

            ActionBar mActionBar = getSupportActionBar();
            if (mActionBar == null) {
                return;
            }
            mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            mActionBar.setDisplayHomeAsUpEnabled(false);

            spinnerAdapter = new ArrayAdapter<NetworkStatus>(this,
                    R.layout.linegraph_dropdownlist, array);
            mActionBar.setListNavigationCallbacks(spinnerAdapter, this);
        }

        NetworkStatus status = null;
        status = new NetworkStatus(type, subtype, ssid);
        // Wi-Fi接続がないことを前提に名前を設定する。

        if (!status.isConnected()) {
            return;
        }

        // 通信のタイプなどを取得
        type = status.getType();
        subtype = status.getSubtype();

        // これをなくせば、Mobileも見える？
        // if (!status.isWifiConnected()) {
        // return;
        // }

        TextView ssidText = (TextView) findViewById(R.id.ssidname);
        ssidText.setText(getString(R.string.wifi_service_prefix,
                status.getSSID()));
        setTitle(status.getSSID());
        this.ssid = status.getSSID();
        // データを設定する
        setTrafficData(status);
    }

    private void setNoWiFiSSIDName() {
        TextView ssidText = (TextView) findViewById(R.id.ssidname);
        ssidText.setText(getString(R.string.no_wifi_service));
    }

    private void setTrafficData(NetworkStatus status) {
        // スレッドの初期化
        final HistoryHourData hourData = new HistoryHourData(this);

        Hour hour = hourData.betweenDate(status, 1, 0);
        long recv = getRecvTraffic(status, hour);
        long send = getSendTraffic(status, hour);
        TextView todaySum = (TextView) findViewById(R.id.today_sum);
        setTrafficData(todaySum, recv + send);

        TextView todaySend = (TextView) findViewById(R.id.today_send);
        setTrafficData(todaySend, send);

        TextView todayRecv = (TextView) findViewById(R.id.today_recv);
        setTrafficData(todayRecv, recv);

        // 昨日のデータはSSIDが変更されない限り描画の必要はないと思う。
        hour = hourData.betweenDate(status, 1, -2);
        recv = getRecvTraffic(status, hour);
        send = getSendTraffic(status, hour);
        todaySum = (TextView) findViewById(R.id.yesterday_sum);
        setTrafficData(todaySum, recv + send);

        todaySend = (TextView) findViewById(R.id.yesterday_send);
        setTrafficData(todaySend, send);

        todayRecv = (TextView) findViewById(R.id.yesterday_recv);
        setTrafficData(todayRecv, recv);

        hour = hourData.monthData(status);
        recv = getRecvTraffic(status, hour);
        send = getSendTraffic(status, hour);

        todaySum = (TextView) findViewById(R.id.month_sum);
        setTrafficData(todaySum, send + recv);

        todaySend = (TextView) findViewById(R.id.month_send);
        setTrafficData(todaySend, send);

        todayRecv = (TextView) findViewById(R.id.month_recv);
        setTrafficData(todayRecv, recv);
    }

    public long getSendTraffic(NetworkStatus status, Hour hour) {
        if (status.getType() == ConnectivityManager.TYPE_MOBILE) {
            return hour.getMsend();
        } else {
            return hour.getOsend() - hour.getMsend();
        }
    }

    public long getRecvTraffic(NetworkStatus status, Hour hour) {
        if (status.getType() == ConnectivityManager.TYPE_MOBILE) {
            return hour.getMrecv();
        } else {
            return hour.getOrecv() - hour.getMrecv();
        }
    }

    private void setTrafficData(TextView view, long datasize) {
        // データを設定する
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);

        view.setText(format.format(ByteUnit.BYTE.toMByte(datasize)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_settings:
                intent = PreferenceFactory.createInstance(this,
                        Build.VERSION.SDK_INT);
                startActivity(intent);
                break;
            case R.id.action_graph:
                intent = new Intent(MainActivity.this, LineGraphicActivity.class);
                intent.putExtra("TYPE", this.type);
                intent.putExtra("SUBTYPE", this.subtype);
                intent.putExtra("SSID", this.ssid);
                startActivity(intent);
                break;
            case R.id.action_google_play:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.action_history:
                intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putExtra("TYPE", this.type);
                intent.putExtra("SUBTYPE", this.subtype);
                intent.putExtra("SSID", this.ssid);
                startActivity(intent);
                break;
            case R.id.action_compare:
                intent = new Intent(MainActivity.this, CompareActivity.class);
                intent.putExtra("TYPE", this.type);
                intent.putExtra("SUBTYPE", this.subtype);
                intent.putExtra("SSID", this.ssid);
                startActivity(intent);
                break;
            case R.id.action_networkinfo:
                intent = new Intent(MainActivity.this,
                        NetworkDetailInfoActivity.class);
                intent.putExtra("TYPE", this.type);
                intent.putExtra("SUBTYPE", this.subtype);
                intent.putExtra("SSID", this.ssid);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long arg1) {
        item = (NetworkStatus) spinnerAdapter.getItem(itemPosition);
        setCurrentNetwork(item);
        drawGraphic();

        return true;
    }

    private void setCurrentNetwork(NetworkStatus item) {
        setNoWiFiSSIDName();

        if (!getString(R.string.current_network).equals(item.getSSID())) {
            this.type = item.getType();
            this.subtype = item.getSubtype();
            this.ssid = item.getSSID();
        } else {
            NetworkStatus status = new NetworkStatus(this);
            this.type = status.getType();
            this.subtype = status.getSubtype();
            this.ssid = status.getSSID();
        }
    }

}
