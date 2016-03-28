package com.nekoscape.android.ntc.activity.compare;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nekoscape.android.ntc.activity.R;
import com.nekoscape.android.ntc.dao.Hour;
import com.nekoscape.android.ntc.dao.NetworkType;
import com.nekoscape.android.ntc.common.ByteUnit;
import com.nekoscape.android.ntc.data.operator.CompareSsid;

public class CompareFragment extends Fragment {

	private int position = 0;

	public CompareFragment setPosition(int i) {
		this.position = ComparePagerAdapter.PAGE_NUM - i - 1;
		return this;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.compare_fragment, null);

		TableLayout tl = (TableLayout) view
				.findViewById(R.id.compare_tablelayout);

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.DAY_OF_MONTH, -this.position);

		CompareSsid compareSsid = new CompareSsid(getActivity());
		List<Hour> hours = compareSsid.getDayTraffics(calendar);

		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);

		int i = 1;
		for (Hour hour : hours) {
			TableRow tr = (TableRow) getLayoutInflater(savedInstanceState)
					.inflate(R.layout.compare_row, null);
			if (++i % 2 == 0) {
				tr.setBackgroundColor(Color.parseColor("#EEEEEE"));
				;
			}

			TextView dateText = (TextView) tr
					.findViewById(R.id.compare_row_date);
			NetworkType networkType = compareSsid.getNetworkType(hour.getId());
			String ssid = networkType.getSsid();
			if (ssid == null || ssid.isEmpty()) {
				// SSIDが空だったら無視
				continue;
			}
			dateText.setText(ssid);
			TextView totalText = (TextView) tr
					.findViewById(R.id.compare_row_total);
			String size = null;
			if (networkType.getType() == ConnectivityManager.TYPE_MOBILE) {
				size = format.format(ByteUnit.BYTE.toMByte(hour.getMrecv()
						+ hour.getMsend()));
			} else {
				size = format.format(ByteUnit.BYTE.toMByte(hour.getOrecv()
						+ hour.getOsend() - hour.getMrecv() - hour.getMsend()));
			}
			totalText.setText(size);
			tl.addView(tr);

		}

		return view;
	}

	protected List<Double> getInitializedList(int num) {
		List<Double> list = new ArrayList<Double>();

		// リスト初期化
		for (int i = 0; i < num; i++) {
			list.add(Double.valueOf(0));
		}
		return list;
	}

	public static String getTitle(int position) {
		int pos = ComparePagerAdapter.PAGE_NUM - position - 1;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -pos);

		return String.format("%s/%s/%s", cal.get(Calendar.YEAR),
				(cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DAY_OF_MONTH));
	}

}
