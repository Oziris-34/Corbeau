package com.example.a34011_73_01.corbeau_project;

import java.util.Random;

/**
 * Created by 34011-73-09 on 25/10/2016.
 */

public class Game {
    private int humanPlayerID;

    private int numFruitPlayerOne;
    private int numFruitPlayerTwo;

    private int remainingYellowFruit;
    private int remainingOrangeFruit;
    private int remainingVioletFruit;
    private int remainingGreenFruit;

    private int ravenPosition;

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
}