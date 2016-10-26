package com.example.a34011_73_01.corbeau_project;

import android.util.Log;

import java.util.Random;

/**
 * Created by 34011-73-09 on 25/10/2016.
 */

public class Game {
    private int humanPlayerID;

    private int currentPlayer;

    private int playerHarvestedGreenFruit;
    private int playerHarvestedOrangeFruit;
    private int playerHarvestedYellowFruit;
    private int playerHarvestedVioletFruit;

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

    public int getPlayerHarvestedGreenFruit() {
        return playerHarvestedGreenFruit;
    }

    public void setPlayerHarvestedGreenFruit(int playerHarvestedGreenFruit) {
        this.playerHarvestedGreenFruit = playerHarvestedGreenFruit;
    }

    public int getPlayerHarvestedOrangeFruit() {
        return playerHarvestedOrangeFruit;
    }

    public void setPlayerHarvestedOrangeFruit(int playerHarvestedOrangeFruit) {
        this.playerHarvestedOrangeFruit = playerHarvestedOrangeFruit;
    }

    public int getPlayerHarvestedYellowFruit() {
        return playerHarvestedYellowFruit;
    }

    public void setPlayerHarvestedYellowFruit(int playerHarvestedYellowFruit) {
        this.playerHarvestedYellowFruit = playerHarvestedYellowFruit;
    }

    public int getPlayerHarvestedVioletFruit() {
        return playerHarvestedVioletFruit;
    }

    public void setPlayerHarvestedVioletFruit(int playerHarvestedVioletFruit) {
        this.playerHarvestedVioletFruit = playerHarvestedVioletFruit;
    }

    public Game() {
        remainingGreenFruit = remainingOrangeFruit = remainingVioletFruit = remainingYellowFruit = 4;
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
                if(currentPlayer == humanPlayerID) {
                    ++playerHarvestedGreenFruit;
                }
                Log.d("Game", "Green!");
            }break;

            case 2: {
                --remainingOrangeFruit;
                if(currentPlayer == humanPlayerID) {
                    ++playerHarvestedOrangeFruit;
                }
                Log.d("Game", "Orange!");
            }break;

            case 3: {
                --remainingVioletFruit;
                if(currentPlayer == humanPlayerID) {
                    ++playerHarvestedVioletFruit;
                }
                Log.d("Game", "Violet!");
            }break;

            case 4: {
                --remainingYellowFruit;
                if(currentPlayer == humanPlayerID) {
                    ++playerHarvestedYellowFruit;
                }
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

    public boolean isGameFinished() {
        boolean isYellowTreeEmpty = remainingYellowFruit <= 0;
        boolean isGreenTreeEmpty = remainingGreenFruit <= 0;
        boolean isVioletTreeEmpty = remainingVioletFruit <= 0;
        boolean isOrangeTreeEmpty = remainingOrangeFruit <= 0;

        return (isGreenTreeEmpty || isOrangeTreeEmpty || isVioletTreeEmpty || isYellowTreeEmpty);
    }
}
