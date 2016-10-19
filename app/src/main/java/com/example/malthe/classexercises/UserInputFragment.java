package com.example.malthe.classexercises;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Interfaces.UserInputListener;

/**
 * Created by Malthe on 19/10/2016.
 */

public class UserInputFragment extends Fragment implements View.OnClickListener {
    private Button playButton;
    private EditText userInput;
    private TextView textView7;
    UserInputListener activityCommander;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_user_input, container, false);
        playButton = (Button) view.findViewById(R.id.button2);
        userInput = (EditText) view.findViewById(R.id.editText3);
        textView7 = (TextView) view.findViewById(R.id.textView7);
        activityCommander.passTextField(this.textView7);
        playButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCommander = (UserInputListener) context;

        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onClick(View v) {
        activityCommander.guessNextLetter(userInput.getText().toString());
    }
}
