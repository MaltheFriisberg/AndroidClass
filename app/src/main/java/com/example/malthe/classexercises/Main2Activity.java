package com.example.malthe.classexercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import Interfaces.ImageViewCommander;
import Interfaces.UserInputListener;
import SpilLogik.GameLogic;

public class Main2Activity extends AppCompatActivity implements UserInputListener, ImageViewCommander {
    GameLogic gameLogic;
    private TextView textView;
    private ImageView imageView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gameLogic = new GameLogic(textView, imageView);
    }

    @Override
    public void guessNextLetter(String letter) {
        gameLogic.play(letter);
    }

    @Override
    public void passTextField(TextView textField) {
        this.textView =textField;
        //gameLogic.setTextView(textField);
    }

    @Override
    public void passImageView(ImageView view) {
        //gameLogic = new GameLogic();
        this.imageView = view;
        //gameLogic.setImageView(view);
    }
}
