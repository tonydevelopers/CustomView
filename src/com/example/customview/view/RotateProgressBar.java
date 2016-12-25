package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class RotateProgressBar extends ProgressBar {
	protected int mBarLength = 200;
	protected int mBarThickness = 21;
	protected int mDegree = 0;

	public RotateProgressBar(Context paramContext) {
		super(paramContext);
	}

	public RotateProgressBar(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public RotateProgressBar(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		switch (this.mDegree) {
		case -90:
			paramCanvas.rotate(-90.0F);
			paramCanvas.translate(-getHeight(), 0.0F);
			break;
		case 180:
			paramCanvas.rotate(180.0F);
			paramCanvas.translate(-getWidth(), -getHeight());
			break;
		case 90:
			paramCanvas.rotate(90.0F);
			paramCanvas.translate(0.0F, -getWidth());
			break;
		case 0:
			paramCanvas.rotate(0.0F);
			paramCanvas.translate(0.0F, 0.0F);
			break;
		}

	}

	protected void onMeasure(int paramInt1, int paramInt2) {
		try {
			super.onMeasure(paramInt2, paramInt1);
			setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
			return;
		} finally {
		}
	}

	protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) {
		if ((this.mDegree == 90) || (this.mDegree == 270)) {
			super.onSizeChanged(paramInt2, paramInt1, paramInt4, paramInt3);
			return;
		}
		super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public void setBarParams(int paramInt1, int paramInt2) {
		this.mBarLength = paramInt1;
		this.mBarThickness = paramInt2;
	}

	public void setDegree(int paramInt) {
		this.mDegree = paramInt;
	}

	public void setProgress(int paramInt) {
		super.setProgress(paramInt);
		onSizeChanged(getWidth(), getHeight(), 0, 0);
		invalidate();
	}
}
