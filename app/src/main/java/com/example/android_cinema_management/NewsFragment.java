package com.example.android_cinema_management;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.android_cinema_management.AccountManagement.SignUp;
import com.example.android_cinema_management.Adapter.DiscountAdapter;
import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.Model.News;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class NewsFragment extends Fragment {
    //Declare imageview
    ImageView Back;

    private RecyclerView News;
    private ArrayList<News> newsArrayList;
    FirebaseFirestore db;


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

//        Back button
        Back = view.findViewById(R.id.back);
        Back.setOnClickListener(View ->{
            startActivity(new Intent(getContext(), MainActivity.class));
        });

        News = view.findViewById(R.id.newsItems);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        News.setLayoutManager(gridLayoutManager);

//        News database
        db = FirebaseFirestore.getInstance();
        newsArrayList = new ArrayList<News>();
        return view;
    }
}