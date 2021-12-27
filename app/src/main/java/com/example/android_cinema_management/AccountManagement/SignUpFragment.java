package com.example.android_cinema_management.AccountManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class SignUpFragment extends Fragment {
    // Declare edit text
    String inputFullName, inputEmail, inputPassword, inputConfirmPassword;
    TextInputLayout password, confirmPassword, fullName, email;
    Button nextPage;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        // Binding value to XML layout file
        fullName = view.findViewById(R.id.frag1SignUp_textLayout_fullName);
        email = view.findViewById(R.id.frag1SignUp_textLayout_email);
        password = view.findViewById(R.id.frag1SignUp_textLayout_password);
        confirmPassword = view.findViewById(R.id.frag1SignUp_textLayout_confirmPassword);
        nextPage = view.findViewById(R.id.fgSignUp_continueButton);
//        nextPage.setEnabled(false);
        // Get value from user

//        if (fullName.length() > 0 && email.length() >0) {
//            nextPage.setEnabled(true);
//        }

        nextPage.setOnClickListener(View -> {
            inputFullName = Objects.requireNonNull(fullName.getEditText()).getText().toString();
            inputEmail = Objects.requireNonNull(email.getEditText()).getText().toString();
            inputPassword = Objects.requireNonNull(password.getEditText()).getText().toString();
            inputConfirmPassword = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString();
            System.out.println("hyu ne " + inputPassword + inputConfirmPassword + inputEmail + inputFullName);
            if (validateFullName() & validateConfirmPassword() & validateEmail() & validatePassword()) {
                Bundle bundle = new Bundle();
                bundle.putString("fullName", inputFullName);
                bundle.putString("password", inputPassword);
                bundle.putString("email", inputEmail);
                bundle.putString("confirmPassword", inputConfirmPassword);
                SignUpFragment2 fragment2 = new SignUpFragment2();
                fragment2.setArguments(bundle);
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction transaction =
                        fm.beginTransaction();
                transaction.replace(R.id.signUp_frameLayout, fragment2).addToBackStack("fragment1").commit();
            }
        });


        return view;
    }

    private boolean validateFullName() {
        if (inputFullName.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (inputEmail.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!inputEmail.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        if(inputPassword.isEmpty()){
            password.setError("Field can not be empty");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
        }


        if (inputPassword.length() > 15 || inputPassword.length() < 8) {
            password.setError("Password must be less than 20 and more than 8 characters in length.");
            return false;
        } else{
            password.setError(null);
            password.setErrorEnabled(false);
        }


        String upperCaseChars = "(.*[A-Z].*)";
        if (!inputPassword.matches(upperCaseChars)) {
            password.setError("Password must have at least one uppercase character");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!inputPassword.matches(lowerCaseChars)) {
            password.setError("Password must have at least one lowercase character");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
        }
        String numbers = "(.*[0-9].*)";
        if (!inputPassword.matches(numbers)) {
            password.setError("Password must have at least one number");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!inputPassword.matches(specialChars)) {
            password.setError("Password must have at least one special character among @#$%");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateConfirmPassword() {
        if(inputConfirmPassword.isEmpty()){
            confirmPassword.setError("Field can not be empty");
            return false;
        }else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }


        if (inputConfirmPassword.length() > 15 || inputConfirmPassword.length() < 8) {
            confirmPassword.setError("Password must be less than 20 and more than 8 characters in length.");
            return false;
        } else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }


        String upperCaseChars = "(.*[A-Z].*)";
        if (!inputConfirmPassword.matches(upperCaseChars)) {
            confirmPassword.setError("Password must have at least one uppercase character");
            return false;
        }else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!inputConfirmPassword.matches(lowerCaseChars)) {
            confirmPassword.setError("Password must have at least one lowercase character");
            return false;
        }else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }
        String numbers = "(.*[0-9].*)";
        if (!inputConfirmPassword.matches(numbers)) {
            confirmPassword.setError("Password must have at least one number");
            return false;
        }else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!inputConfirmPassword.matches(specialChars)) {
            confirmPassword.setError("Password must have at least one special character among @#$%");
            return false;
        }
        else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }
        if (!inputConfirmPassword.equals(inputPassword)) {
            confirmPassword.setError("ConfirmPassword not match password");
            return false;
        } else {
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
            return true;
        }
    }
}