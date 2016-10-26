package com.example.a34011_73_01.corbeau_project;

import android.os.SystemClock;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.sql.Time;
import java.util.Timer;

/**
 * Created by 34011-73-09 on 26/10/2016.
 */

public class GameThread extends Thread {
    private boolean running;

    private WeakReference<GameActivity> activity;

    private Game game;

    private boolean doTurn;

    private final static long TIME_RESOLUTION = 1000;

    public GameThread(GameActivity activity) {
        this.activity = new WeakReference<GameActivity>(activity);
        game = new Game();
        game.setPlayer();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean getRunning() {
        return running;
    }

    @Override
    public void run() {
        long oldTime = SystemClock.elapsedRealtime();
        long newTime = 0;

        while(running) {
            Log.d("GameThread", "Running game thread!");
            newTime += (SystemClock.elapsedRealtime() - oldTime);
            Log.d("GameThread", "NewTime: " + newTime);
            if(newTime >= TIME_RESOLUTION) {
                Log.d("GameThread", "Game updated!");

                game.doTurn();

                activity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.get().updateBoard(game);
                    }
                });

                oldTime = SystemClock.elapsedRealtime();
                newTime = 0;
            }
            else {
                try {
                    Thread.sleep(1);
                }
                catch(InterruptedException e) {

                }
            }
        }
    }

    public void doTurn() {

    }
}
