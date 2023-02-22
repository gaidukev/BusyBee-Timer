package com.gaidukev.busybeetimer;

import static java.lang.Math.min;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;

import androidx.annotation.Nullable;

public class Hexagon extends View {

    private Paint hexagonPaint;
    private int hexagonColor;
    private Path hexagonPath;

    public Hexagon(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        hexagonPaint = new Paint();
        hexagonPath = new Path();

        // get attributes specified in attrs.xml
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Hexagon, 0, 0);

        try {
            hexagonColor = a.getInteger(R.styleable.Hexagon_hexagonColor, 0);
        } finally {
            a.recycle();
        }

        hexagonPaint.setColor(hexagonColor);
        hexagonPaint.setStyle(Paint.Style.STROKE);
        hexagonPaint.setStrokeWidth(10f);
        hexagonPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas){

        hexagonPath.reset();

        int viewLeft = this.getLeft();
        int viewRight = this.getRight();
        int viewTop = this.getTop();
        int viewBottom = this.getBottom();

        int viewWidthHalf = ( viewRight - viewLeft) / 2;
        int viewHeightHalf = (viewBottom - viewTop) / 2;

        int size = min(viewWidthHalf * 2, viewHeightHalf * 2);

        float section = (float) (2 * Math.PI / 6); // 6 sides!
        int radius = size / 2;

        hexagonPath.moveTo(
                (viewWidthHalf + radius * (float) Math.cos(0)),
                (viewHeightHalf + radius * (float) Math.sin(0))
        );

        for (int i = 1; i < 6; i++){
            hexagonPath.lineTo(
                    (viewWidthHalf + radius * (float) Math.cos(section * i)),
                    (viewHeightHalf + radius * (float) Math.sin(section * i))
            );
        }

        hexagonPath.close();
        canvas.drawPath(hexagonPath, hexagonPaint);


    }

}
