package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {

	public MyViewGroup(Context context) {
		super(context);
	}
	
	public MyViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int left=getPaddingLeft();
		int top=getPaddingTop();
		int right=getPaddingRight();
		int bottom=getPaddingBottom();
		for (int i = 0; i < getChildCount(); i++) {
			View v=getChildAt(i);
			v.layout(l+left, t+top, r-right, b-bottom);
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//���������padding�Ļ�ֱ����������
		
		int childSize=MeasureSpec.getSize(widthMeasureSpec)-getPaddingLeft()*2;
		for (int i = 0; i < getChildCount(); i++) {
			View child=getChildAt(i);
			//������ͼ�Ŀ���޶��ڸ���ͼ�Ŀ������
			child.measure(MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.AT_MOST), 
					MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.AT_MOST));
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
