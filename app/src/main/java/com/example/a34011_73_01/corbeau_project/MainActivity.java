package com.example.a34011_73_01.corbeau_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMediaPlayer();

        Button newGameButton = (Button)findViewById(R.id.newGame);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMediaPlayer();
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        Button creditsButton = (Button)findViewById(R.id.credit);
        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMediaPlayer();
                Intent intent = new Intent(MainActivity.this, Credit.class);
                startActivity(intent);
            }
        });

        Button quitGameButton = (Button)findViewById(R.id.quit);
        quitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMediaPlayer();
                destroyMediaPlayer();
                Process.killProcess(Process.myPid());
            }
        });
    }

    private void createMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        if(mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    private void stopMediaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private void destroyMediaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
