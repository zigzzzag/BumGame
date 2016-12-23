package com.gogicompany.zigzzzag.bumgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by sbt-nikiforov-mo on 12/15/16.
 */

public class MainThread extends Thread {

    private static final int FPS = 30;
    private double averageFPS;
    private double averageFPSMy;
    private final SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking canvas for pixel editing
            canvas = this.surfaceHolder.lockCanvas();
            try {
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1_000_000;
            waitTime = targetTime - timeMillis;

                try {
                    sleep(waitTime);
                } catch (Exception e) {
//                    e.printStackTrace();
                }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1_000_000);
                averageFPSMy = 1000 / (totalTime / (frameCount * 1_000_000));
                frameCount = 0;
                totalTime = 0;
                System.out.println("averageFPS=" + averageFPS + "; averageFPSMy=" + averageFPSMy);
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
