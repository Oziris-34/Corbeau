package com.example.a34011_73_01.corbeau_project;

/**
 * Created by 34011-73-09 on 26/10/2016.
 */

public class SoundThread extends Thread {
    private boolean running;

    public SoundThread() {

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean getRunning() {
        return running;
    }

    @Override
    public void run() {
        while(running) {

        }
    }
}
