package com.example.android_cinema_management.AccountManagement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.facebook.FacebookSdk;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;


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
    private AccessTokenTracker accessTokenTracker;
    private FirebaseAuth.AuthStateListener authStateListener;
    private LoginButton loginButton;
    private SignInButton signInButton;
    private GoogleSignInClient googleSignInClient;
    private Button btnSignOut;
    private static final String TAG_Google = "SignInFragment";
    private static final String TAG_Facebook = "FacebookAuthentication";
    private static final int RC_SIGN_IN = 123;
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
        close = view.findViewById(R.id.signInClose);
        logIn = view.findViewById(R.id.fragSignInLogin);
        email = view.findViewById(R.id.frag_signIn_textLayout_email);
        password = view.findViewById(R.id.frag_signIn_textLayout_password);
        // Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();
        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();


        //Function to close current fragment and return to previous fragment
        close.setOnClickListener(View ->{
            // Replace this fragment by accounts fragment
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.ma_container, new Accounts()).commit();
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
        signInButton = view.findViewById(R.id.googleSignInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        btnSignOut = view.findViewById(R.id.googleSignOutButton);
        loginButton.setReadPermissions("email","public_profile");
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG_Facebook, "onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG_Facebook, "onCancel");
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Log.d(TAG_Facebook, "onError" + e);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null){
                    firebaseAuth.signOut();
                }
            }
        };

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(resultCode, requestCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }catch (ApiException e){
                
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    //Function login to Facebook
    private void handleFacebookToken(AccessToken token){
        Log.d(TAG_Facebook, "handleFacebookToken" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG_Facebook, "Sign in with credential: Successful");
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

    private void signIn(){
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

}