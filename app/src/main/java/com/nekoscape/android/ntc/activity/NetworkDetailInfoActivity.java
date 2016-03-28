package com.nekoscape.android.ntc.activity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class NetworkDetailInfoActivity extends ActionBarActivity implements
		ActionBar.OnNavigationListener {

	private int rowNums = 0;
	
	/**
	 * アクティビティ作成時に呼び出される
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// サービスを起動　する

		setContentView(R.layout.activity_network_detail);

		TableLayout tl = (TableLayout) findViewById(R.id.network_tablelayout);

		// ネットワーク情報の取得
		boolean isWifi = setConnectionRow(tl);

		// Wi-Fi状態　モバイル通信なら表示できない
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();

		if (isWifi) {
			setWifiRow(tl, wifiInfo);
			
			setDhcpRow(tl, dhcpInfo, wifiInfo);
		}

	}

	private void setWifiRow(TableLayout tl, WifiInfo wifiInfo) {
		if (wifiInfo == null) {
			return;
		}

		tl.addView(createWifiMacAdress(wifiInfo));

		tl.addView(createWifiSSID(wifiInfo));

		tl.addView(createWifiIsStels(wifiInfo));

		tl.addView(createWifiLevel(wifiInfo));

		tl.addView(createWifiLinkSpeed(wifiInfo));
		
//		tl.addView(createWifiBSSID(wifiInfo));
//		
//		tl.addView(createWifiSupplicantState(wifiInfo));
//		
//		tl.addView(createWifiSupplicantStateDetails(wifiInfo));
	}
	

	private void setDhcpRow(TableLayout tl, DhcpInfo dhcpInfo, WifiInfo wifiInfo) {
		if (wifiInfo == null) {
			return;
		}
		tl.addView(createWifiDNS1(dhcpInfo));

		tl.addView(createWifiDNS2(dhcpInfo));

		tl.addView(createWifiGateWay(dhcpInfo));

		tl.addView(createWifiNetMask(dhcpInfo));

		tl.addView(createWifiIPAddress(dhcpInfo));
	}

	private TableRow createWifiMacAdress(WifiInfo wifiInfo) {
		TableRow row = createTableRow(this);

		setColumnValue(row, getString(R.string.network_device_macaddress), 0);
		setColumnValue(row, wifiInfo.getMacAddress(), 1);

		return row;
	}
	
//	private TableRow createWifiBSSID(WifiInfo wifiInfo) {
//		TableRow row = createTableRow(this);
//
//		setColumnValue(row, "BSSID", 0);
//		setColumnValue(row, wifiInfo.getBSSID(), 1);
//
//		return row;
//	}
//	
//	private TableRow createWifiSupplicantState(WifiInfo wifiInfo) {
//		TableRow row = createTableRow(this);
//
//		setColumnValue(row, "SupplicantState", 0);
//		setColumnValue(row, wifiInfo.getSupplicantState().toString(), 1);
//
//		return row;
//	}
//	
//	private TableRow createWifiSupplicantStateDetails(WifiInfo wifiInfo) {
//		TableRow row = createTableRow(this);
//
//		setColumnValue(row, "SupplicantStateDetail", 0);
//		setColumnValue(row, wifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()).toString(), 1);
//
//		return row;
//	}

	


	private TableRow createWifiSSID(WifiInfo wifiInfo) {
		TableRow row = createTableRow(this);

		setColumnValue(row, getString(R.string.network_wifi_ssid), 0);
		setColumnValue(row, wifiInfo.getSSID(), 1);

		return row;
	}

	private TableRow createWifiIsStels(WifiInfo wifiInfo) {
		TableRow row = createTableRow(this);

		setColumnValue(row, getString(R.string.network_wifi_stels), 0);
		setColumnValue(row, Boolean.toString(wifiInfo.getHiddenSSID()), 1);

		return row;
	}

	private TableRow createWifiLinkSpeed(WifiInfo wifiInfo) {
		TableRow row = createTableRow(this);

		setColumnValue(row, getString(R.string.network_wifi_linkspeed), 0);
		setColumnValue(row,
				wifiInfo.getLinkSpeed() + WifiInfo.LINK_SPEED_UNITS, 1);

		return row;
	}

	private TableRow createWifiLevel(WifiInfo wifiInfo) {
		TableRow row = createTableRow(this);

		setColumnValue(row, getString(R.string.network_wifi_power_level), 0);
		setColumnValue(row,
				WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 11)
						+ " / 10", 1);

		return row;
	}

	private TableRow createWifiDNS1(DhcpInfo dhcpInfo) {
		TableRow row = createTableRow(this);
		setColumnValue(row, getString(R.string.network_wifi_dns1), 0);
		setColumnValue(row, intToInetAddress(dhcpInfo.dns1).getHostAddress(), 1);
		return row;
	}

	private TableRow createWifiDNS2(DhcpInfo dhcpInfo) {
		TableRow row = createTableRow(this);
		setColumnValue(row, getString(R.string.network_wifi_dns2), 0);
		setColumnValue(row, intToInetAddress(dhcpInfo.dns2).getHostAddress(), 1);
		return row;
	}

	private TableRow createWifiGateWay(DhcpInfo dhcpInfo) {
		TableRow row = createTableRow(this);
		setColumnValue(row, getString(R.string.network_wifi_gateway), 0);
		setColumnValue(row,
				intToInetAddress(dhcpInfo.gateway).getHostAddress(), 1);
		return row;
	}

	private TableRow createWifiNetMask(DhcpInfo dhcpInfo) {
		TableRow row = createTableRow(this);
		setColumnValue(row, getString(R.string.network_wifi_netmask), 0);
		setColumnValue(row,
				intToInetAddress(dhcpInfo.netmask).getHostAddress(), 1);
		return row;
	}

	private TableRow createWifiIPAddress(DhcpInfo dhcpInfo) {
		TableRow row = createTableRow(this);
		setColumnValue(row, getString(R.string.network_wifi_ipaddress), 0);
		setColumnValue(row, intToInetAddress(dhcpInfo.ipAddress)
				.getHostAddress(), 1);
		return row;
	}
	

	private InetAddress intToInetAddress(int hostAddress) {
		byte[] addressBytes = { (byte) (0xff & hostAddress),
				(byte) (0xff & (hostAddress >> 8)),
				(byte) (0xff & (hostAddress >> 16)),
				(byte) (0xff & (hostAddress >> 24)) };

		try {
			return InetAddress.getByAddress(addressBytes);
		} catch (UnknownHostException e) {
			throw new AssertionError();
		}
	}

	private boolean setConnectionRow(TableLayout tl) {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo == null) {
			return false;
		}


		tl.addView(createConnectionStateDetail(this, connectivityManager,
				networkInfo));

		tl.addView(createConnectionExtraInfo(this, connectivityManager,
				networkInfo));


		tl.addView(createNetworkType(this, connectivityManager, networkInfo));


		if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
			return true;
		}else{
			tl.addView(createNetworkSubType(this, connectivityManager, networkInfo));

			tl.addView(createRoaming(this, connectivityManager, networkInfo));
			return false;
		}
	}

	private TableRow createConnectionStateDetail(Context context,
			ConnectivityManager connectivityManager, NetworkInfo info) {
		TableRow row = createTableRow(context);

		setColumnValue(row, getString(R.string.network_connect_state), 0);
		setColumnValue(row, info.getDetailedState().toString(), 1);

		return row;
	}

	private TableRow createConnectionExtraInfo(Context context,
			ConnectivityManager connectivityManager, NetworkInfo networkInfo) {
		TableRow row = createTableRow(context);

		setColumnValue(row, getString(R.string.network_connect_extra), 0);
		setColumnValue(row, networkInfo.getExtraInfo(), 1);

		return row;
	}

	private TableRow createNetworkType(Context context,
			ConnectivityManager connectivityManager, NetworkInfo networkInfo) {
		TableRow row = createTableRow(context);

		setColumnValue(row, getString(R.string.network_connect_type), 0);
		setColumnValue(row, networkInfo.getTypeName(), 1);

		return row;
	}

	private TableRow createNetworkSubType(Context context,
			ConnectivityManager connectivityManager, NetworkInfo networkInfo) {
		TableRow row = createTableRow(context);

		setColumnValue(row, getString(R.string.network_connect_subtype), 0);
		setColumnValue(row, networkInfo.getSubtypeName(), 1);

		return row;
	}

	private TableRow createRoaming(Context context,
			ConnectivityManager connectivityManager, NetworkInfo networkInfo) {
		TableRow row = createTableRow(context);

		setColumnValue(row, getString(R.string.network_connect_roming), 0);
		setColumnValue(row, Boolean.toString(networkInfo.isRoaming()), 1);

		return row;
	}

	private void setColumnValue(TableRow row, String name, int index) {
		TextView nameView = (TextView) (row.getChildAt(index));
		nameView.setText(name);
	}

	private TableRow createTableRow(Context context) {
		TableRow row = 	(TableRow) getLayoutInflater().inflate(R.layout.history_row, null);
		if((rowNums % 2) == 0){
			row.setBackgroundColor(Color.parseColor("#EEEEEE"));;
		}
		rowNums++;
		return row;
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
