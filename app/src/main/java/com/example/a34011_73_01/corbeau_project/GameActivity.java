package com.example.a34011_73_01.corbeau_project;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by 34011-73-09 on 20/10/2016.
 */

public class GameActivity extends AppCompatActivity {

    private ImageView corbeauImageView;
    private Animation corbeauAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageView orchardImageView = (ImageView)findViewById(R.id.orchardBackground);
        /*
        Drawable corbeauDrawable = getResources().getDrawable(R.drawable.corbeau);
        corbeauDrawable.setBounds(corbeauDrawable.getBounds());
        */

        corbeauImageView  = (ImageView)findViewById(R.id.corbeauImage);
        corbeauImageView.setImageResource(R.drawable.corbeau);
        corbeauImageView.setScaleX(-0.4f);
        corbeauImageView.setScaleY(0.4f);
        corbeauImageView.setTranslationX(0);
        corbeauImageView.setTranslationY(160);

        corbeauAnimation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.corbeau_anim);

        orchardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG", "Orchard Clicked");
                corbeauImageView.startAnimation(corbeauAnimation);
            }
        });
    }
}
