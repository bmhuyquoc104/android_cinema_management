package com.example.android_cinema_management.AccountManagement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.R;
import com.facebook.AccessToken;
import com.facebook.AuthenticationToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.api.Authentication;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Objects;


public class SignInFragment extends Fragment {
    //Declare textview ,textInputLayout ,and imageView,button
    TextView title;
    ImageView close;
    TextInputLayout email,password;
    Button logIn;
    //Declare String
    String inputEmail,inputPassword;

    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private LoginButton loginButton;
    private static final String TAG = "FacebookAuthentication";
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

        firebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());
        loginButton = view.findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {

            }
        });

        return view;
    }

    //Function login to Facebook
    private void handleFacebookToken(AccessToken token){
        Log.d(TAG, "handleFacbookToken" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "Sign in with credential: Successful");
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                }
            }
        });
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