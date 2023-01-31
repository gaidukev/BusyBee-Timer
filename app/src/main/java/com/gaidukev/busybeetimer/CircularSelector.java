package com.gaidukev.busybeetimer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class CircularSelector extends View {
    private Paint paint;
    private int widthX;
    private int widthY;
    public CircularSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setARGB(1, 255, 77, 0);
        paint.setStrokeWidth(1f);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL); // can also use Paint.Style.STROKE, with strokeWidth = ...
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthX = widthMeasureSpec;
        widthY = heightMeasureSpec;

    }

    @Override
    public void onDraw(Canvas canvas){
        System.out.println("HHHHHHHHHHHHHHH");
        super.onDraw(canvas);
        canvas.drawCircle(widthX-0, widthY+5, 100000f, paint);

    }
}
