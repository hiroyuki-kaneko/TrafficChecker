package com.nekoscape.android.ntc.data.object;

import java.util.Date;

public class TransferData implements Cloneable {
	private final Date date;
	private final int type;
	private final int subtype;
	private final String ssid;

	private long sendSize;
	private long revSize;

	private long msendSize;

	private boolean mobile_flag = false;

	public void setMobileFlag(boolean flag) {
		this.mobile_flag = flag;
	}

	public long getMsendSize() {
		return msendSize;
	}

	public void setMsendSize(long msendSize) {
		this.msendSize = msendSize;
	}

	public long getMrecvSize() {
		return mrecvSize;
	}

	public void setMrecvSize(long mrecvSize) {
		this.mrecvSize = mrecvSize;
	}

	public long getOsendSize() {
		return osendSize;
	}

	public void setOsendSize(long osendSize) {
		this.osendSize = osendSize;
	}

	public long getOrecvSize() {
		return orecvSize;
	}

	public void setOrecvSize(long orecvSize) {
		this.orecvSize = orecvSize;
	}

	private long mrecvSize;
	private long osendSize;
	private long orecvSize;

	public long getSendSize() {
		if (!mobile_flag) {
			return sendSize;
		} else {
			return msendSize;
		}
	}

	public void setSendSize(long sendSize) {
		this.sendSize = sendSize;
	}

	public long getRevSize() {
		if (!mobile_flag) {
			return revSize;
		} else {
			return mrecvSize;
		}
	}

	public void setRevSize(long revSize) {
		this.revSize = revSize;
	}

	public TransferData(Date date, int l, int m, String ssid) {
		if (ssid == null) {
			ssid = "";
		}
		this.date = date;
		this.type = l;
		this.subtype = m;
		this.ssid = ssid;

	}

	public long getDataSize() {
		return getRevSize() + getSendSize();
	}

	public Date getDate() {
		return date;
	}

	public int getType() {
		return type;
	}

	public int getSubtype() {
		return subtype;
	}

	public String getSSID() {
		return ssid;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s.%s.%s.%s.%s", date, type,
				subtype, ssid, sendSize, revSize, osendSize, orecvSize,
				msendSize, mrecvSize);
	}
}
