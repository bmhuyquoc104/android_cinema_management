package com.example.android_cinema_management.AccountManagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminActivity;
import com.example.android_cinema_management.UserManagement.UserHomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SignInFragment extends Fragment {
    //Declare textview ,textInputLayout ,and imageView,button
    ImageView close;
    TextInputLayout email, password;
    Button logIn;
    Button logOut;
    TextView forgotPassword;
    //Declare admin;
    User admin;
    //Declare String
    String inputEmail, inputPassword;

    TextView register;

    Activity fragmentActivity;

    //Declare Firebase Authentication
    FirebaseAuth firebaseAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    String userId;

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
        forgotPassword = root.findViewById(R.id.signInForgetPassword);

        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();

        fragmentActivity = getActivity();

        // Initialize admin
        admin = User.createAdmin();

        //Initialize firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();
        mUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
//        userId = firebaseAuth.getCurrentUser().getUid();


        //Function to log into the account
        logIn.setOnClickListener(view ->{
            //For at home testing remove before commit
//            Bundle bundle = new Bundle();
//            UserHomeFragment fragment = new UserHomeFragment();
//            fragment.setArguments(bundle);
//            FragmentTransaction transaction =
//                    fm.beginTransaction();
//            transaction.replace(R.id.ma_container, fragment).commit();
            // Replace this fragment by accounts fragment

                if (emailIsNotEmpty() && passwordIsNotEmpty()) {
                    //getting user's email and password
                    inputEmail = email.getEditText().getText().toString();
                    inputPassword = password.getEditText().getText().toString();
                    // If the input is from an admin -> redirect to admin page
                    if (inputEmail.equals(admin.getEmail()) && inputPassword.equals(admin.getPassword())){
                        Intent intent2 = new Intent(getContext(), AdminActivity.class);
                        intent2.setAction(Intent.ACTION_SEND);
                        intent2.setType("plain/text");
                        intent2.putExtra("userName", admin.getFullName());
                        // Delete all stacks before to avoid stack memory redundant and collapse between stacks
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent2);
                    }
                    // the input is from user
                    else {
                        Bundle bundle = new Bundle();
                        bundle.putString("password", inputPassword);
                        bundle.putString("email", inputEmail);
                        //send user's email and password to Firebase Authentication to check
                        firebaseAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    //if the email and password is correct
                                    UserHomeFragment fragment = new UserHomeFragment();
                                    fragment.setArguments(bundle);
                                    FragmentTransaction transaction =
                                            fm.beginTransaction();
                                    transaction.replace(R.id.ma_container, fragment).commit();
                                } else {
                                    //if the email and password is incorrect
                                    Toast.makeText(getActivity(), "Fail to login " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        db.collection("Users").document(mUser.getUid()).update("status", "active");
                    }
                }
        });

        //Function forgot password
        forgotPassword.setOnClickListener(view -> {
            //Initialize Dialog
            Dialog forgotPasswordDialog = new Dialog(getActivity());
            forgotPasswordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            //set content for Dialog
            forgotPasswordDialog.setContentView(R.layout.forgot_password_dialog);
            Window window = forgotPasswordDialog.getWindow();
            if (window == null) {
                return;
            }
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            // set the dialog to top
            windowAttributes.gravity = Gravity.TOP;
            window.setAttributes(windowAttributes);
            // Disable cancel by clicking randomly on the screen
            forgotPasswordDialog.setCancelable(false);

            forgotPasswordDialog.show();
            //Initialize EditText and ImageButton
            EditText resetEmail = forgotPasswordDialog.findViewById(R.id.user_forgot_password_tie);
            ImageButton closeDialog = forgotPasswordDialog.findViewById(R.id.open_user_forgotPassword_close_button);

            //Initialize send Verification button
            Button sendVerification = forgotPasswordDialog.findViewById(R.id.user_profile_dialog_send_verification_button);
            //listen to OnClickListener of sendVerification button
            sendVerification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Initialize String with value of user's email account
                    String email = resetEmail.getText().toString();
                    //call Firebase Authentication to send reset password link to user's email
                    firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        //onSuccess sending link via email
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "Reset link has been sent to your email", Toast.LENGTH_SHORT).show();
                            forgotPasswordDialog.dismiss();
                        }
                        //onFailure sending link via email
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Fail to send rest link" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            //set close button to dismiss forgotPasswordDialog
            closeDialog.setOnClickListener(view1 -> {
                forgotPasswordDialog.dismiss();
            });

        });

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
        if (Objects.requireNonNull(email.getEditText()).getText().toString().isEmpty()) {
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
        if (Objects.requireNonNull(password.getEditText()).getText().toString().isEmpty()) {
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