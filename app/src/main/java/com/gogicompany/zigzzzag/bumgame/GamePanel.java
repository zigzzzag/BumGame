package com.gogicompany.zigzzzag.bumgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gogicompany.zigzzzag.bumgame.background.BackGrImage;
import com.gogicompany.zigzzzag.bumgame.background.BackGrSound;
import com.gogicompany.zigzzzag.bumgame.gameobj.GameObj;
import com.gogicompany.zigzzzag.bumgame.gameobj.GameObjFactory;
import com.gogicompany.zigzzzag.bumgame.gameobj.Score;
import com.gogicompany.zigzzzag.bumgame.particle.ParticleEffect;

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
    private List<GameObj> flyObjects = new ArrayList<>();
    private final ParticleEffect effect = new ParticleEffect();
    private Score score;

    SoundPool soundPool;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GamePanel(Context ctx) {
        super(ctx);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        this.thread = new MainThread(getHolder(), this);
        setFocusable(true);

        this.bgs = new BackGrSound(ctx);
        bgs.execute(ctx);

//        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPool = new SoundPool.Builder().build();
        soundPool.load(ctx, R.raw.past, 1);
        soundPool.load(ctx, R.raw.explosion1, 2);
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

    private float getMaxX() {
        return getWidth() / scale;
    }

    private float getMaxY() {
        return getHeight() / scale;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bgi = new BackGrImage(BitmapFactory.decodeResource(getResources(), R.drawable.bg2));
        bgi.setVector(-5);

        scale = getHeight() / (float) bgi.getHeight();

        float maxX = getMaxX();
        float maxY = getMaxY();

        score = new Score(maxX, maxY);
        System.out.println("getWidth() * scale, getHeight() * scale" + getWidth() * scale + " ," + getHeight() * scale);
        score.position(Score.Side.DOWN, Score.Side.RIGHT);

        for (int i = 0; i < 5; i++) {
            GameObj go = GameObjFactory.INSTANCE.randomObj((int) maxX, (int) maxY);
            this.flyObjects.add(go);
        }

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                touch(e);
                break;
            }
            default:
                break;
        }

        return true;
    }

    private void touch(MotionEvent e) {
        List<GameObj> dueObjects = new ArrayList<>();

        for (GameObj go : flyObjects) {
            if (go.onTouch(this, e, scale)) {
                score.addScore(10);
                dueObjects.add(go);
                effect.addPoints((int) (e.getX() / scale), (int) (e.getY() / scale), 50);
                soundPool.play(2, 1.0f, 1.0f, 1, 0, 1f);
            } else {
                soundPool.play(1, 1.0f, 1.0f, 1, 0, 1f);
            }
        }

        for (int i = 0; i < dueObjects.size(); i++) {
            flyObjects.add(GameObjFactory.INSTANCE.randomObj((int) getMaxX(), (int) getMaxY()));
        }
        flyObjects.removeAll(dueObjects);
    }

    public void update() {
        bgi.update();
        for (GameObj go : flyObjects) {
            go.update();
        }
        effect.update();
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
                effect.draw(c);
                score.draw(c);
            } finally {
                c.restoreToCount(savedState);
            }
        }
    }
}
