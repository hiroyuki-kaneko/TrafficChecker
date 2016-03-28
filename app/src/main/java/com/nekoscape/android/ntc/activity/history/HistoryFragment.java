package com.nekoscape.android.ntc.activity.history;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.dao.Hour;
import com.nekoscape.android.ntc.common.ByteUnit;
import com.nekoscape.android.ntc.common.DataType;
import com.nekoscape.android.ntc.data.object.SearchDatas;
import com.nekoscape.android.ntc.data.object.SearchDatas.Entity;
import com.nekoscape.android.ntc.data.operator.UserDataManager;

public class HistoryFragment extends Fragment {

	TableLayout tl = null;

	private int position = 0;

	public HistoryFragment setPosition(int i) {
		this.position = HistoryPagerAdapter.PAGE_NUM - i - 1;
		return this;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.history_fragment, null);

		tl = (TableLayout) view.findViewById(R.id.history_tablelayout);

		UserDataManager dataManager = new UserDataManager(this.getActivity());

		Calendar calendar = Calendar.getInstance();

		Intent intent = getActivity().getIntent();
		int type = intent.getIntExtra("TYPE", 0);
		int subtype = intent.getIntExtra("SUBTYPE", 0);
		String ssid = intent.getStringExtra("SSID");

		calendar.add(Calendar.MONTH, -this.position);
		List<Hour> list = getInitializedList(calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
		SearchDatas dataIterator = dataManager.getSearchData(
				DataType.MONTH_SSID, calendar, type, subtype, ssid);

		Log.d("get data", "" + dataIterator.count());
		while (dataIterator.hasNext()) {
			Entity entity = dataIterator.next();
			// list.add((int) entity.getX() , Double.valueOf(ByteUnit.BYTE
			// .toMByte(entity.getWifiTotal())));
			Hour hour = new Hour();
			hour.setMrecv((long) entity.getMobileRecv());
			hour.setMsend((long) entity.getMobileSend());
			hour.setOrecv((long) entity.getWifiRecv());
			hour.setOsend((long) entity.getWifiSend());
			list.add((int) entity.getX(), hour);

		}
		int month = calendar.get(Calendar.MONTH) + 1;
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);

		for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			TableRow tr = (TableRow) getLayoutInflater(savedInstanceState)
					.inflate(R.layout.history_row, null);
			if (i % 2 == 0) {
				tr.setBackgroundColor(Color.parseColor("#EEEEEE"));
				;
			}
			TextView dateText = (TextView) tr
					.findViewById(R.id.history_row_date);
			dateText.setText(String.format("%s/%s", month, i));
			TextView totalText = (TextView) tr
					.findViewById(R.id.history_row_total);

			Hour hour = list.get(i);
			long send = getSize(type, hour.getMsend(), hour.getOsend());
			long recv = getSize(type, hour.getMrecv(), hour.getOrecv());
			totalText.setText(String.format("%s (%6s/%6s)",
					format.format(ByteUnit.BYTE.toMByte(send + recv)),
					format.format(ByteUnit.BYTE.toMByte(send)),
					format.format(ByteUnit.BYTE.toMByte(recv))));
			tl.addView(tr);

		}

		return view;
	}

	private long getSize(int type, long mobile, long other) {
		if (type == ConnectivityManager.TYPE_MOBILE) {
			return mobile;
		} else {
			return other - mobile;
		}
	}

	protected List<Hour> getInitializedList(int num) {
		List<Hour> list = new ArrayList<Hour>();

		// リスト初期化
		for (int i = 0; i < num; i++) {
			Hour hour = new Hour();
			hour.setMrecv(0L);
			hour.setMsend(0L);
			hour.setOrecv(0L);
			hour.setOsend(0L);
			list.add(hour);
		}
		return list;
	}

	public static String getTitle(int position) {
		int pos = HistoryPagerAdapter.PAGE_NUM - position - 1;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -pos);

		return String.format("%s/%s", cal.get(Calendar.YEAR),
				(cal.get(Calendar.MONTH) + 1));
	}

}
