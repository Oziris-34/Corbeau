package com.example.a34011_73_01.corbeau_project;

import android.media.MediaPlayer;

import java.lang.ref.WeakReference;

/**
 * Created by 34011-73-09 on 26/10/2016.
 */

public class SoundThread extends Thread {
    private boolean running;

    private WeakReference<GameActivity> activity;

    public SoundThread(GameActivity activity) {
        this.activity = new WeakReference<GameActivity>(activity);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean getRunning() {
        return running;
    }

    @Override
    public void run() {
        /*
        MediaPlayer bgm = MediaPlayer.create(activity.get().getBaseContext(), R.raw.bgm);
        bgm.setLooping(true);
        bgm.start();
        */
        while(running) {

        }
        //bgm.stop();
    }
}
