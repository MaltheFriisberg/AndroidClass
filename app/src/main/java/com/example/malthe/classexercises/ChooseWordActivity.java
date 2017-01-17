package com.example.malthe.classexercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChooseWordActivity extends AppCompatActivity {

    List<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_choose_word);
        this.words = new ArrayList<>();
        this.words.add("abe");
        this.words.add("kat");

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.layout_wordlist, R.id.textView, words);
        ListView view = new ListView(this);
        setContentView(view);


    }
}
