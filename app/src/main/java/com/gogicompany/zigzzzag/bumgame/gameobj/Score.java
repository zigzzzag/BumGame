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
    private float x;
    private float y;
    private final Paint p;
    private static final int DELTA_SPD = 10;
    private static final int INDENT = 20;
    private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    public Score() {
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

    public void flow(Side... flows) {
        for (Side s : flows) {
            switch (s) {
                case RIGHT:
                    x = 400;
                    break;
                case DOWN:
                    y = 400;
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
        c.drawText(Integer.toString(score), x, y, p);
    }

    public void addScore(final int delta) {
        THREAD_POOL.submit(new Runnable() {
            @Override
            public void run() {
                int d = delta;
                while (d > 0) {
                    d--;
                    score++;
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
