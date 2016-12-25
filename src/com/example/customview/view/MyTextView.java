package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.rotate(-90);
		System.out.println("--->" + getHeight() + "---" + getWidth());
		canvas.translate(-getHeight(), 0);
		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		try {
			super.onMeasure(heightMeasureSpec, widthMeasureSpec);
			setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
		} finally {
		}
	}

}