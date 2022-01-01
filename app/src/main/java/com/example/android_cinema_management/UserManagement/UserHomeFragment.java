package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.R;


public class UserHomeFragment extends Fragment {
    //Declare imageview
    ImageView profile, avatar, combo, transaction, feedback, review, points;
    //Declare textview
    TextView welcome, type;
    //Declare logout
    Button logOut;
    // Declare string email
    String email;
    public UserHomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Binding to XML layout
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        profile = view.findViewById(R.id.user_home_profile_iv);
        combo = view.findViewById(R.id.user_home_gift_iv);
        transaction = view.findViewById(R.id.user_home_transaction_history_iv);
        feedback = view.findViewById(R.id.user_home_feedback_iv);
        review = view.findViewById(R.id.user_home_review_iv);
        points = view.findViewById(R.id.user_home_points_iv);
        logOut = view.findViewById(R.id.user_home_logout_button);
        welcome = view.findViewById(R.id.user_home_name);
        type = view.findViewById(R.id.user_home_account_type);
        // Receive the bundle from other fragments
        Bundle bundle = this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
        email = bundle.getString("email");

        //Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1= new SpannableString("Welcome: ");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2= new SpannableString(email);
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
        builder.append(str2);

        // Set text for textView
        welcome.setText("Welcome " + email);

        return view;
    }
}