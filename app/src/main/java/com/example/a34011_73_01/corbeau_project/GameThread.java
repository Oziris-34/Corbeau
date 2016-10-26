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
        game = new Game(this.activity);
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
        long changeTime = 0;
        playerTurnDone = false;

        game.setPlayer();

        while(running) {
            newTime += (SystemClock.elapsedRealtime() - oldTime);

            if(newTime >= TIME_RESOLUTION) {
                if (game.getCurrentPlayer() == game.getHumanPlayerID()) {
                    activity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.get().showButton();
                        }
                    });

                    if (playerTurnDone) {
                        game.doTurn();
                        playerTurnDone = false;
                    }
                } else {
                    changeTime += newTime;

                    if (changeTime >= 10000) {
                        activity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity.get().hideButton();
                            }
                        });

                        game.doTurn();
                        changeTime = 0;
                    }
                }

                activity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.get().updateBoard(game);
                    }
                });

                if(game.hasCorbackWon()) {
                    activity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast gameFinished = Toast.makeText(activity.get().getBaseContext(), "The game is finished and the raven has won, your orchard is ruined!", Toast.LENGTH_LONG);
                            gameFinished.show();
                        }
                    });

                    running = false;
                }

                if(game.isGameFinished()) {
                    activity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String successfulPlayer = "";
                            if(game.getCurrentPlayer() == game.getHumanPlayerID()) { //The computer wins because the current player is the player for the next turn
                                successfulPlayer = " The computer has won! He gets to sell the orchard!";
                            }
                            else {
                                successfulPlayer = " You have won! Your orchard is saved!";
                            }

                            Toast gameFinished = Toast.makeText(activity.get().getBaseContext(), "The game is finished!" + successfulPlayer, Toast.LENGTH_LONG);
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
