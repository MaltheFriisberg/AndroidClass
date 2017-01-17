package com.example.malthe.classexercises;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

import Interfaces.IAsyncTaskClient;
import SpilLogik.WordCache;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class FrontScreen extends AppCompatActivity implements View.OnClickListener, IAsyncTaskClient {

    Button btnPlay;
    Button btnGetWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_screen);
        this.btnPlay = (Button) findViewById(R.id.button3);
        this.btnGetWords = (Button) findViewById(R.id.button4);
        this.btnPlay.setOnClickListener(this);
        this.btnGetWords.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == btnPlay.getId()){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else {
            startWordFetch();
            Intent intent = new Intent(this, ChooseWordActivity.class);
            startActivity(intent);
            //testGetWords();
        }


    }
    private void startWordFetch() {
        WordFetcher asyncTask = new WordFetcher();
        asyncTask.execute(this, this);
    }

    @Override
    public void notifyWhenDone(String result) {

    }
    private WordCache testGetWords() {
        SharedPreferences mPrefs = getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = mPrefs.getString("WordCache", "");
        int x = 1;
        if(json.length() >0 && !json.equals("")) {

            WordCache state = gson.fromJson(json, WordCache.class);
            return state;
        }
        return new WordCache(new ArrayList<String>());
    }
}
