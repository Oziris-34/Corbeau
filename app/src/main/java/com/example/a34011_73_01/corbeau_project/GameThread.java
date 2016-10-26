package com.example.a34011_73_01.corbeau_project;

import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

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

    private boolean playerTurnDone = false;

    private boolean doTurn;

    private final static long TIME_RESOLUTION = 1000;

    public GameThread(GameActivity activity) {
        this.activity = new WeakReference<GameActivity>(activity);
        game = new Game();
        game.setPlayer();
    }

    public boolean isPlayerTurnDone() {
        return playerTurnDone;
    }

    public void setPlayerTurnDone(boolean playerTurnDone) {
        this.playerTurnDone = playerTurnDone;
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
        playerTurnDone = false;

        while(running) {
            newTime += (SystemClock.elapsedRealtime() - oldTime);

            if(newTime >= TIME_RESOLUTION) {
                if(game.getCurrentPlayer() == game.getHumanPlayerID()) {
                    activity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.get().showButton();
                        }
                    });

                    if(playerTurnDone) {
                        game.doTurn();
                        playerTurnDone = false;
                    }
                }
                else {
                    activity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.get().hideButton();
                        }
                    });

                    game.doTurn();
                }

                activity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.get().updateBoard(game);
                    }
                });

                if(game.isGameFinished()) {
                    activity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast gameFinished = Toast.makeText(activity.get().getBaseContext(), "The game is finished!", Toast.LENGTH_LONG);
                            gameFinished.show();
                        }
                    });

                    running = false;
                }

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
}
