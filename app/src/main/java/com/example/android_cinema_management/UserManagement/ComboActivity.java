package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_cinema_management.Adapter.UserComboAdapter;
import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.ComboDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ComboActivity extends AppCompatActivity {
    //Declare viewpager + adapter + imageview
    ViewPager2 viewPager2;
    UserComboAdapter adapter;
    ImageView close;
    //Declare arraylist
    ArrayList<Combo> comboArrayList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("ResourceType")
    // Declare view layout and textview array
    LinearLayout dotContainer;
    ArrayList<TextView> dotTextViews;
    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);
        viewPager2 = findViewById(R.id.user_combo_viewpager2);
        dotContainer = findViewById(R.id.user_combo_dot_container);
        comboArrayList = new ArrayList<>();
        dotTextViews = new ArrayList<>();
        close = findViewById(R.id.user_combo_close_iv);

        //Function to close this activity
        close.setOnClickListener(View->{
            finish();
        });

//     Set animation when sliding fragments
        viewPager2.setPageTransformer(new DepthPageTransformer());
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                // show current indicator relative to page
                currentIndicator(position);
                super.onPageSelected(position);
            }
        });


        /*
        * Function to get info from database
        * */
        ComboDatabase.getCombos(db,comboArrayList,()->{
            adapter = new UserComboAdapter(comboArrayList, this);
            for (int i = 0; i < comboArrayList.size(); i++){
                TextView textView = new TextView(this);
                dotTextViews.add(textView);
            }
            viewPager2.setAdapter(adapter);
            showDotsIndicator();
        });

    }
    @Override
        public void onBackPressed() {
        }

// Function to apply animation for viewpager2
    private static class DepthPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setTranslationZ(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Move it behind the left page
                view.setTranslationZ(-1f);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

// Render indicator in the UI
    private void showDotsIndicator (){
        for (int i = 0; i < dotTextViews.size() ; i++){
            dotTextViews.set(i, new TextView(this));
            dotTextViews.get(i).setText(Html.fromHtml("&#9679"));
            dotTextViews.get(i).setTextSize(25);
            dotTextViews.get(i).setPadding(60,20,20,20);
            dotContainer.addView(dotTextViews.get(i));
        }
    }

// Check current position and change the color of indicator
    private void currentIndicator(int position) {
        for (int i =0; i < dotTextViews.size(); i++){
            if ( i == position){
                dotTextViews.get(i).setTextColor(Color.rgb(222, 22, 25));
            }
            else{
                dotTextViews.get(i).setTextColor(Color.rgb(161, 161, 161));
            }
        }
    }

}