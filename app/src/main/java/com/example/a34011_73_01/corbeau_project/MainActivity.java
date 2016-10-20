package com.example.a34011_73_01.corbeau_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonclick = (Button)findViewById(R.id.credit);
        buttonclick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(MainActivity.this, Credit.class);
                startActivity(intent1);
            }
        });
    }
}
