package com.example.malthe.classexercises;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import SpilLogik.WordCache;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class ChooseWordActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    List<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_choose_word);
        this.words = new ArrayList<>();
        fetchWordCache();

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.layout_wordlist, R.id.textView, words);
        ListView view = new ListView(this);
        view.setOnItemClickListener(this);
        view.setAdapter(adapter);
        setContentView(view);


    }

    private void fetchWordCache() {

        SharedPreferences mPrefs = getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = mPrefs.getString("WordCache", "");
        int x = 1;
        if(json.length() >0 && !json.equals("")) {

            WordCache state = gson.fromJson(json, WordCache.class);
            this.words = state.words;
        } else {
            this.words.add("abe");
            this.words.add("kat");
        }




    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("OnItemClick", i+"");
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("wordChosen", this.words.get(i));
        startActivity(intent);
    }
}
