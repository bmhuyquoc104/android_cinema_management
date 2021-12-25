package com.example.android_cinema_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.Button;

import com.example.android_cinema_management.AccountManagement.Accounts;
import com.example.android_cinema_management.Adapter.HomeAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    //Declare tablayout, adapter and viewpager2
    TabLayout layout;
    ViewPager2 viewpager2;
    HomeAdapter adapter;
    //Declare login and register button
    Button loginAndRegister;
    //Declare bottom navigation
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Binding with XML values
        layout = findViewById(R.id.ma_tab_layout);
        viewpager2 = findViewById(R.id.ma_viewpager2);
        loginAndRegister = findViewById(R.id.loginAndRegister);
        bottomNavigation = findViewById(R.id.bottomNavigation);
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



        //Choose the fragment by bottom navigation
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment chosenFragment = null;
                switch (item.getItemId()){
                    case R.id.menu_account:
                        chosenFragment = new Accounts();
                        break;
                    case (R.id.menu_cinema):
                        chosenFragment = new Cinema();
                        break;
                    case (R.id.menu_search) :
                        chosenFragment = new SearchMovieAndCinema();
                        break;
//                    case (R.id.menu_home):
//                        chosenFragment = new Home();
//                        break;
                    default: chosenFragment = new Home();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.ma_frag_container,chosenFragment).commit();
                return true;
            }
        });
    }
}