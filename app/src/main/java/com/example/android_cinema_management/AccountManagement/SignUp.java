package com.example.android_cinema_management.AccountManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    @SuppressLint("ResourceType")
    ViewPager2 viewpager2;
    RegisterAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Binding to XML's value
        title = findViewById(R.id.signUp_title);
        close = findViewById(R.id.signUpClose);
        // Instantiate a ViewPager2 and a PagerAdapter.
        FragmentManager fm = getSupportFragmentManager();
        viewpager2 = findViewById(R.id.signUp_viewPager2);
        adapter = new RegisterAdapter(fm,getLifecycle());
        viewpager2.setAdapter(adapter);
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
//            Intent intent = new Intent(this, MainActivity.class);
//            //Send intent to sign Up activity
//
//            // Start intent
//            try {
//                startActivity(intent);
//            }
//            // Exception if activity is not found
//            catch (ActivityNotFoundException e){
//                Toast.makeText(this,"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
//            }
            // Return to the last stack
            finish();
        });

        viewpager2.setPageTransformer(new DepthPageTransformer());
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
}
