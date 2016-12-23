package com.gogicompany.zigzzzag.bumgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gogicompany.zigzzzag.bumgame.background.BackGrImage;
import com.gogicompany.zigzzzag.bumgame.background.BackGrSound;
import com.gogicompany.zigzzzag.bumgame.gameobj.GameObj;
import com.gogicompany.zigzzzag.bumgame.gameobj.GameObjFactory;
import com.gogicompany.zigzzzag.bumgame.gameobj.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-nikiforov-mo on 12/15/16.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private BackGrImage bgi;
    private final BackGrSound bgs;
    private float scale;
    private final List<GameObj> flyObjects = new ArrayList<>();
    private final Score score = new Score();

    public GamePanel(Context ctx) {
        super(ctx);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
        score.flow(Score.Side.DOWN, Score.Side.RIGHT);

        this.thread = new MainThread(getHolder(), this);
        setFocusable(true);

        this.bgs = new BackGrSound(ctx);
        bgs.execute(ctx);

        for (int i = 0; i < 1; i++) {
            GameObj go = GameObjFactory.INSTANCE.randomObj();
            this.flyObjects.add(go);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        bgs.cancel(true);

        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bgi = new BackGrImage(BitmapFactory.decodeResource(getResources(), R.drawable.bg2));
        bgi.setVector(-5);

        scale = getHeight() / (float) bgi.getHeight();

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                    for (GameObj go : flyObjects) {
                        if (go.onTouch(this, e, scale)) {
                            score.addScore(go.getScore());
                        } else {
                            System.out.println("past");
                        }
                    }
                break;
            }
            default:
                break;
        }

        return true;
    }

    public void update() {
        bgi.update();
        for (GameObj go : flyObjects) {
            go.update();
        }
    }

    @Override
    public void draw(Canvas c) {
        if (c != null) {
            final int savedState = c.save();
            c.scale(scale, scale);
            try {
                bgi.draw(c);
                for (GameObj go : flyObjects) {
                    go.draw(c);
                }
                score.draw(c);
            } finally {
                c.restoreToCount(savedState);
            }
        }
    }
}
