package com.gogicompany.zigzzzag.bumgame.gameobj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.gogicompany.zigzzzag.bumgame.listener.OnScaleTouchListener;

import java.util.Random;

/**
 * Created by sbt-nikiforov-mo on 12/16/16.
 */

public abstract class GameObj implements OnScaleTouchListener {

    protected int x;
    protected int y;
    protected final int maxX;
    protected final int maxY;
    protected int width;
    protected int height;
    protected int dx;
    protected int dy;

    protected Paint p = new Paint();

    public GameObj(int x, int y, int width, int height, int dx, int dy, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;

        Random rand = new Random();
        int randColor = Color.argb(150, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        p.setColor(randColor);
    }

    public void update() {
        x += dx;
        y += dy;
        updateBoundary();
    }

    protected void updateBoundary() {
        if (y < 0) {
            y = maxY;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent e, float scale) {
        float xTouch = e.getX() / scale;
        float yTouch = e.getY() / scale;

        System.out.println("x: " + this.x + "<" + xTouch + "<" + (this.x + width));
        System.out.println("y: " + y + "<" + yTouch + "<" + (y + height));

        if (!isHit(xTouch, yTouch)) {
            return false;
        }

        Random rand = new Random();
        p.setColor(Color.argb(150, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));

        return true;
    }

    protected boolean isHit(float xTouch, float yTouch) {
        return xTouch >= x && xTouch <= (x + width) &&
                yTouch >= y && yTouch <= (y + height);
    }

    public void draw(Canvas c) {
    }

    public void reset() {
        Random r = new Random();
        x = r.nextInt(maxX);
        y = maxY;

        p.setColor(Color.argb(150, r.nextInt(256), r.nextInt(256), r.nextInt(256)));
    }
}
