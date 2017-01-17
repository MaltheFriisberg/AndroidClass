package com.example.malthe.classexercises;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import Interfaces.ImageViewCommander;
import Interfaces.UserInputListener;
import SpilLogik.GameLogic;

public class MainActivity extends AppCompatActivity implements UserInputListener, ImageViewCommander {
    GameLogic gameLogic;
    private TextView textView;
    private ImageView imageView;
    private EditText editText;
    public static Context contextOfApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gameLogic = new GameLogic(textView, imageView, this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.gameLogic = new GameLogic(textView, imageView, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.gameLogic = new GameLogic(textView, imageView, this);
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
    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    /*public static SharedPreferences getSharedPrefs() {
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
    }*/
}
