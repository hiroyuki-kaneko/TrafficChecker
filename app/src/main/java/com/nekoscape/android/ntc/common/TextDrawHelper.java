package com.nekoscape.android.ntc.common;

import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

public class TextDrawHelper {

	private final float baseX;
	private final float baseY;
	private final String text;

	public TextDrawHelper(float centerX, float centerY, String text, Paint paint) {
		FontMetrics fontMetrics = paint.getFontMetrics();

		// 文字列の幅を取得
		if(null == text){
			text = "No Wi-Fi Service";
		}
		float textWidth = paint.measureText(text);

		// 中心にしたいX座標から文字列の幅の半分を引く
		baseX = centerX - textWidth / 2;
		// 中心にしたいY座標からAscentとDescentの半分を引く
		baseY = centerY - (fontMetrics.ascent + fontMetrics.descent) / 2;
		
		this.text = text;

	}
	
	public float getX(){
		return baseX;
	}
	
	public float getY(){
		return baseY;
	}
	
	public String getText(){
		return text;
	}
}
