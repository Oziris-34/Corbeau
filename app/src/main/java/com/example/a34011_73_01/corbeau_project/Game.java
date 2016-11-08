package com.example.a34011_73_01.corbeau_project;

import java.util.Random;

/**
 * Created by 34011-73-09 on 25/10/2016.
 */

public class Game {
    private String playerName;

    private int nbTurn;

    private int humanPlayerID;

    private int currentPlayer;

    private int remainingYellowFruit;
    private int remainingOrangeFruit;
    private int remainingVioletFruit;
    private int remainingGreenFruit;

    private int ravenPosition;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

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

    public int getRemainingOrangeFruit() {
        return remainingOrangeFruit;
    }

    public void setRemainingOrangeFruit(int remainingOrangeFruit) {
        this.remainingOrangeFruit = remainingOrangeFruit;
    }

    public int getRemainingVioletFruit() {
        return remainingVioletFruit;
    }

    public void setRemainingVioletFruit(int remainingVioletFruit) {
        this.remainingVioletFruit = remainingVioletFruit;
    }

    public int getRemainingGreenFruit() {
        return remainingGreenFruit;
    }

    public void setRemainingGreenFruit(int remainingGreenFruit) {
        this.remainingGreenFruit = remainingGreenFruit;
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

    public int getNbTurn() {
        return nbTurn;
    }

    public void setNbTurn(int nbTurn) {
        this.nbTurn = nbTurn;
    }

    public Game() {
        reset();
    }

    public void reset() {
        nbTurn = 1;
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

    public int doTurn() {
        ++nbTurn;

        int result = launchDice();
        switch(result) {
            case 1: {
                --remainingGreenFruit;
            }break;

            case 2: {
                --remainingOrangeFruit;
            }break;

            case 3: {
                --remainingVioletFruit;
            }break;

            case 4: {
                --remainingYellowFruit;
            }break;

            case 5: {
            }break;

            case 6: {
                ++ravenPosition;
            }break;
        }

        return result;
    }

    public boolean hasCorbackWon() {
        return (ravenPosition >= 8);
    }

    public boolean isTreeEmpty() {
        boolean isYellowTreeEmpty = remainingYellowFruit <= 0;
        boolean isGreenTreeEmpty = remainingGreenFruit <= 0;
        boolean isVioletTreeEmpty = remainingVioletFruit <= 0;
        boolean isOrangeTreeEmpty = remainingOrangeFruit <= 0;

        return (isGreenTreeEmpty || isOrangeTreeEmpty || isVioletTreeEmpty || isYellowTreeEmpty);
    }
}
