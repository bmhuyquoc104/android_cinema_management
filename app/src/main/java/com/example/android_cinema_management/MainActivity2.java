package com.example.android_cinema_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.example.android_cinema_management.Adapter.HomeAdapter;
import com.example.android_cinema_management.Adapter.MovieFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity2 extends AppCompatActivity {
    //Declare tablayout, adapter and viewpager2
    TabLayout layout;
    ViewPager2 viewpager2;
    HomeAdapter adapter;
    //Declare login and register button
    Button loginAndRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Binding with XML values
        layout = findViewById(R.id.ma_tab_layout);
        viewpager2 = findViewById(R.id.ma_viewpager2);
        loginAndRegister = findViewById(R.id.loginAndRegister);
        // Initialize fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // Initialize adapter
        adapter = new HomeAdapter(fm,getLifecycle());
        // Set adapter to viewpage2
        viewpager2.setAdapter(adapter);

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
    }
}