package com.gogicompany.zigzzzag.bumgame.gameobj;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by sbt-nikiforov-mo on 12/19/16.
 */

public class CircleObj extends GameObj {

    private int r;

    public CircleObj(int maxX, int maxY) {
        super(maxX, maxY);
    }

    @Override
    public void draw(Canvas c) {
        c.drawCircle(x, y, r, p);
        super.draw(c);
    }

    @Override
    protected boolean isHit(float xTouch, float yTouch) {
        return Math.pow(xTouch - x, 2) + Math.pow(yTouch - y, 2) <= Math.pow(r, 2);
    }

    @Override
    protected void updateBoundary() {
        if (y + r < 0) {
            y = maxY + r;
        }
    }

    @Override
    public void randomProps() {
        Random rand = new Random();

        r = rand.nextInt(50) + 50;
        x = rand.nextInt(maxX - 2 * r) + r;
        y = maxY;

        dx = 0;
        dy = -(rand.nextInt(10) + 5);

        p.setColor(Color.argb(150, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
    }
}
