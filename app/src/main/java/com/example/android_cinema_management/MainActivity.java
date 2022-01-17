package com.example.android_cinema_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.android_cinema_management.Adapter.HomeAdapter;
import com.example.android_cinema_management.CinemaManagement.CinemaFragment;
import com.example.android_cinema_management.UserManagement.UserHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    HomeAdapter adapter;

    //Declare boolean
    public static boolean isLogin = false;

    //Declare bottom navigation
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        // Initialize fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // Initialize adapter
        adapter = new HomeAdapter(fm,getLifecycle());
        fm.beginTransaction().replace(R.id.ma_container,new HomeFragment()).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment chosenFragment = null;
                switch (item.getItemId()){
                    case R.id.menu_account:
                        if(!isLogin){
                        chosenFragment = new Accounts();
                        }
                        else{
                            chosenFragment = new UserHomeFragment();
                        }
                        break;
                    case (R.id.menu_cinema):
                        chosenFragment = new CinemaFragment();
                        break;
                    case (R.id.menu_news) :
                        chosenFragment = new NewsFragment();
                        break;
                    case (R.id.menu_discount):
                        chosenFragment = new DiscountAndTicket();
                        break;
                    default: chosenFragment = new HomeFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.ma_container,chosenFragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed(){

    };

}
