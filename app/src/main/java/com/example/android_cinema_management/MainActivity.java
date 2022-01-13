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

import com.example.android_cinema_management.AccountManagement.Accounts;
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
//        // Set adapter to viewpage2
//        viewpager2.setAdapter(adapter);

        // Create spannalbe String
//        SpannableStringBuilder builder = new SpannableStringBuilder();
//
//        // Text and color for string 1
//        SpannableString str1= new SpannableString("Login / ");
//        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
//        builder.append(str1);
//
//        // Text and color for string 2
//        SpannableString str2= new SpannableString("Sign Up");
//        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
//        builder.append(str2);
//
//        // Set text for button
//        loginAndRegister.setText( builder, Button.BufferType.SPANNABLE);
//
//        // Change the layout by tab selected
//        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewpager2.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                layout.selectTab(layout.getTabAt(position));
//            }
//        });
//
//        viewpager2.setPageTransformer(new ZoomOutPageTransformer());
//
//        //Switch to logging page when user click logging button
//        loginAndRegister.setOnClickListener(view ->{
//            Accounts accountFragment = new Accounts();
//            FragmentTransaction transaction =
//                    fm.beginTransaction();
//            transaction.replace(R.id.ma_frag_container, accountFragment).commit();
//        });

        //Choose the fragment by bottom navigation
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {

            ArrayList<Boolean> arrayList = new ArrayList<>();
            switch (item.getItemId()){
                case R.id.menu_account:
                    checkLogin(arrayList, () -> {
                        if (arrayList.get(0) == true){
                            chosenFragment = new UserHomeFragmentActivity();
                        }else{
                            chosenFragment = new Accounts();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.ma_container,chosenFragment).commit();
                    });
                    break;
                case (R.id.menu_cinema):
                    chosenFragment = new CinemaFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.ma_container,chosenFragment).commit();
                    break;
                case (R.id.menu_search) :
                    chosenFragment = new SearchMovieFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.ma_container,chosenFragment).commit();
                    break;
                case (R.id.menu_discount):
                    chosenFragment = new DiscountAndTicket();
                    getSupportFragmentManager().beginTransaction().replace(R.id.ma_container,chosenFragment).commit();
                    break;
                default: chosenFragment = new HomeFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.ma_container,chosenFragment).commit();
            return true;
        });
    }
    public void checkLogin(ArrayList<Boolean> rsArray, Runnable callback){
        DocumentReference docRef = db.collection("Users").document(mUser.getUid());
        docRef.get().addOnCompleteListener(task -> {
            DocumentSnapshot docSnap = task.getResult();
            if (docSnap.getString("status").equals("active")){
                rsArray.add(true);
            }else {
                rsArray.add(false);
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