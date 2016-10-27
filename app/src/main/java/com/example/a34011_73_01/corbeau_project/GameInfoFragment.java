package com.example.a34011_73_01.corbeau_project;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 34011-73-09 on 27/10/2016.
 */

public class GameInfoFragment extends Fragment {
    private OnOkPressedListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnOkPressedListener)activity;
        }
        catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnOkPressedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        Button ok = (Button)getView().findViewById(R.id.okButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)getView().findViewById(R.id.editText);
                String text = editText.getText().toString();
                if((text != null) && !text.trim().equals("")) {
                    listener.onOkPressed(text);
                }
            }
        });

        Drawable orchard = getView().getResources().getDrawable(R.drawable.plateau2);
        Drawable corback = getView().getResources().getDrawable(R.drawable.corbeau1);

        Bitmap result = BitmapBlending.blend(((BitmapDrawable)orchard).getBitmap(), ((BitmapDrawable)corback).getBitmap());

        FileOutputStream out = null;

        Log.d("GameInfo", "Enregister: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));

        try {
            out = new FileOutputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "BlendedImage.png"));
            result.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnOkPressedListener {
        public void onOkPressed(String name);
    }
}
