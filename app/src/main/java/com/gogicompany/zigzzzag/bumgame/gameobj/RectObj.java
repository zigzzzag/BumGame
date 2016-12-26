package com.gogicompany.zigzzzag.bumgame.gameobj;

import android.graphics.Canvas;

/**
 * Created by sbt-nikiforov-mo on 12/16/16.
 */

public class RectObj extends GameObj {

    public RectObj(int x, int y, int width, int height, int dx, int dy, int maxX, int maxY) {
        super(x, y, width, height, dx, dy, maxX, maxY);
    }

    @Override
    public void draw(Canvas c) {
        c.drawRect(x, y, x + width, y + height, p);
        super.draw(c);
    }

    @Override
    protected void updateBoundary() {
        if (y + height < 0) {
            y = maxY;
        }
    }
}
