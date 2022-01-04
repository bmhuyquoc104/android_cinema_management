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

import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.UserHomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignInFragment extends Fragment {
    //Declare textview ,textInputLayout ,and imageView,button
    ImageView close;
    TextInputLayout email, password;
    Button logIn;
    Button logOut;
    TextView forgotPassword;

    //Declare String
    String inputEmail, inputPassword;

    TextView register;

    Activity fragmentActivity;

    FirebaseAuth firebaseAuth;

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
        logOut = root.findViewById(R.id.fragSignInLogOut);
        email = root.findViewById(R.id.frag_signIn_textLayout_email);
        password = root.findViewById(R.id.frag_signIn_textLayout_password);
        forgotPassword = root.findViewById(R.id.signInForgetPassword);

        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();

        fragmentActivity = getActivity();

        //Initialize firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();


        //Function to log into the account
        logIn.setOnClickListener(view ->{
            // Replace this fragment by accounts fragment
                if (emailIsNotEmpty() && passwordIsNotEmpty()) {
                    inputEmail = email.getEditText().getText().toString();
                    inputPassword = password.getEditText().getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("password", inputPassword);
                    bundle.putString("email", inputEmail);
                    firebaseAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                UserHomeFragment fragment = new UserHomeFragment();
                                fragment.setArguments(bundle);
                                FragmentTransaction transaction =
                                        fm.beginTransaction();
                                transaction.replace(R.id.ma_container, fragment).commit();
                            }else{
                                Toast.makeText(getActivity(), "Fail to login " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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

            Button sendVerification = forgotPasswordDialog.findViewById(R.id.user_profile_dialog_send_verification_button);
            sendVerification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = resetEmail.getText().toString();
                    firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "Reset link has been sent to your email", Toast.LENGTH_SHORT).show();
                            forgotPasswordDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Fail to send rest link" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

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