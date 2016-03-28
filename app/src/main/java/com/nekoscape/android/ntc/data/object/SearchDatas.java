package com.nekoscape.android.ntc.data.object;

import java.util.Iterator;
import java.util.NoSuchElementException;

import android.database.Cursor;

import com.nekoscape.android.ntc.data.object.SearchDatas.Entity;

public class SearchDatas implements Iterator<Entity> {

	private Cursor cursor = null;
	private boolean init_flag = true;

	public SearchDatas(Cursor cursor) {
		this.cursor = cursor;
	}
	
	public int count(){
		return cursor.getCount();
	}
	
	@Deprecated
	public int getNetworkID(){
		return cursor.getInt(5);
	}

	@Override
	public boolean hasNext() {
		if (init_flag) {
			init_flag = false;
			return cursor.moveToFirst();
		} else {
			return cursor.moveToNext();
		}
	}

	@Override
	public Entity next() throws NoSuchElementException{
		if(cursor ==null){
			throw new NoSuchElementException("cursor is null.");
		}
		
		return new Entity(cursor.getDouble(0), cursor.getDouble(1),
				cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4));
		
	}

	@Override
	public void remove() {
		// do nothing

	}

	public static class Entity {
		private double x;
		private double osend;
		private double orecv;
		private double msend;
		private double mrecv;

		Entity(double x, double osend, double orecv, double msend, double mrecv) {
			this.x = x;
			this.osend = osend;
			this.orecv = orecv;
			this.msend = msend;
			this.mrecv = mrecv;
		}

		public double getX() {
			return this.x;
		}
		
		public double getMobileSend(){
			return this.msend;
		}
		
		public double getMobileRecv(){
			return this.mrecv;
		}
		
		public double getOSend(){
			return this.osend;
		}
		
		public double getORecv(){
			return this.orecv;
		}

		public double getWifiSend() {
			return this.osend - msend;
		}

		public double getWifiRecv() {
			return this.orecv - mrecv;
		}
		
		public double getMobileTotal(){
			return getMobileSend() + getMobileRecv();
		}

		public double getWifiTotal() {
			return getWifiSend() + getWifiRecv();
		}

	}
}
