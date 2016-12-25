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
 * ���еĽǶȾ����������90������---ֻ�ܰ���һ�����ֹ�����---ֻ�����˵�һ������(����ͼ)
 * 
 * @author chengkai.gan
 * 
 */
public class RotateViewGroup extends ViewGroup {

	private int angle;
	private boolean flag;//�Ƿ�ʹ���ⲿ������ת�Ƕ�

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
		setWillNotDraw(false);// ��ʾ����ͼ���Լ�����
	}

	public View getView() {
		return getChildAt(0);
	}

	@Override
	// ����ߴ类���¼��㣬��ôonLayoutҲ��Ҫ���¼���
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

			if (Math.abs(angle % 180) == 90) {// �ȼ��������ͼ��Ȼ����ݼ���Ŀ�����ñ���ͼ�Ŀ��
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

		// ������ת�����ͼʹ�ø���ͼ�����¼�ӳ�䵽����ͼ��
		rotateMatrix.mapPoints(childTouchPoint, viewTouchPoint);
		// ����������¼�ӳ��Ĵ�����ַ�����Ӧ������ͼ---��������Ӧ���
		event.setLocation(childTouchPoint[0], childTouchPoint[1]);
		boolean result = super.dispatchTouchEvent(event);// ��������ͼ�Ƿ���Ӧ
		// ����ͼ�¼��ַ���---��Ӧ������ͼ�Ĵ����¼�---����ͼ����false�Ļ�����ͼ������Ӧ
		event.setLocation(viewTouchPoint[0], viewTouchPoint[1]);

		return result;
	}

	@Override
	// ������ͼͨ��getLayoutParamsʱ��ص��������������LayoutParams---����ʱ���ø÷���������
	public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new RotateViewGroup.LayoutParams(getContext(), attrs);
	}

	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
		return layoutParams instanceof RotateViewGroup.LayoutParams;
	}

	@Override
	// ������ͼͨ��getLayoutParamsʱ��ص��������������LayoutParams
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

	// ǿ��ת�Ƕȼ����90��������
	private static int calculateDegree(int degree) {
		if (degree < 0) {// �ȴ�������ĽǶ�
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
