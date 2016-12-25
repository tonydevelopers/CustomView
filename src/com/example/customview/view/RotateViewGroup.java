package com.example.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.customview.R;

/**
 * 所有的角度均會被处理成90的整数---只能包含一个布局管理器---只处理了第一个容器(子视图)
 * 
 * @author chengkai.gan
 * 
 */
public class RotateViewGroup extends ViewGroup {

	private int angle;
	private boolean flag;//是否使用外部设置旋转角度

	private final Matrix rotateMatrix = new Matrix();

	private final Rect viewRectRotated = new Rect();

	private final RectF tempRectF1 = new RectF();
	private final RectF tempRectF2 = new RectF();

	private final float[] viewTouchPoint = new float[2];
	private final float[] childTouchPoint = new float[2];

	private boolean angleChanged = true;

	public RotateViewGroup(Context context) {
		this(context, null);
	}

	public RotateViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);// 表示子视图由自己绘制
	}

	public View getView() {
		return getChildAt(0);
	}

	@Override
	// 如果尺寸被重新计算，那么onLayout也需要重新计算
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("onMeasure");
		final View view = getView();
		if (view != null) {
			final LayoutParams layoutParams = (LayoutParams) view
					.getLayoutParams();
			if(flag==false){
				if (angle != layoutParams.angle) {
					angle = layoutParams.angle;
					angleChanged = true;
				}
			}else{
				if (angle != layoutParams.angle) {
					layoutParams.angle = angle;
					angleChanged = true;
				}
			}

			if (Math.abs(angle % 180) == 90) {// 先计算好子视图，然后根据计算的宽高设置本视图的宽高
				measureChild(view, heightMeasureSpec, widthMeasureSpec);
				setMeasuredDimension(
						resolveSize(view.getMeasuredHeight(), widthMeasureSpec),
						resolveSize(view.getMeasuredWidth(), heightMeasureSpec));
			} else {
				measureChild(view, widthMeasureSpec, heightMeasureSpec);
				setMeasuredDimension(
						resolveSize(view.getMeasuredWidth(), widthMeasureSpec),
						resolveSize(view.getMeasuredHeight(), heightMeasureSpec));
			}
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		System.out.println("onLayout");
		if (angleChanged || changed) {
			final RectF layoutRect = tempRectF1;
			final RectF layoutRectRotated = tempRectF2;
			layoutRect.set(0, 0, r - l, b - t);
			rotateMatrix.setRotate(angle, layoutRect.centerX(),
					layoutRect.centerY());
			rotateMatrix.mapRect(layoutRectRotated, layoutRect);
			layoutRectRotated.round(viewRectRotated);
			angleChanged = false;
		}

		final View view = getView();
		if (view != null) {
			view.layout(viewRectRotated.left, viewRectRotated.top,
					viewRectRotated.right, viewRectRotated.bottom);
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		canvas.save();
		canvas.rotate(-angle, getWidth() / 2f, getHeight() / 2f);
		super.dispatchDraw(canvas);
		canvas.restore();
		System.out.println("dispatchDraw");
	}

	@Override
	public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
		invalidate();
		return super.invalidateChildInParent(location, dirty);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		viewTouchPoint[0] = event.getX();
		viewTouchPoint[1] = event.getY();

		// 根据旋转后的视图使用父视图触摸事件映射到子视图上
		rotateMatrix.mapPoints(childTouchPoint, viewTouchPoint);
		// 将这个触摸事件映射的触摸点分发到对应的子视图---并返回响应结果
		event.setLocation(childTouchPoint[0], childTouchPoint[1]);
		boolean result = super.dispatchTouchEvent(event);// 返回子视图是否响应
		// 子视图事件分发后---响应到父视图的触摸事件---子视图返回false的话父视图继续响应
		event.setLocation(viewTouchPoint[0], viewTouchPoint[1]);

		return result;
	}

	@Override
	// 在子视图通过getLayoutParams时会回调这个方法来创建LayoutParams---解析时调用该方法来创建
	public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new RotateViewGroup.LayoutParams(getContext(), attrs);
	}

	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
		return layoutParams instanceof RotateViewGroup.LayoutParams;
	}

	@Override
	// 在子视图通过getLayoutParams时会回调这个方法来创建LayoutParams
	protected ViewGroup.LayoutParams generateLayoutParams(
			ViewGroup.LayoutParams layoutParams) {
		return new RotateViewGroup.LayoutParams(layoutParams);
	}

	public static class LayoutParams extends ViewGroup.LayoutParams {

		public int angle;

		public LayoutParams(Context context, AttributeSet attrs) {
			super(context, attrs);
			final TypedArray a = context.obtainStyledAttributes(attrs,
					R.styleable.RotateLayout_Layout);
			int degree = a.getInt(R.styleable.RotateLayout_Layout_layout_angle,
					0);
			System.out.println("degree--->" + degree);
			this.angle = calculateDegree(degree);

			a.recycle();
		}

		public LayoutParams(ViewGroup.LayoutParams layoutParams) {
			super(layoutParams);
		}

	}

	// 强旋转角度计算成90的整数倍
	private static int calculateDegree(int degree) {
		if (degree < 0) {// 先处理成正的角度
			degree %= 360;
			degree += 360;
		}
		int result = Math.round(degree / 90f) * 90;

		return result;
	}

	public void setAngle(int angle) {
		this.angle = calculateDegree(angle);
		this.flag=true;
		this.requestLayout();
	}

}
