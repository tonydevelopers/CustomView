package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
//import com.quvideo.xiaoying.common.ComUtil;

//public class RotateCircleProgress
//  extends CircleProgress
//{
//  private int aJw = 0;
//  private int aJx = 0;
//  protected int mBarLength = 200;
//  protected int mBarThickness = 21;
//  protected int mDegree = 0;
//  private int mHeight = 0;
//  private int mWidth = 0;
//  
//  public RotateCircleProgress(Context paramContext, AttributeSet paramAttributeSet)
//  {
//    super(paramContext, paramAttributeSet);
//    this.mPaintWidth = ComUtil.dpToPixel(paramContext, this.mPaintWidth);
//    this.mCircleAttribute.cT(this.mPaintWidth);
//    this.mSidePaintInterval = ComUtil.dpToPixel(paramContext, this.mSidePaintInterval);
//    this.mCircleAttribute.mSidePaintInterval = this.mSidePaintInterval;
//  }
//  
//  public int getMainProgress()
//  {
//    try
//    {
//      int i = super.getMainProgress();
//      return i;
//    }
//    finally
//    {
//      localObject = finally;
//      throw ((Throwable)localObject);
//    }
//  }
//  
//  protected void onDraw(Canvas paramCanvas)
//  {
//    switch (this.mDegree)
//    {
//    }
//    for (;;)
//    {
//      super.onDraw(paramCanvas);
//      return;
//      paramCanvas.rotate(-90.0F);
//      paramCanvas.translate(-getHeight(), 0.0F);
//      continue;
//      paramCanvas.rotate(180.0F);
//      paramCanvas.translate(-getWidth(), -getHeight());
//      continue;
//      paramCanvas.rotate(90.0F);
//      paramCanvas.translate(0.0F, -getWidth());
//      continue;
//      paramCanvas.rotate(0.0F);
//      paramCanvas.translate(0.0F, 0.0F);
//    }
//  }
//  
//  protected void onMeasure(int paramInt1, int paramInt2)
//  {
//    try
//    {
//      super.onMeasure(paramInt2, paramInt1);
//      setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
//      return;
//    }
//    finally
//    {
//      localObject = finally;
//      throw ((Throwable)localObject);
//    }
//  }
//  
//  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
//  {
//    if ((this.mDegree == 90) || (this.mDegree == 270))
//    {
//      super.onSizeChanged(paramInt2, paramInt1, paramInt4, paramInt3);
//      return;
//    }
//    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
//  }
//  
//  public void setBarParams(int paramInt1, int paramInt2)
//  {
//    this.mBarLength = paramInt1;
//    this.mBarThickness = paramInt2;
//  }
//  
//  public void setDegree(int paramInt)
//  {
//    this.mDegree = paramInt;
//  }
//  
//  public void setMainProgress(int paramInt)
//  {
//    try
//    {
//      super.setMainProgress(paramInt);
//      return;
//    }
//    finally
//    {
//      localObject = finally;
//      throw ((Throwable)localObject);
//    }
//  }
//}
