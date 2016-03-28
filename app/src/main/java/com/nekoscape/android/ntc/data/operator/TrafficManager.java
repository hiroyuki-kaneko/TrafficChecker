package com.nekoscape.android.ntc.data.operator;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.net.TrafficStats;
//import android.util.Log;

import com.nekoscape.android.ntc.common.DataType;
import com.nekoscape.android.ntc.common.NetworkStatus;
import com.nekoscape.android.ntc.data.object.SearchDatas;
import com.nekoscape.android.ntc.data.object.SearchDatas.Entity;
import com.nekoscape.android.ntc.data.object.TransferData;

/**
 * 3G, WIFIの通信量を記録する。
 * 作成するアプリはWifiを計測することがメインであるが、将来的に有料版でMobile通信もチェックできるようにするので
 * 
 * @author someone
 * 
 */
public class TrafficManager {
	private static TrafficManager instance = null;
	private long prevTrafficSize = 0L;
	private long prevResvTrafficSize = 0L;
	private long prevMSendSize = 0L;
	private long prevMRecvSize = 0L;
	private long prevOSendSize = 0L;
	private long prevORecvSize = 0L;

	private boolean hasTraffic = false;
	
//	private Writable writable = null;

	// private Map<String, Long> totalTraffic = new HashMap<String, Long>();
	private UserDataManager dataManager = null;

	private TrafficManager(Context context) {
		this.dataManager = new UserDataManager(context);
//		writable = Writable.getInstance(context);

		this.prevTrafficSize = getCurrentWiFiTrafficBytes();

		this.prevResvTrafficSize = getCurrentWifiRecvTrafficBytes();

		this.prevMSendSize = TrafficStats.getMobileTxBytes();
		this.prevMRecvSize = TrafficStats.getMobileRxBytes();
		this.prevOSendSize = TrafficStats.getTotalTxBytes();
		this.prevORecvSize = TrafficStats.getTotalRxBytes();

		//これはどのくらい重要なのか？
//		exec();
	}

	public static synchronized TrafficManager getInstance(Context context) {
		if (instance == null) {
			instance = new TrafficManager(context);
		}
		return instance;
	}

	public boolean hasCurrrentTraffic() {
		return this.hasTraffic;
	}

	/**
	 * 通信量を記録。
	 * @param status
	 */
	public void exec(NetworkStatus status) {

		// 通信の種別を調べる。
		// WIFI、3Gは別になってるのでWIFIだけであれば、SSID取得だけでOK

		long currentTrafficSize = getCurrentWiFiTrafficBytes();
		long currentRecvTrafficSize = getCurrentWifiRecvTrafficBytes();

		long currentMSendSize = TrafficStats.getMobileTxBytes();
		long currentMRecvSize = TrafficStats.getMobileRxBytes();
		long currentOSendSize = TrafficStats.getTotalTxBytes();
		long currentORecvSize = TrafficStats.getTotalRxBytes();

//		Log.d("traffic current", Long.toString(currentTrafficSize));

		// 通信量の取得
		long trafficSize = currentTrafficSize - this.prevTrafficSize;
		long recvTrafficSize = currentRecvTrafficSize
				- this.prevResvTrafficSize;

		long mSendSize = currentMSendSize - this.prevMSendSize;
		long mRecvSize = currentMRecvSize - this.prevMRecvSize;
		long oSendSize = currentOSendSize - this.prevOSendSize;
		long oRecvSize = currentORecvSize - this.prevORecvSize;

		this.prevTrafficSize = currentTrafficSize;
		this.prevResvTrafficSize = currentRecvTrafficSize;

		this.prevMSendSize = currentMSendSize;
		this.prevMRecvSize = currentMRecvSize;
		this.prevOSendSize = currentOSendSize;
		this.prevORecvSize = currentORecvSize;

		if (mSendSize < 0) {
			mSendSize = 0L;
		}

		if (mRecvSize < 0) {
			mRecvSize = 0L;
		}

		if (oSendSize < 0) {
			oSendSize = 0L;
		}

		if (oRecvSize < 0) {
			oRecvSize = 0L;
		}

		// マイナスの値であれば 0にする
		if (recvTrafficSize < 0) {
			recvTrafficSize = 0L;
		}
		if (trafficSize < 0) {
			trafficSize = 0L;
		}

		long mobileTrafficSize = mSendSize + mRecvSize;
		if (recvTrafficSize + trafficSize == 0 && mobileTrafficSize == 0) {
			this.hasTraffic = false;
			return;
		} else {
			this.hasTraffic = true;
		}

//		Log.d("traffic size", Long.toString(trafficSize));
		TransferData data = new TransferData(new Date(), status.getType(),
				status.getSubtype(), status.getSSID());
//		
//		TraficData traficData = new TraficData(mSendSize, mRecvSize, oSendSize, oRecvSize);
//		writable.insertTraffic(status, traficData);
		
		
		data.setSendSize(trafficSize);
		data.setRevSize(recvTrafficSize);

		data.setMrecvSize(mRecvSize);
		data.setMsendSize(mSendSize);
		data.setOrecvSize(oRecvSize);
		data.setOsendSize(oSendSize);
//		Log.d("toString", data.toString());

		this.dataManager.regsitData(data);

	}

	/**
	 * 全体の通信量からモバイルデータの通信量を差し引いてWiFiの通信量としたもの。
	 * 
	 * @return
	 */
	private long getCurrentWiFiTrafficBytes() {
		long currentTrafficSize = TrafficStats.getTotalTxBytes()
				- TrafficStats.getMobileTxBytes();

//		Log.d("traffic state", String.format("%s, %s, %s, %s",
//				TrafficStats.getTotalRxBytes(), TrafficStats.getTotalTxBytes(),
//				TrafficStats.getMobileRxBytes(),
//				TrafficStats.getMobileTxBytes()));
		return currentTrafficSize;
	}

	private long getCurrentWifiRecvTrafficBytes() {
		return TrafficStats.getTotalRxBytes() - TrafficStats.getMobileRxBytes();
	}


	public double getMobileTrafficSize() {
		Calendar calendar = Calendar.getInstance();
		SearchDatas dataIterator = dataManager.getSearchData(DataType.DAY_SUM,
				calendar);

		double sum = 0f;
		while (dataIterator.hasNext()) {
			Entity entity = dataIterator.next();
			sum = entity.getMobileTotal();
		}
		return sum;
	}

	@Deprecated
	public long getTotalTrafficSize(NetworkStatus status) {

		TransferData userData = new TransferData(new Date(), status.getType(),
				status.getSubtype(), status.getSSID());
		TransferData data = this.dataManager.search(userData);
//		Log.d("return", Long.toString(data.getDataSize()));
		return data.getDataSize();
	}

	public void upgradeTrafficdataTable() {
		this.dataManager.upgradeTrafficdata();
	}
}
