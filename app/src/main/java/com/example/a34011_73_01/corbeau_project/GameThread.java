package com.example.a34011_73_01.corbeau_project;

/**
 * Created by 34011-73-09 on 26/10/2016.
 */

public class GameThread extends Thread {
    private boolean running;
    private long oldTime;

    public GameThread() {

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while(running) {

        }
    }
}
