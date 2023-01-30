package com.gaidukev.busybeetimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircularSelector extends View {
    public CircularSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawCircle(3f, 4f, 10f, new Paint());

    }
}
