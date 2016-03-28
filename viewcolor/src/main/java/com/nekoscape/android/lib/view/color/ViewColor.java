package com.nekoscape.android.lib.view.color;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ViewColor extends View {
	int colBG = 0;
	Paint paint = new Paint();
	RectF rbase = new RectF();

	public ViewColor(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		rbase.set(0, 0, getWidth(), getHeight());
		paint.setColor(colBG);
		canvas.drawRect(rbase, paint);
	}

	public void setColor(int col) {
		colBG = col;
	}
}
