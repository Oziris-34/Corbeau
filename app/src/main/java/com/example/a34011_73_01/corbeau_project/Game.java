package com.example.a34011_73_01.corbeau_project;

import android.util.Log;

import java.util.Random;

/**
 * Created by 34011-73-09 on 25/10/2016.
 */

public class Game {
    private int humanPlayerID;

    private int currentPlayer;

    private int numFruitPlayerOne;
    private int numFruitPlayerTwo;

    private int remainingYellowFruit;
    private int remainingOrangeFruit;
    private int remainingVioletFruit;
    private int remainingGreenFruit;

    private int ravenPosition;

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getHumanPlayerID() {
        return humanPlayerID;
    }

    public void setHumanPlayerID(int humanPlayerID) {
        this.humanPlayerID = humanPlayerID;
    }

    public int getNumFruitPlayerOne() {
        return numFruitPlayerOne;
    }

    public void setNumFruitPlayerOne(int numFruitPlayerOne) {
        this.numFruitPlayerOne = numFruitPlayerOne;
    }

    public void incNumFruitPlayerOne() {
        numFruitPlayerOne++;
    }

    public int getNumFruitPlayerTwo() {
        return numFruitPlayerTwo;
    }

    public void setNumFruitPlayerTwo(int numFruitPlayerTwo) {
        this.numFruitPlayerTwo = numFruitPlayerTwo;
    }

    public void incNumFruitPlayerTwo() {
        numFruitPlayerTwo++;
    }

    public int getRemainingYellowFruit() {
        return remainingYellowFruit;
    }

    public void setRemainingYellowFruit(int remainingYellowFruit) {
        this.remainingYellowFruit = remainingYellowFruit;
    }

    public void decRemainingYellowFruit() {
        remainingYellowFruit--;
    }

    public int getRemainingOrangeFruit() {
        return remainingOrangeFruit;
    }

    public void setRemainingOrangeFruit(int remainingOrangeFruit) {
        this.remainingOrangeFruit = remainingOrangeFruit;
    }

    public void decRemainingOrangeFruit() {
        remainingOrangeFruit--;
    }

    public int getRemainingVioletFruit() {
        return remainingVioletFruit;
    }

    public void setRemainingVioletFruit(int remainingVioletFruit) {
        this.remainingVioletFruit = remainingVioletFruit;
    }

    public void decRemainingVioletFruit() {
        remainingVioletFruit--;
    }

    public int getRemainingGreenFruit() {
        return remainingGreenFruit;
    }

    public void setRemainingGreenFruit(int remainingGreenFruit) {
        this.remainingGreenFruit = remainingGreenFruit;
    }

    public void decRemainingGreenFruit() {
        remainingGreenFruit--;
    }

    public int getRavenPosition() {
        return ravenPosition;
    }

    public void setRavenPosition(int ravenPosition) {
        this.ravenPosition = ravenPosition;
    }

    public void incRavenPosition() {
        ravenPosition++;
    }

    public Game() {
        remainingGreenFruit = remainingOrangeFruit = remainingVioletFruit = remainingYellowFruit = 4;
        numFruitPlayerOne = numFruitPlayerTwo = 0;
        currentPlayer = 0;
        ravenPosition = 1;
    }

    public void setPlayer() {
        Random random = new Random();
        humanPlayerID = random.nextInt(2);
    }

    public int launchDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public void nextPlayer() {
        currentPlayer = ++currentPlayer % 2;
    }

    public void doTurn() {
        Log.d("Game", "PLayer_" + currentPlayer);

        int result = launchDice();

        switch(result) {
            case 1: {
                --remainingGreenFruit;
                Log.d("Game", "Green!");
            }break;

            case 2: {
                --remainingOrangeFruit;
                Log.d("Game", "Orange!");
            }break;

            case 3: {
                --remainingVioletFruit;
                Log.d("Game", "Violet!");
            }break;

            case 4: {
                --remainingYellowFruit;
                Log.d("Game", "Yellow!");
            }break;

            case 5: {
                Log.d("Game", "Skip turn!");
            }break;

            case 6: {
                ++ravenPosition;
                Log.d("Game", "Raven!");
            }break;
        }

        nextPlayer();
    }
}
