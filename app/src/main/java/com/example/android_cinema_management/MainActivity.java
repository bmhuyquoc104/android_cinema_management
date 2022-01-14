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
import com.example.android_cinema_management.UserManagement.UserHomeFragmentActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declare tablayout, adapter and viewpager2
    TabLayout layout;
    ViewPager2 viewpager2;
    HomeAdapter adapter;
    //Declare login and register button
    Button loginAndRegister;
    //Declare boolean
    public static boolean isLogin = false;

    //Declare bottom navigation
    BottomNavigationView bottomNavigation;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser mUser = mAuth.getCurrentUser();
    Fragment chosenFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_Android_cinema_management);
        setContentView(R.layout.activity_main);
        //Binding with XML values
//        layout = findViewById(R.id.ma_tab_layout);
//        viewpager2 = findViewById(R.id.ma_viewpager2);
//        loginAndRegister = findViewById(R.id.loginAndRegister);
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
                    case (R.id.menu_search) :
                        chosenFragment = new SearchMovieFragment();
                        break;
                    case (R.id.menu_discount):
                        chosenFragment = new DiscountAndTicket();
                        break;
                    default: chosenFragment = new HomeFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.ma_container,chosenFragment).commit();
                return true;
            }

            callback.run();
        });
    }

    @Override
    public void onBackPressed(){

    };

//    private static class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
//        private static final float MIN_SCALE = 0.85f;
//        private static final float MIN_ALPHA = 0.5f;
//
//        public void transformPage(View view, float position) {
//            int pageWidth = view.getWidth();
//            int pageHeight = view.getHeight();
//
//            if (position < -1) { // [-Infinity,-1)
//                // This page is way off-screen to the left.
//                view.setAlpha(0f);
//
//            } else if (position <= 1) { // [-1,1]
//                // Modify the default slide transition to shrink the page as well
//                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
//                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
//                if (position < 0) {
//                    view.setTranslationX(horzMargin - vertMargin / 2);
//                } else {
//                    view.setTranslationX(-horzMargin + vertMargin / 2);
//                }
//
//                // Scale the page down (between MIN_SCALE and 1)
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
//
//                // Fade the page relative to its size.
//                view.setAlpha(MIN_ALPHA +
//                        (scaleFactor - MIN_SCALE) /
//                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//
//            } else { // (1,+Infinity]
//                // This page is way off-screen to the right.
//                view.setAlpha(0f);
//            }
//        }
//    }
}