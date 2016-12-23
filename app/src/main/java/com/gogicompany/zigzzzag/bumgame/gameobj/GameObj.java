package com.gogicompany.zigzzzag.bumgame.gameobj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.gogicompany.zigzzzag.bumgame.drawable.Drawable;
import com.gogicompany.zigzzzag.bumgame.listener.OnScaleTouchListener;
import com.gogicompany.zigzzzag.bumgame.particle.ParticleEffect;
import com.gogicompany.zigzzzag.bumgame.updatable.Updatable;

import java.util.Random;

/**
 * Created by sbt-nikiforov-mo on 12/16/16.
 */

public abstract class GameObj implements OnScaleTouchListener, Drawable, Updatable {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int dx;
    protected int dy;
    private int score = 10;

    protected Paint p = new Paint();

    private final ParticleEffect effects = new ParticleEffect();

    public GameObj(int x, int y, int width, int height, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;

        p.setColor(Color.GREEN);
    }

    @Override
    public void update() {
//        x += dx;
//        y += dy;
        effects.update();
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

//        pe.addPoints(50, x, y);
        effects.addPoints(x, y, 50);
//        Random r1 = new Random();
//        for (int i = 0; i < 100; i++) {
//            int x1 = r1.nextInt(200) - 100;
//            int y1 = r1.nextInt(200) - 100;
//
//            effects.addPoints(x + x1, y + y1, 50);
//        }

        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        p.setColor(Color.rgb(r, g, b));


        String sDown = null;
        String sMove = null;
        String sUp = null;

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                sDown = "Down: " + xTouch + "," + yTouch;
                sMove = "";
                sUp = "";
                break;
            case MotionEvent.ACTION_MOVE: // движение
                sMove = "Move: " + x + "," + y;
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up: " + x + "," + y;
                break;
        }
        System.out.println(sDown + "\n" + sMove + "\n" + sUp);
        return true;
    }

    protected boolean isHit(float xTouch, float yTouch) {
        return xTouch >= x && xTouch <= (x + width) &&
                yTouch >= y && yTouch <= (y + height);
    }

    @Override
    public void draw(Canvas c) {
        effects.draw(c);
    }

    public int getScore() {
        return score;
    }
}
