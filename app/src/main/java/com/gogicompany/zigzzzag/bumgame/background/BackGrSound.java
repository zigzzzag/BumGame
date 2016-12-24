package com.gogicompany.zigzzzag.bumgame.background;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import com.gogicompany.zigzzzag.bumgame.R;

/**
 * Created by sbt-nikiforov-mo on 12/22/16.
 */

public class BackGrSound extends AsyncTask<Context, Void, Void> {

    public BackGrSound(Context ctx) {
    }

    @Override
    protected Void doInBackground(Context... params) {
        MediaPlayer mp = MediaPlayer.create(params[0], R.raw.background);
        mp.setLooping(true);
        mp.setVolume(0.5f, 0.5f);
        mp.start();

        return null;
    }
}
