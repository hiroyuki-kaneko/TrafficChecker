package com.nekoscape.android.ntc.common;

public enum ByteUnit {
	BYTE(0) ,KBYTE(1) ,MBYTE(2),GBYTE(3);
		
	private static int UNIT = 1024;
	private int power;
	
	ByteUnit(int n){
		this.power = n;
	}
	
	public double toByte(double data){
		return conv(power,data);
	}

	public double toKByte(double data){
		return conv(1-power,data);
	}
	
	public double toMByte(double data){
		return conv(2-power, data);
	}
	
	private double conv(int n, double data){
		
		return data/Math.pow(UNIT, n);
	}
}
