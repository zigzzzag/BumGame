package com.gogicompany.zigzzzag.bumgame.gameobj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by sbt-nikiforov-mo on 12/16/16.
 */

public class RectObj extends GameObj {

    public RectObj(int x, int y, int width, int height, int dx, int dy) {
        super(x, y, width, height, dx, dy);
    }

    @Override
    public void draw(Canvas c) {
        c.drawRect(x, y, x + width, y + height, p);
        super.draw(c);
    }
}
