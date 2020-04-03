package com.example.livedrawing;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

class ParticleSystem {
    boolean isRunning = false;
    private float duration;
    private ArrayList<Particle> particles;
    private Random random = new Random();

    public void init(int numParticles) {
        particles = new ArrayList<>();

        for (int i = 0; i < numParticles; i++) {
            float angle = random.nextInt(360);
            angle = angle * 3.14f / 180f;

            float speed = random.nextInt(10) + 1;

            PointF direction = new PointF((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);

            particles.add(new Particle(direction));
        }
    }

    public void update(long fps) {
        duration -= 1f / fps;

        for (Particle p : particles) {
            p.update(fps);
        }

        if (duration < 0)
            isRunning = false;
    }

    public void emitParticle(PointF startPosition) {
        isRunning = true;

        duration = 1f;

        for (Particle p : particles) {
            p.setPosition(startPosition);
        }
    }
}
