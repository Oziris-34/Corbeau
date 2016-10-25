package com.example.a34011_73_01.corbeau_project;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by 34011-73-09 on 20/10/2016.
 */

public class GameActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game();

        updateBoard();
    }

    private void updateBoard() {
        switch(game.getRemainingGreenFruit()) {

        }

        switch(game.getRemainingOrangeFruit()) {

        }

        switch(game.getRemainingVioletFruit()) {

        }

        switch(game.getRemainingYellowFruit()) {

        }

        switch(game.getRavenPosition()) {

        }
    }
}
