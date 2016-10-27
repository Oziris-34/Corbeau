package com.example.a34011_73_01.corbeau_project;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by 34011-73-09 on 20/10/2016.
 */

public class GameActivity extends AppCompatActivity implements GameInfoFragment.OnOkPressedListener {

    private FragmentManager fragmentManager;
    private GameInfoFragment gameInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        fragmentManager = getSupportFragmentManager();
        gameInfoFragment = new GameInfoFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.visibleFragment, gameInfoFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onOkPressed(String name) {
        Toast toast = Toast.makeText(this, "Well done you have pressed the OK button: " + name, Toast.LENGTH_LONG);
        toast.show();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.visibleFragment, new PlayFragment());

        fragmentTransaction.commit();
    }
}
