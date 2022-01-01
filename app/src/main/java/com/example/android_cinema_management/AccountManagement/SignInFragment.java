package com.example.android_cinema_management.AccountManagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignInFragment extends Fragment {
    //Declare textview ,textInputLayout ,and imageView,button
    ImageView close;
    TextInputLayout email, password;
    Button logIn;
    //Declare String
    String inputEmail, inputPassword;

    TextView register;

    Activity fragmentActivity;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sign_in, container, false);
        //Binding to XML's value
        close = root.findViewById(R.id.signInClose);
        logIn = root.findViewById(R.id.fragSignInLogin);
        email = root.findViewById(R.id.frag_signIn_textLayout_email);
        password = root.findViewById(R.id.frag_signIn_textLayout_password);

        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();

        fragmentActivity = getActivity();


        //Function to close current fragment and return to previous fragment
        close.setOnClickListener(view -> {
            // Replace this fragment by accounts fragment
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.ma_container, new Accounts()).commit();
        });

        register = root.findViewById(R.id.tv_register);
        register.setOnClickListener(view -> {
            startActivity(new Intent(fragmentActivity, SignUp.class));
        });

        return root;
    }

    // Function to check email is empty or not
    private boolean emailIsNotEmpty() {
        if (inputEmail.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else {
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
        } else {
            // Set email password
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}