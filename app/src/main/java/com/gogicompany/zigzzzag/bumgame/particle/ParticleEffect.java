package com.gogicompany.zigzzzag.bumgame.particle;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by sbt-nikiforov-mo on 12/20/16.
 */

public class ParticleEffect {

    private final List<EPoint> points = new CopyOnWriteArrayList<>();

    public ParticleEffect() {
    }

    public void addPoints(int x, int y, int count) {
        for (int i = 0; i < count; i++) {
            points.add(new EPoint(x, y));
        }
    }

    public void draw(Canvas c) {
        for (EPoint p : points) {
            p.draw(c);
        }
    }

    public void update() {
        List<EPoint> duePoints = new ArrayList<>();
        points.forEach(p -> {
            if (p.isAlive()) {
                p.update();
            } else {
                duePoints.add(p);
            }
        });

        points.removeAll(duePoints);
    }
}
