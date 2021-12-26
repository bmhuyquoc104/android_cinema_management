package com.example.android_cinema_management.AccountManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.AccountManagement.Accounts;
import com.example.android_cinema_management.Adapter.RegisterAdapter;
import com.example.android_cinema_management.MainActivity;
import com.example.android_cinema_management.R;

public class SignUp extends AppCompatActivity {
    //Declare textview and imageView
    TextView title;
    ImageView close;
    // Declare viewpager 2 and adapter
    @SuppressLint("ResourceType")
    ViewPager2 viewpager2;
    RegisterAdapter adapter;
    // Declare view layout and textview array
    LinearLayout dotLayout;
    TextView [] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Binding to XML's value
        title = findViewById(R.id.signUp_title);
        close = findViewById(R.id.signUpClose);
        dotLayout = findViewById(R.id.signUp_dotIndicator);
        // Instantiate a ViewPager2 and a PagerAdapter.
        FragmentManager fm = getSupportFragmentManager();
        viewpager2 = findViewById(R.id.signUp_viewPager2);
        adapter = new RegisterAdapter(fm,getLifecycle());
        viewpager2.setAdapter(adapter);
        // Instantiate textview array
        dots = new TextView[3];
        dotsIndicator();
        // Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1 = new SpannableString("Register Here");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161, 161, 161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2 = new SpannableString("!");
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222, 22, 25)), 0, str2.length(), 0);
        builder.append(str2);
        // Set text for textView
        title.setText(builder, Button.BufferType.SPANNABLE);
        // Function to get back to previous account fragment
        close.setOnClickListener(view -> {
            finish();
        });

        // Set animation when sliding fragments
        viewpager2.setPageTransformer(new DepthPageTransformer());
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                chosenIndicator(position);
                super.onPageSelected(position);
            }
        });
    }
    
    @Override
        public void onBackPressed() {
            if (viewpager2.getCurrentItem() == 0) {
                // If the user is currently looking at the first step, allow the system to handle the
                // Back button. This calls finish() on this activity and pops the back stack.
                super.onBackPressed();
            } else {
                // Otherwise, select the previous step.
                viewpager2.setCurrentItem(viewpager2.getCurrentItem() - 1);
            }
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
    private void dotsIndicator (){
        for (int i = 0; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#9679"));
            dots[i].setTextSize(25);
            dots[i].setPadding(60,20,20,20);
            dotLayout.addView(dots[i]);
        }
    }

    // Check current position and change the color of indicator
    private void chosenIndicator(int position) {
        for (int i =0; i < dots.length; i++){
            if ( i == position){
                dots[i].setTextColor(Color.rgb(222, 22, 25));
            }
            else{
                dots[i].setTextColor(Color.rgb(161, 161, 161));
            }
        }
    }
}
