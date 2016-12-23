package com.gogicompany.zigzzzag.bumgame.background;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by sbt-nikiforov-mo on 12/15/16.
 */

public class BackGrImage {

    private final Bitmap image;
    private int x, y, dx;

    public BackGrImage(Bitmap image) {
        this.image = image;
    }

    public void update() {
        x += dx;
        if (x < -image.getWidth()) {
            x = 0;
        }
    }

    public void draw(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        c.drawRect(0, 0, c.getWidth(), c.getHeight(), p);
        c.drawBitmap(image, x, y, null);
        if (x < 0) {
            c.drawBitmap(image, x + image.getWidth(), y, null);
        }
    }

    public void setVector(int dx) {
        this.dx = dx;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
