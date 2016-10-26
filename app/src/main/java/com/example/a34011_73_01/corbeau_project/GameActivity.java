package com.example.a34011_73_01.corbeau_project;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by 34011-73-09 on 20/10/2016.
 */

public class GameActivity extends AppCompatActivity {

    private GameThread gameThread;
    private SoundThread soundThread;

    private ImageView greenFruit;
    private ImageView orangeFruit;
    private ImageView violetFruit;
    private ImageView yellowFruit;

    private ImageView raven;

    private ImageButton de;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        greenFruit = (ImageView)findViewById(R.id.orchardGreenFruit);
        orangeFruit = (ImageView)findViewById(R.id.orchardOrangeFruit);
        violetFruit = (ImageView)findViewById(R.id.orchardVioletFruit);
        yellowFruit = (ImageView)findViewById(R.id.orchardYellowFruit);

        raven = (ImageView)findViewById(R.id.raven);

        de = (ImageButton)findViewById(R.id.imageButton7);
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameThread.setPlayerTurnDone(true);
            }
        });

        startThreads();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopThreads();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        stopThreads();
    }

    private void startThreads() {
        gameThread = new GameThread(GameActivity.this);
        gameThread.setRunning(true);
        gameThread.start();

        soundThread = new SoundThread();
        soundThread.setRunning(true);
        soundThread.start();
    }

    private void stopThreads() {
        if(gameThread.getRunning()) {
            gameThread.setRunning(false);
            boolean gameThreadTerminated = true;
            while (gameThreadTerminated) {
                Log.d("GameActivity", "Terminating game thread!");
                try {
                    gameThread.join();
                    gameThreadTerminated = false;
                } catch (InterruptedException e) {
                }
            }
        }

        if(soundThread.getRunning()) {
            soundThread.setRunning(false);
            boolean soundThreadTerminated = true;
            while (soundThreadTerminated) {
                Log.d("GameActivity", "Terminating sound thread!");
                try {
                    soundThread.join();
                    soundThreadTerminated = false;
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void showButton() {
        de.setAlpha(1.0f);
        de.setFocusable(true);
    }

    public void hideButton() {
        de.setAlpha(0.0f);
        de.setFocusable(false);
    }

/*
    private void playerTurn() {
        int result = game.launchDice();

        switch(result) {
            case 1: {
                game.decRemainingGreenFruit();
            }break;

            case 2: {
                game.decRemainingYellowFruit();
            }break;

            case 3: {
                game.decRemainingVioletFruit();
            }break;

            case 4: {
                game.decRemainingOrangeFruit();
            }break;

            case 5: {
                game.incRavenPosition();
            }break;

            case 6: {

            }break;
        }
    }
*/
    public void updateBoard(Game game) {

        switch(game.getRemainingGreenFruit()) {
            case 4: {
                greenFruit.setImageDrawable(getResources().getDrawable(R.drawable.vert4));
            }break;

            case 3: {
                greenFruit.setImageDrawable(getResources().getDrawable(R.drawable.vert3));
            }break;

            case 2: {
                greenFruit.setImageDrawable(getResources().getDrawable(R.drawable.vert2));
            }break;

            case 1: {
                greenFruit.setImageDrawable(getResources().getDrawable(R.drawable.vert1));
            }break;

            default: {
                greenFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingOrangeFruit()) {
            case 4: {
                orangeFruit.setImageDrawable(getResources().getDrawable(R.drawable.orange4));
            }break;

            case 3: {
                orangeFruit.setImageDrawable(getResources().getDrawable(R.drawable.orange3));
            }break;

            case 2: {
                orangeFruit.setImageDrawable(getResources().getDrawable(R.drawable.orange2));
            }break;

            case 1: {
                orangeFruit.setImageDrawable(getResources().getDrawable(R.drawable.orange1));
            }break;

            default: {
                orangeFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingVioletFruit()) {
            case 4: {
                violetFruit.setImageDrawable(getResources().getDrawable(R.drawable.violet4));
            }break;

            case 3: {
                violetFruit.setImageDrawable(getResources().getDrawable(R.drawable.violet3));
            }break;

            case 2: {
                violetFruit.setImageDrawable(getResources().getDrawable(R.drawable.violet2));
            }break;

            case 1: {
                violetFruit.setImageDrawable(getResources().getDrawable(R.drawable.violet1));
            }break;

            default: {
                violetFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingYellowFruit()) {
            case 4: {
                yellowFruit.setImageDrawable(getResources().getDrawable(R.drawable.rouge4));
            }break;

            case 3: {
                yellowFruit.setImageDrawable(getResources().getDrawable(R.drawable.rouge3));
            }break;

            case 2: {
                yellowFruit.setImageDrawable(getResources().getDrawable(R.drawable.rouge2));
            }break;

            case 1: {
                yellowFruit.setImageDrawable(getResources().getDrawable(R.drawable.rouge1));
            }break;

            default: {
                yellowFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRavenPosition()) {
            case 8: {
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau_gagne));
            }break;

            case 7: {
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau6));
            }break;

            case 6: {
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau5));
            }break;

            case 5: {
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau4));
            }break;

            case 4: {
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau3));
            }break;

            case 3: {
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau2));
            }break;

            case 2: {
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau1));
            }break;

            default: {
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau_depart));
            }break;
        }
    }
}
