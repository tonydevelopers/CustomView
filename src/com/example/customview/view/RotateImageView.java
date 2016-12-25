package com.example.customview.view;

import com.example.customview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RotateImageView extends ImageView {

	private int angle=90;//注意处理成正数
	
	public RotateImageView(Context context) {
		this(context, null, 0);
	
	}
	
	public RotateImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public RotateImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if(attrs!=null){
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RotateImageView);
			this.angle = a.getInt(R.styleable.RotateImageView_angle, 0);
			a.recycle();
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(angle%180==90){
			super.onMeasure(heightMeasureSpec,widthMeasureSpec);			
		}else{
			super.onMeasure(widthMeasureSpec,heightMeasureSpec);	
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		canvas.rotate(angle, getWidth()/2f, getHeight()/2f);
		super.onDraw(canvas);
		canvas.restore();
	}
	
	public void setAngle(int angle){
		this.angle=angle;
		this.invalidate();
	}
}
