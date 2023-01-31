package com.gaidukev.busybeetimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircularSelector extends View {
    private Paint paint;
    private int widthX;
    private int widthY;
    public CircularSelector(Context context, int defStyle) {
        super(context);
        paint = new Paint();
        paint.setARGB(1, 255, 77, 0);
        paint.setStrokeWidth(1f);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL); // can also use Paint.Style.STROKE, with strokeWidth = ...
        setWillNotDraw(false);
    }

    public CircularSelector(Context context) {
        super(context);
        paint = new Paint();
        paint.setARGB(1, 255, 77, 0);
        paint.setStrokeWidth(1f);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL); // can also use Paint.Style.STROKE, with strokeWidth = ...
        setWillNotDraw(false);
    }

    public CircularSelector(Context context, AttributeSet attrs, int defStyle) {
        super(context);
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
        super.onDraw(canvas);
        canvas.drawCircle(widthX, widthY, 10f, paint);

    }
}
