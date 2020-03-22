package com.example.canvasdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    Bitmap bitmap;
    ImageView imageView;
    Canvas canvas;
    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int widthInPixel = 800;
        int heightInPixel = 600;

        bitmap = Bitmap.createBitmap(widthInPixel, heightInPixel, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        imageView = new ImageView(this);
        paint = new Paint();

        canvas.drawColor(Color.argb(255, 0, 0, 255));

        paint.setTextSize(100);
        paint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawText("Hello Tarun", 100, 100, paint);

        paint.setColor(Color.argb(255, 212, 207, 62));

        canvas.drawCircle(400, 250, 100, paint);

        imageView.setImageBitmap(bitmap);

        setContentView(imageView);
    }
}
