package com.example.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.example.customview.R;

/**
 * Created by Administrator on 2014/9/9.
 */
public class RotateTextView extends TextView {

	public static final int ROTATE_CENTER = 0;
	public static final int ROTATE_LEFT = 1;
	public static final int ROTATE_RIGHT = 2;
	public static final int ROTATE_TOP = 3;
	public static final int ROTATE_BOTTOM = 4;

	private Context mContext;
	private TextPaint mTextPaint = new TextPaint();
	private boolean isShadowOn = true;
	private int mDegree = 0;
	private Paint mPaint = new Paint();

	public RotateTextView(Context context) {
		super(context, null);
	}

	public RotateTextView(Context context, AttributeSet attrs) {
		super(context, attrs, android.R.attr.textViewStyle);
		this.mContext = context;
		this.setGravity(Gravity.CENTER);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.RotateView);
		this.isShadowOn = a
				.getBoolean(R.styleable.RotateView_isShadowOn, false);
		this.mDegree = a.getInt(R.styleable.RotateView_rotation, 0);

		a.recycle();
	}

	private void update() {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.restore();
		int pl = getPaddingLeft();
		int pr = getPaddingRight();
		int pt = getPaddingTop();
		int pb = getPaddingBottom();
		// �������ݿ��
		float rw = getWidth() - pl - pr;// ����Ŀ�ȼ�ȥ�����ڱ߾�
		float rh = getHeight() - pt - pb;// ����ĸ߶ȼ�ȥ�����ڱ߾�
		this.mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		this.mPaint.setColor(getCurrentTextColor());
		this.mPaint.setTextSize(getTextSize());

		// ascent����baseline֮�����ַ���ߴ��ľ���
		// descent����baseline֮�����ַ���ʹ��ľ���
		// leading:����һ���ַ���descent����һ�е�ascent֮��ľ��룬Ҳ���������м�Ŀհ׾���
		float baselineTop = this.mPaint.ascent();
		float baselineBottom = this.mPaint.descent();
		// �����ַ�����ռ�õ�����
		float strpx = this.mPaint.measureText(getText().toString());
		System.out.println("top-" + baselineTop + " bottom-" + baselineBottom
				+ " strpx-" + strpx);

		this.mPaint.setTextAlign(Paint.Align.CENTER);
		if (this.isShadowOn) {// ������Ӱ
			this.mPaint.setShadowLayer(1f, 1f, 1f, Color.BLACK);
		}
		canvas.rotate(-mDegree, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
		super.draw(canvas);
		// for (int i8 = 0; i8 <= k; i8++) {
		// paramCanvas.drawText(arrayOfString[i8], n + i4 / 2, i5 / 2 + f2 *
		// calculate(k + 1, i8 + 1) / 2.0F + f2 / 2.0F - this.mPaint.descent(),
		// this.mPaint);
		// }
		// this.mPaint.setTextAlign(Paint.Align.RIGHT);
		// if (this.aJO) {
		// this.mPaint.setShadowLayer(1.0F, 1.0F, 1.0F, -16777216);
		// }
		// paramCanvas.rotate(-this.aJy, n + i4 - f2 / 2.0F, i1 + f2 / 2.0F);
		// paramCanvas.drawText(str1.toString(), n + i4, -this.mPaint.ascent(),
		// this.mPaint);
		// }
	}

	public void setDegree(int mDegree) {
		this.mDegree = mDegree;
		update();
	}

	public void setDegree(int mDegree, long paramLong) {
		this.mDegree = mDegree;
		update();
	}

	public void setRotateLocation(Paint.Align align) {
		this.mPaint.setTextAlign(align);
	}

	public void setShadow(boolean isShadowOn) {
		this.isShadowOn = isShadowOn;
	}

}
