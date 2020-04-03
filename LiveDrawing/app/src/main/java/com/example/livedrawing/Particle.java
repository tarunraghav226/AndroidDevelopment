package com.example.livedrawing;

import android.graphics.PointF;

public class Particle {
    private PointF velocity;
    private PointF position;

    public Particle(PointF direction) {
        velocity = new PointF();
        position = new PointF();

        velocity.x = direction.x;
        velocity.y = direction.y;
    }
}
