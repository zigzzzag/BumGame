package com.gogicompany.zigzzzag.bumgame.listener;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sbt-nikiforov-mo on 12/20/16.
 */

public interface OnScaleTouchListener {

    boolean onTouch(View v, MotionEvent e, float scale);
}
