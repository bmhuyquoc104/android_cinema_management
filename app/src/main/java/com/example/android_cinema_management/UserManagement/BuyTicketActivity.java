package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.android_cinema_management.AccountManagement.SignUpFragment;
import com.example.android_cinema_management.Adapter.BuyTicketFragmentAdapter;
import com.example.android_cinema_management.R;
import com.google.android.material.tabs.TabLayout;

public class BuyTicketActivity extends AppCompatActivity {
    //Declare imageview
    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        //Binding with XML values
        close = findViewById(R.id.buy_ticket_close_iv);
        // Add first fragment to activity
        FragmentManager fm = getSupportFragmentManager();
        BuyByMovieFragment fragment = new BuyByMovieFragment();
        fm.beginTransaction().add(R.id.buy_ticket_frame_layout,fragment).commit();

        /*
         *Function to close activity
         * */
        close.setOnClickListener(view->{
            finish();
        });
    }
}