package com.example.malthe.classexercises;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import Interfaces.IAsyncTaskClient;
import SpilLogik.WordCache;

import static android.content.Context.MODE_PRIVATE;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by malthe on 11/18/16.
 */

public class WordFetcher extends AsyncTask<Object, String, String> {
    private IAsyncTaskClient client;
    private Context context;
    private Activity activity;
    //private static String url =
    List<String> list = new ArrayList<>();
    private static String hentUrl(String url) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

    private void hentOrdFraDr() throws Exception {
        String data = hentUrl("http://dr.dk");
        //System.out.println("data = " + data);

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

        System.out.println("data = " + data);
        //muligeOrd.clear();
        list.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));


        //System.out.println("muligeOrd = " + muligeOrd);
        //nulstil();
    }


    @Override
    protected String doInBackground(Object... clients) {
        this.client = (IAsyncTaskClient)clients[0];
        this.context = (Context) clients[1];
        //this.context = clients[1];
        try {
            hentOrdFraDr();
            saveToWordCache();
            //SharedPreferences manager = PreferenceManager.getDefaultSharedPreferences(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Random random = new Random();
        this.client.notifyWhenDone(list.get(random.nextInt(list.size())));
        //super.onPostExecute(s);
    }
    private void saveToWordCache() {

        SharedPreferences mPrefs = getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        WordCache cache = new WordCache(this.list);
        String json = gson.toJson(cache);

        prefsEditor.putString("WordCache", json);
        prefsEditor.commit();

    }
}
