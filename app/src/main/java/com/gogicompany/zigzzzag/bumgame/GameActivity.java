package com.gogicompany.zigzzzag.bumgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {

    private GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gamePanel = new GamePanel(this);
        setContentView(gamePanel);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gamePanel.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
}
