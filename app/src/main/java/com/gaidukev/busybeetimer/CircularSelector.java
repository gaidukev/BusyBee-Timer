package com.gaidukev.busybeetimer;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class CircularSelector extends View {
    private int widthX;
    private int widthY;

    private float sweepAngle = 90f;

    // get colors
    private int circleColor, backgroundColor, progressColor, textColor;
    // label text
    private String circleText;
    // paint for drawing custom view
    private Paint circlePaint;
    public CircularSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        circlePaint = new Paint();

        // get attributes specified in attrs.xml
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircularSelector, 0, 0);

        try {
            circleText = a.getString(R.styleable.CircularSelector_circleLabel);
            circleColor = a.getInteger(R.styleable.CircularSelector_circleColor, 0);
            backgroundColor = a.getInteger(R.styleable.CircularSelector_backgroundColor, 0);
            progressColor = a.getInteger(R.styleable.CircularSelector_progressColor, 0);
            textColor = a.getInteger(R.styleable.CircularSelector_textColor, 0);
        } finally {
            a.recycle();
        }

        circlePaint.setARGB(1, 255, 77, 0);
        circlePaint.setStrokeWidth(1f);
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL); // can also use Paint.Style.STROKE, with strokeWidth = ...
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthX = widthMeasureSpec;
        widthY = heightMeasureSpec;

        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int minh = MeasureSpec.getSize(w) + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);

        //setMeasuredDimension(w, h);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int viewLeft = this.getLeft();
        int viewRight = this.getRight();
        int viewTop = this.getTop();
        int viewBottom = this.getBottom();

        int viewWidthHalf = ( viewRight - viewLeft) / 2;
        int viewHeightHalf = (viewBottom - viewTop) / 2;

        int radius = 0;
        if (viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-30;
        else
            radius=viewWidthHalf-30;

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleColor);

        //canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
        canvas.drawArc(viewLeft, viewTop, viewRight, viewBottom, -90f, sweepAngle, true, circlePaint);
        // negative sweep angle: counter clockwise
        // 0 is at 0 degrees and then degrees go clockwise


        circlePaint.setColor(backgroundColor);
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
        System.out.println(radius);

        circlePaint.setColor(textColor);
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(radius/3);
        canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);

    }

    public void updateSweepAngle(float xVal, float yVal){
        sweepAngle = 0;
        float halfWidth = (this.getRight() - this.getLeft()) / 2;
        float halfHeight = (this.getBottom() - this.getTop()) / 2;


        float refAngle = (float) Math.toDegrees(Math.atan(Math.abs((xVal - halfHeight)/ (yVal - halfWidth))));


        if ((xVal > (halfWidth)) && (yVal > halfHeight)){
            sweepAngle = 180 - refAngle; // Q2
        }
        else if ((xVal > halfWidth) && (yVal < halfHeight)){
            sweepAngle = 0 + refAngle; // Q1
        } else if ((xVal < halfWidth) && (yVal < halfHeight)){
            sweepAngle = 360 - refAngle; // Q4
        } else {
            sweepAngle = 180 + refAngle; //
        }
        // update text displayed in center
        circleText = String.valueOf(sweepAngle);

        invalidate(); // forces the view to re-draw itself

    }
}
