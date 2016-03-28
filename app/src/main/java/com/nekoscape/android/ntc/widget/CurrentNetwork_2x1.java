package com.nekoscape.android.ntc.widget;


public class CurrentNetwork_2x1 extends CurrentNetwork {
	
	private static final int widget_with = 250;
	private static final int widget_hight = 100;
//	private static final int text_size = 25;

	@Override
	int getWidth() {
		return widget_with;
	}

	@Override
	int getHight() {
		return widget_hight;
	}
	
//	private static Bitmap getCurrentNetrowrkWidget(NetworkStatus status, String str) {
//		
//		Bitmap bitmap = Bitmap.createBitmap(widget_with, widget_hight, Bitmap.Config.ARGB_8888);
//		Canvas canvas = new Canvas(bitmap);
//
//		Paint rectPaint = new Paint();
//		rectPaint.setAntiAlias(true);
//		rectPaint.setARGB(255, 0, 0, 0);
//		
//		canvas.drawRoundRect(new RectF(0, 0, widget_with, widget_hight), 20, 20, rectPaint);		
//
//		 Paint paint = new Paint();
//		paint.setAntiAlias(true);
//		paint.setARGB(255, 255, 255, 255);
//		paint.setTextSize(text_size);
//		
//		TextDrawHelper ssidHelper = new TextDrawHelper(widget_with/2, 21, status.getSSID(), paint);
//		TextDrawHelper trafficHelper = new TextDrawHelper(widget_with/2, 51, str, paint);
//
//		canvas.drawText(ssidHelper.getText(), ssidHelper.getX(), ssidHelper.getY(), paint);
//		canvas.drawText(trafficHelper.getText(), trafficHelper.getX(), trafficHelper.getY(), paint);
//		Log.d("canvas", "paint widget");
//		return bitmap;
//	}

}
