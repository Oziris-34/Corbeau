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

        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        Button newGameButton = (Button)findViewById(R.id.newGame);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                mediaPlayer.stop();
                startActivity(intent);
            }
        });

        Button creditsButton = (Button)findViewById(R.id.credit);
        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Credit.class);
                mediaPlayer.stop();
                startActivity(intent);
            }
        });

        Button quitGameButton = (Button)findViewById(R.id.quit);
        quitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                Process.killProcess(Process.myPid());
            }
        });
    }
}
