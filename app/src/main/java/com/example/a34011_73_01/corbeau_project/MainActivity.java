package com.example.a34011_73_01.corbeau_project;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button newGameButton = (Button)findViewById(R.id.newGame);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        Button creditsButton = (Button)findViewById(R.id.credit);
        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Credit.class);
                startActivity(intent);
            }
        });

        Button quitGameButton = (Button)findViewById(R.id.quit);
        quitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process.killProcess(Process.myPid());
            }
        });
    }
}
