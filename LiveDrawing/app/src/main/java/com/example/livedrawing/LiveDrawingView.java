package com.example.livedrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LiveDrawingView extends SurfaceView implements Runnable {

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

    private Thread thread;
    private volatile boolean drawing;
    private boolean paused = true;
    private RectF resetButton;
    private RectF togglePauseButton;

    public LiveDrawingView(Context context, int x, int y) {
        super(context);

        screenX = x;
        screenY = y;

        ourHolder = getHolder();
        paint = new Paint();

        fontSize = screenX / 20;
        fontMargin = screenX / 75;

        resetButton = new RectF(0, 0, 100, 100);
        togglePauseButton = new RectF(0, 150, 100, 250);
    }

    private void draw() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 200, 0, 0));
            canvas.drawRect(resetButton, paint);
            canvas.drawRect(togglePauseButton, paint);
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(fontSize);

            if (DEBBUGING) {
                printDebuggingText();
            }

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void printDebuggingText() {
        int debugSize = fontSize / 2;
        int debugStart = 150;

        paint.setTextSize(fontSize);
        canvas.drawText("FPS: " + FPS, 10, debugStart + debugSize, paint);
    }

    @Override
    public void run() {
        while (drawing) {
            long frameStartTime = System.currentTimeMillis();
            if (!paused) {
                update();
            }
            draw();
            long timeThisFrame =
                    System.currentTimeMillis() - frameStartTime;
            if (timeThisFrame > 0) {
                FPS = MILLI_SECONDS / timeThisFrame;
            }
        }
    }

    public void pause() {
        drawing = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    public void resume() {
        drawing = true;
        thread = new Thread(this);
        thread.start();
    }

    private void update() {

    }
}