package com.gaidukev.busybeetimer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Hexagon extends View {

    private Paint hexagonPaint;
    private int hexagonColor;
    private Path hexagonPath;

    public Hexagon(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        hexagonPaint = new Paint();

        // get attributes specified in attrs.xml
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Hexagon, 0, 0);

        try {
            hexagonColor = a.getInteger(R.styleable.Hexagon_hexagonColor, 0);
        } finally {
            a.recycle();
        }

        hexagonPaint.setColor(hexagonColor);
        hexagonPaint.setStrokeWidth(1f);
        hexagonPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas){


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

        hexagonPath.reset();
        hexagonPath.moveTo((float) radius, (float) viewWidthHalf, (float) viewHeightHalf);

    }

}
