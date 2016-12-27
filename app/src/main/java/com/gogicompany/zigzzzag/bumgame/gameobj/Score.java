package com.gogicompany.zigzzzag.bumgame.gameobj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sbt-nikiforov-mo on 12/21/16.
 */

public class Score {

    private int score;
    private int delta;
    private float x;
    private float maxX;
    private float y;
    private float maxY;
    private final Paint p;
    private static final int INDENT = 50;
    private static final ExecutorService THREAD_POOL = Executors.newSingleThreadExecutor();
    private boolean updating = true;

    {
        THREAD_POOL.submit(() -> {
            while (updating) {
                if (delta > 0) {
                    int d = delta / 10 + 1;
                    delta -= d;
                    score += d;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public Score(float maxX, float maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

        this.p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(50);
    }

    public enum Side {
        RIGHT,
        DOWN,
        LEFT,
        UP
    }

    public void position(Side... flows) {
        for (Side s : flows) {
            switch (s) {
                case RIGHT:
                    x = maxX - INDENT;
                    break;
                case DOWN:
                    y = maxY - INDENT;
                    break;
                case LEFT:
                    x = INDENT;
                    break;
                case UP:
                    y = INDENT;
                    break;
                default:
                    break;
            }
        }
    }

    public void draw(Canvas c) {
        String txt = Integer.toString(score);
        float txtW = p.measureText(txt);
        c.drawText(txt, x - txtW, y, p);
    }

    public void addScore(final int delta) {
        this.delta += delta;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }
}
