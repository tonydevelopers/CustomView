package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.example.customview.R;

public class RotateLinearLayout
  extends LinearLayout
{
  private int asK = 0;
  private int asL = 0;
  private int asM = 0;
  private Context mContext;
  private int mDegree = 0;
  private int screenWidth;
  private int screenHeight;
  
  public RotateLinearLayout(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
  }
  
  public RotateLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    this.asK = this.mContext.getResources().getDimensionPixelSize(R.dimen.panel_top_height);
    this.asL = this.mContext.getResources().getDimensionPixelSize(R.dimen.panel_bottom_height);
    this.asM = this.mContext.getResources().getDimensionPixelSize(R.dimen.toast_offset);
    
    DisplayMetrics dm=this.getResources().getDisplayMetrics();
    screenWidth=dm.widthPixels;
    screenHeight=dm.heightPixels;
  }
  
  public void draw(Canvas canvas)
  {
    int i = 0;
    canvas.save();
    if (canvas.getClipBounds().top == 0) {
    	
    }
    //获取事件响应触摸区域
    Rect localRect = new Rect();
    getHitRect(localRect);
    int j = screenWidth / 2;
    int k = screenHeight / 2;
    int m = localRect.centerX();
    int n = localRect.centerY();
    int i1;
    switch (this.mDegree)
    {
    default: 
      i1 = 0;
    }
    for (;;)
    {
    	canvas.translate(i + (j - m), i1 + (k - n));
      if ((this.mDegree == 0) || (this.mDegree == 90) || (this.mDegree == 180) || (this.mDegree == 270)) {
    	  canvas.rotate(-this.mDegree, m, n);
      }
      super.draw(canvas);
      canvas.restore();
//      return;
//      i1 = -k + this.asK + this.asM;
//      i = 0;
//      continue;
//      i = -j + this.asM;
//      i1 = -(this.asL - this.asK) / 2;
//      continue;
//      i1 = k - this.asL - this.asM;
//      i = 0;
//      continue;
//      i = j - this.asM;
//      i1 = -(this.asL - this.asK) / 2;
    }
  }
  
  public int getDegree()
  {
    return this.mDegree;
  }
  
  public void setDegree(int paramInt)
  {
    this.mDegree = paramInt;
  }
}
