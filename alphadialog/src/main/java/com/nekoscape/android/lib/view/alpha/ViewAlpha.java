package com.nekoscape.android.lib.view.alpha;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ViewAlpha extends View {
	int alpha = 0;
	Paint paint = new Paint();

	public ViewAlpha(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		paint.setAlpha(alpha);
	}

	public void setAlpha(int col) {
		alpha = col;
	}
}
