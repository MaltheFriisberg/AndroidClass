package com.example.malthe.classexercises;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

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

/**
 * Created by malthe on 11/18/16.
 */

public class WordFetcher extends AsyncTask<IAsyncTaskClient, String, String> {
    private IAsyncTaskClient client;
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
    protected String doInBackground(IAsyncTaskClient... clients) {
        this.client = clients[0];
        try {
            hentOrdFraDr();
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
}
