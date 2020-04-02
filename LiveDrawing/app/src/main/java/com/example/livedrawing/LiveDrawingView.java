package com.example.livedrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LiveDrawingView extends SurfaceView {

    private final boolean DEBBUGING = true;
    private final int MILLI_SECONDS = 1000;
    private SurfaceHolder ourHolder;
    private Canvas canvas;
    private Paint paint;
    private long FPS;
    private int screenX;
    private int screenY;

    private int fontSize;
    private int fontMargin;

    public LiveDrawingView(Context context, int x, int y) {
        super(context);
    }

}
