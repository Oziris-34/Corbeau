package com.example.a34011_73_01.corbeau_project;

import java.util.Random;

/**
 * Created by 34011-73-09 on 25/10/2016.
 */

class Game {
    private String playerName;

    private int nbTurn;

    private int humanPlayerID;

    private int currentPlayer;

    private int remainingYellowFruit;
    private int remainingOrangeFruit;
    private int remainingVioletFruit;
    private int remainingGreenFruit;

    private int ravenPosition;

    String getPlayerName() {
        return playerName;
    }

    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    int getHumanPlayerID() {
        return humanPlayerID;
    }

    public void setHumanPlayerID(int humanPlayerID) {
        this.humanPlayerID = humanPlayerID;
    }

    int getRemainingYellowFruit() {
        return remainingYellowFruit;
    }

    public void setRemainingYellowFruit(int remainingYellowFruit) {
        this.remainingYellowFruit = remainingYellowFruit;
    }

    int getRemainingOrangeFruit() {
        return remainingOrangeFruit;
    }

    public void setRemainingOrangeFruit(int remainingOrangeFruit) {
        this.remainingOrangeFruit = remainingOrangeFruit;
    }

    int getRemainingVioletFruit() {
        return remainingVioletFruit;
    }

    public void setRemainingVioletFruit(int remainingVioletFruit) {
        this.remainingVioletFruit = remainingVioletFruit;
    }

    int getRemainingGreenFruit() {
        return remainingGreenFruit;
    }

    public void setRemainingGreenFruit(int remainingGreenFruit) {
        this.remainingGreenFruit = remainingGreenFruit;
    }

    int getRavenPosition() {
        return ravenPosition;
    }

    public void setRavenPosition(int ravenPosition) {
        this.ravenPosition = ravenPosition;
    }

    public void incRavenPosition() {
        ravenPosition++;
    }

    int getNbTurn() {
        return nbTurn;
    }

    public void setNbTurn(int nbTurn) {
        this.nbTurn = nbTurn;
    }

    Game() {
        reset();
    }

    private void reset() {
        nbTurn = 1;
        remainingGreenFruit = remainingOrangeFruit = remainingVioletFruit = remainingYellowFruit = 4;
        currentPlayer = 0;
        ravenPosition = 1;
    }

    void setPlayer() {
        Random random = new Random();
        humanPlayerID = random.nextInt(2);
    }

    private int launchDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    void nextPlayer() {
        currentPlayer = ++currentPlayer % 2;
    }

    int doTurn() {
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

    boolean hasCorbackWon() {
        return (ravenPosition >= 8);
    }

    boolean isTreeEmpty() {
        boolean isYellowTreeEmpty = remainingYellowFruit <= 0;
        boolean isGreenTreeEmpty = remainingGreenFruit <= 0;
        boolean isVioletTreeEmpty = remainingVioletFruit <= 0;
        boolean isOrangeTreeEmpty = remainingOrangeFruit <= 0;

        return (isGreenTreeEmpty || isOrangeTreeEmpty || isVioletTreeEmpty || isYellowTreeEmpty);
    }
}
