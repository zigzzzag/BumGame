package com.gogicompany.zigzzzag.bumgame.gameobj;

import android.graphics.Canvas;

/**
 * Created by sbt-nikiforov-mo on 12/19/16.
 */

public class CircleObj extends GameObj {

    private float r;

    public CircleObj(int x, int y, float r, int dx, int dy, int maxX, int maxY) {
        super(x, y, (int) r, (int) r, dx, dy, maxX, maxY);
        this.r = r;
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
            y = maxY + (int) r;
        }
    }
}
