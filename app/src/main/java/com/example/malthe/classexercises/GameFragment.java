package com.example.malthe.classexercises;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import Interfaces.ImageViewCommander;
import Interfaces.UserInputListener;

/**
 * Created by Malthe on 19/10/2016.
 */

public class GameFragment extends Fragment {
    private ImageView imageView;
    private ImageViewCommander imageViewCommander;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageViewCommander.passImageView(this.imageView);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            imageViewCommander = (ImageViewCommander) context;

        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
}
