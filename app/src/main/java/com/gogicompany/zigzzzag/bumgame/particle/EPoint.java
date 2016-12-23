package com.gogicompany.zigzzzag.bumgame.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.gogicompany.zigzzzag.bumgame.drawable.Drawable;

import java.util.Random;

/**
 * Created by sbt-nikiforov-mo on 12/20/16.
 */

public class EPoint implements Drawable {

    public float x;
    public float y;
    public float dx;
    public float dy;
    private final long liveTime;
    private final long startTime = System.currentTimeMillis();
    private Paint p = new Paint();

    public EPoint(int x, int y) {
        this.x = x;
        this.y = y;
        init();

        Random r = new Random();
        this.liveTime = r.nextInt(3000) + 1000;
    }

    public EPoint(int x, int y, long liveTime) {
        this.x = x;
        this.y = y;
        this.liveTime = liveTime;
        init();
    }

    private void init() {
        Random rand = new Random();
        this.dx = rand.nextFloat() * 20 - 10;
        this.dy = -rand.nextFloat() * 10;

        setRandomColor();
    }

    public void setRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        p.setColor(Color.rgb(r, g, b));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, 3, p);
    }

    public boolean isAlive() {
        return (System.currentTimeMillis() - startTime) < liveTime;
    }

    public void update() {
        x += dx;
        y += dy;

        dx *= 0.99;
        dy += 0.6;
        dy *= 0.99;
    }
}
