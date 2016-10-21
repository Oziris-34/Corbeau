package com.example.a34011_73_01.corbeau_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by 34011-73-01 on 20/10/2016.
 */

public class Credit extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_credit);

        Button buttonclick = (Button)findViewById(R.id.creation);
        buttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Credit.this, Creation.class);
                startActivity(intent);
            }
        });
    }
}