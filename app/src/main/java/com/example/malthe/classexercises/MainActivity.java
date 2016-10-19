package com.example.malthe.classexercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import SpilLogik.GalgeLogik;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    TextView textView2;
    TextView textView3;
    EditText editText;
    ImageView imageView;
    Button playBtn;
    GalgeLogik logik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView3);
        playBtn = (Button) findViewById(R.id.button);
        playBtn.setOnClickListener(this);
        logik = new GalgeLogik(this.imageView, this.textView3);
    }

    @Override
    public void onClick(View v) {
        if(!logik.play(editText.getText().toString())) {
            //Restart
            this.logik = new GalgeLogik(this.imageView, this.textView3);
        }
    }
}
