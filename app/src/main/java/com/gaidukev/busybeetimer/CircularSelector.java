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

        int viewWidthHalf = ( viewRight - viewLeft) / 2;//parent.getWidth()/2;// this.getMeasuredWidth()/2; //
        int viewHeightHalf = (viewBottom - viewTop) / 2;//parent.getHeight()/2;// this.getMeasuredHeight()/2; //

        int radius = 0;
        if (viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-30;
        else
            radius=viewWidthHalf-30;

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleColor);

        //canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
        canvas.drawArc(viewLeft, viewTop, viewRight, viewBottom, 180f, 100f, true, circlePaint);

        circlePaint.setColor(backgroundColor);
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
        System.out.println(radius);

        circlePaint.setColor(textColor);
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(20);
        canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);

    }
}
