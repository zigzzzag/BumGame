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
    protected int dx;
    protected int dy;

    protected Paint p = new Paint();

    public GameObj(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

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

//        System.out.println("x: " + this.x + "<" + xTouch + "<" + (this.x + width));
//        System.out.println("y: " + y + "<" + yTouch + "<" + (y + height));

        if (!isHit(xTouch, yTouch)) {
            return false;
        }

        Random rand = new Random();
        p.setColor(Color.argb(150, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));

        return true;
    }

    protected abstract boolean isHit(float xTouch, float yTouch);

    public abstract void randomProps();

    public void reset() {
        randomProps();
    }

    public void draw(Canvas c) {
    }
}
