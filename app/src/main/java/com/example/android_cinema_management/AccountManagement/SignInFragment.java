package com.example.android_cinema_management.AccountManagement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class SignInFragment extends Fragment {
    //Declare textview ,textInputLayout ,and imageView,button
    TextView title;
    ImageView close;
    TextInputLayout email,password;
    Button logIn;
    //Declare String
    String inputEmail,inputPassword;
    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        //Binding to XML's value
        title = view.findViewById(R.id.signIn_title);
        close = view.findViewById(R.id.signInClose);
        logIn = view.findViewById(R.id.fragSignInLogin);
        email = view.findViewById(R.id.frag_signIn_textLayout_email);
        password = view.findViewById(R.id.frag_signIn_textLayout_password);
        // Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();
        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();
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

        //Function to close current fragment and return to previous fragment
        close.setOnClickListener(View ->{
            // Replace this fragment by accounts fragment
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.ma_frag_container, new Accounts()).commit();
        });

        logIn.setOnClickListener(View -> {
            inputEmail = Objects.requireNonNull(email.getEditText()).getText().toString();
            inputPassword = Objects.requireNonNull(password.getEditText()).getText().toString();
            if (passwordIsNotEmpty() & emailIsNotEmpty()){
                Toast.makeText(getContext(), "You have successfully logged in",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    // Function to check email is empty or not
    private boolean emailIsNotEmpty() {
        if (inputEmail.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        }
        else {
            // Set email error
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    // Function to check password is empty or not
    private boolean passwordIsNotEmpty() {
        // Check if the user input password or not
        if (inputPassword.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        }
        else {
            // Set email password
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

}