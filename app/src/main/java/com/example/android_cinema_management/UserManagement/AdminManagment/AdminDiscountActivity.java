package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.android_cinema_management.Adapter.ComboAdminFragmentAdapter;
import com.example.android_cinema_management.Adapter.DiscountAdminFragmentAdapter;
import com.example.android_cinema_management.R;
import com.google.android.material.tabs.TabLayout;

public class AdminDiscountActivity extends AppCompatActivity {

    //Declare imageview
    ImageView close;
    //Declare tableLayout, adapter and viewpager2
    TabLayout layout;
    ViewPager2 viewpager2;
    DiscountAdminFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_discount);

        //Binding with XML values
        layout = findViewById(R.id.admin_discount_tabLayout);
        viewpager2 = findViewById(R.id.admin_discount_viewpager2);
        close = findViewById(R.id.admin_discount_close_iv);
        // Initialize fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // Initialize adapter
        adapter = new DiscountAdminFragmentAdapter(fm,getLifecycle());
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

        //Disable swiping
        viewpager2.setUserInputEnabled(false);
        /*
         *Function to close activity
         * */
        close.setOnClickListener(view->{
            finish();
        });

    }

    @Override
    public void onBackPressed(){

    };
}