package com.gogicompany.zigzzzag.bumgame.gameobj;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by sbt-nikiforov-mo on 12/16/16.
 */

public class RectObj extends GameObj {

    private int width;
    private int height;

    public RectObj(int maxX, int maxY) {
        super(maxX, maxY);
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

    @Override
    protected boolean isHit(float xTouch, float yTouch) {
        return xTouch >= x && xTouch <= (x + width) &&
                yTouch >= y && yTouch <= (y + height);
    }

    @Override
    public void randomProps() {
        Random rand = new Random();

        width = rand.nextInt(50) + 50;
        height = rand.nextInt(50) + 50;
        x = rand.nextInt(maxX - width);

        dx = 0;
        dy = -(rand.nextInt(10) + 5);

        p.setColor(Color.argb(150, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
    }
}
