package com.example.a34011_73_01.corbeau_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by 34011-73-09 on 20/10/2016.
 */

public class GameActivity extends AppCompatActivity {

    private GameThread gameThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameThread = new GameThread();
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    protected void onDestroy() {
        Log.d("GameActivity", "Destroying thread!");
        super.onDestroy();

        gameThread.setRunning(false);

        boolean retry = true;
        while(retry) {
            try {
                gameThread.join();
                retry = false;
            }
            catch(InterruptedException e) {
                Log.d("GameActivity", "Failed to join game thread!");
            }
        }
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

    private void updateBoard() {

        switch(game.getRemainingGreenFruit()) {
            case 4: {
                greenFruit.setImageResource(R.drawable.vert_4);
            }break;

            case 3: {
                greenFruit.setImageResource(R.drawable.vert_3);
            }break;

            case 2: {
                greenFruit.setImageResource(R.drawable.vert_2);
            }break;

            case 1: {
                greenFruit.setImageResource(R.drawable.vert_1);
            }break;

            default: {

            }break;
        }

        switch(game.getRemainingOrangeFruit()) {
            case 4: {
                orangeFruit.setImageResource(R.drawable.orange_4);
            }break;

            case 3: {
                orangeFruit.setImageResource(R.drawable.orange_3);
            }break;

            case 2: {
                orangeFruit.setImageResource(R.drawable.orange_2);
            }break;

            case 1: {
                orangeFruit.setImageResource(R.drawable.orange_1);
            }break;

            default: {

            }break;
        }

        switch(game.getRemainingVioletFruit()) {
            case 4: {
                violetFruit.setImageResource(R.drawable.violet_4);
            }break;

            case 3: {
                violetFruit.setImageResource(R.drawable.violet_3);
            }break;

            case 2: {
                violetFruit.setImageResource(R.drawable.violet_2);
            }break;

            case 1: {
                violetFruit.setImageResource(R.drawable.violet_1);
            }break;

            default: {

            }break;
        }

        switch(game.getRemainingYellowFruit()) {
            case 4: {
                yellowFruit.setImageResource(R.drawable.rouge_4);
            }break;

            case 3: {
                yellowFruit.setImageResource(R.drawable.rouge_3);
            }break;

            case 2: {
                yellowFruit.setImageResource(R.drawable.rouge_2);
            }break;

            case 1: {
                yellowFruit.setImageResource(R.drawable.rouge_1);
            }break;

            default: {

            }break;
        }

        switch(game.getRavenPosition()) {

        }
    }
    */
}
