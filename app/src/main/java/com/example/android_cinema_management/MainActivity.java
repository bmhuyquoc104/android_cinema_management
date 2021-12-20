package com.example.android_cinema_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declare login and register button
    Button loginAndRegister;
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.Adapter participantAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private MoviesAdapter moviesAdapter;
    //Declare Movie list
    private ArrayList<Movie> movieList;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding with xml layout
        loginAndRegister = findViewById(R.id.loginAndRegister);
        movieList = new ArrayList<>();

        // Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1= new SpannableString("Login / ");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2= new SpannableString("Sign Up");
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
        builder.append(str2);

        // Set text for button
        loginAndRegister.setText( builder, Button.BufferType.SPANNABLE);

        // Set fixed size for recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //Use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // Specify an adapter
        moviesAdapter = new MoviesAdapter(movieList,this);
        recyclerView.setAdapter(moviesAdapter);

    }
}