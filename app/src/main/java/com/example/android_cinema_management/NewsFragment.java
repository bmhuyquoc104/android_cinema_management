package com.example.android_cinema_management;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android_cinema_management.AccountManagement.SignUp;


public class NewsFragment extends Fragment {
    //Declare imageview
    ImageView close;


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        close = view.findViewById(R.id.fragment_news_close_iv);
        close.setOnClickListener(View ->{
            startActivity(new Intent(getContext(), MainActivity.class));
        });
        return view;
    }
}