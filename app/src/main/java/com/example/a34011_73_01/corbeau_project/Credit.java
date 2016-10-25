package com.example.a34011_73_01.corbeau_project;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by 34011-73-01 on 20/10/2016.
 */

public class Credit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        Button buttonclick = (Button)findViewById(R.id.creationButton);

        buttonclick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // use Intent

                Intent intent1= new Intent(Credit.this, Creation.class);
                startActivity(intent1);
            }
        });
    }
}