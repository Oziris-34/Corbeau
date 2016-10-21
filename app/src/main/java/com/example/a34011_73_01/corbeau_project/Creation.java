package com.example.a34011_73_01.corbeau_project;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by 34011-73-01 on 20/10/2016.
 */

public class Creation extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_creation);
    }
}