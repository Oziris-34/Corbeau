package com.example.a34011_73_01.corbeau_project;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 34011-73-09 on 27/10/2016.
 */

public class GameInfoFragment extends Fragment {
    private OnOkPressedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnOkPressedListener)getActivity();
        }
        catch(ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement OnOkPressedListener");
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
                else {
                    Toast message = Toast.makeText(getContext(), "Please enter a valid name!", Toast.LENGTH_SHORT);
                    message.show();
                }
            }
        });
    }

    public interface OnOkPressedListener {
        public void onOkPressed(String name);
    }
}
