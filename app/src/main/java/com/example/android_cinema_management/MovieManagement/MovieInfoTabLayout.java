package com.example.android_cinema_management.MovieManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.example.android_cinema_management.Adapter.MovieFragmentAdapter;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieInfoTabLayout extends AppCompatActivity {
    //Declare tablayout, adapter and viewpager2
    TabLayout layout;
    ViewPager2 viewpager2;
    MovieFragmentAdapter adapter;
    // Declare String
    String vnTitle, enTitle,imageUrl,movieDetailUrl;
    //Declare button
    Button loginAndRegister;
    //Declare public static list to store current movie and use later by other classes
    public static Movie currentMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info_tab_layout);
        //Binding with XML values
        layout = findViewById(R.id.mitl_tab_layout);
        viewpager2 = findViewById(R.id.mitl_viewpager2);
        loginAndRegister = findViewById(R.id.loginAndRegister);
        // Initialize fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // Initialize adapter
        adapter = new MovieFragmentAdapter(fm,getLifecycle());
        // Set adapter to viewpage2
        viewpager2.setAdapter(adapter);
        // Change the layout by tab selected
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                layout.selectTab(layout.getTabAt(position));
            }
        });

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
        // Get intent from other activities
        Intent intent = getIntent();

        if(intent != null) {
            if (intent.hasExtra("vietnameseTitle")) {
                vnTitle = intent.getStringExtra("vietnameseTitle");
                // Set text for vietnamese title textview
            }
            if (intent.hasExtra("englishTitle")) {
                enTitle = intent.getStringExtra("englishTitle");
                // Set text for english title textview
            }

            if (intent.hasExtra("imageUrl")) {
                imageUrl = intent.getStringExtra("imageUrl");
                // Set image for image view
            }

            // Get movie detail url from main activity
            if( intent.hasExtra("movieDetailUrl")){
                movieDetailUrl = intent.getStringExtra("movieDetailUrl");
            }
        }
        //Disable swiping
        viewpager2.setUserInputEnabled(false);
        // Get currentMovie
        currentMovie = new Movie(vnTitle,enTitle,imageUrl,movieDetailUrl);
    }
}