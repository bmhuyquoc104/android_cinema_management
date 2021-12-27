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

    public SignUpFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        // Function to change fragment and send data to fragment2
        nextPage.setOnClickListener(View -> {
            inputFullName = Objects.requireNonNull(fullName.getEditText()).getText().toString();
            inputEmail = Objects.requireNonNull(email.getEditText()).getText().toString();
            inputPassword = Objects.requireNonNull(password.getEditText()).getText().toString();
            inputConfirmPassword = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString();
            System.out.println("hyu ne " + inputPassword + inputConfirmPassword + inputEmail + inputFullName);
            if (isValidEmail() & isValidPassword() & isValidFullName() & isValidConfirmPassword()) {
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


    // Function to check fullName is valid or not
    private boolean isValidFullName() {
        // Check string of user input full name is empty or not
        if (inputFullName.isEmpty()) {
            // Set error for full name textInputLayout
            fullName.setError("Field can not be empty");
            return false;
        } else {
            // Set full name error
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    // Function to check email is valid or not
    private boolean isValidEmail() {
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        // Check string email is empty or not
        if (inputEmail.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        }
        // Check if email is in the correct format
        else if (!inputEmail.matches(checkEmail)) {
            // Set error for full name textInputLayout
            email.setError("Invalid Email!");
            return false;
        }
        else {
            // Set full name error
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    // Function to check password is valid or not
    private boolean isValidPassword() {
        // Check string password is empty or not
        if(inputPassword.isEmpty()){
            password.setError("Field can not be empty");
            return false;
        }else{
            // Set password error
            password.setError(null);
            password.setErrorEnabled(false);
        }

        // Check if password has the valid length
        if ( inputPassword.length() < 6) {
            password.setError("Password must be less than 6 in the length.");
            return false;
        }
        // Set password error
        else{
            password.setError(null);
            password.setErrorEnabled(false);
        }

        // Check if the password contains upper character
        String upperCaseChars = "(.*[A-Z].*)";
        if (!inputPassword.matches(upperCaseChars)) {
            // Set password error
            password.setError("Password must have at least one uppercase character");
            return false;
        }
        // Set password error
        else{
            password.setError(null);
            password.setErrorEnabled(false);
        }
        // Check if password contains lower character
        String lowerCaseChars = "(.*[a-z].*)";
        if (!inputPassword.matches(lowerCaseChars)) {
            // Set password error
            password.setError("Password must have at least one lowercase character");
            return false;
        }
        // Set password error
        else{
            password.setError(null);
            password.setErrorEnabled(false);
        }
        // Check if password contains number
        String numbers = "(.*[0-9].*)";
        if (!inputPassword.matches(numbers)) {
            password.setError("Password must have at least one number");
            return false;
        }
        // Set password error
        else{
            password.setError(null);
            password.setErrorEnabled(false);
        }

        // Check if password contains special characters
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!inputPassword.matches(specialChars)) {
            password.setError("Password must have at least one special character among @#$%");
            return false;
        }
        // Set password error
        else{
            password.setError(null);
            password.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidConfirmPassword() {
        // Check string input confirm password is empty or not
        if(inputConfirmPassword.isEmpty()){
            confirmPassword.setError("Field can not be empty");
            return false;
        }
        // Set confirm password error
        else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }

        // Check string length input confirm password
        if (inputConfirmPassword.length() < 6) {
            confirmPassword.setError("Password must be more than 6 characters in length.");
            return false;
        }
        // Set confirm password error
        else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }

        // Check if confirm password contains upper characters
        String upperCaseChars = "(.*[A-Z].*)";
        if (!inputConfirmPassword.matches(upperCaseChars)) {
            confirmPassword.setError("Password must have at least one uppercase character");
            return false;
        }
        // Set confirm password error
        else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }
        // Check if confirm password contains lower characters
        String lowerCaseChars = "(.*[a-z].*)";
        if (!inputConfirmPassword.matches(lowerCaseChars)) {
            confirmPassword.setError("Password must have at least one lowercase character");
            return false;
        }
        // Set confirm password error
        else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }
        // Check if password contains number
        String numbers = "(.*[0-9].*)";
        if (!inputConfirmPassword.matches(numbers)) {
            confirmPassword.setError("Password must have at least one number");
            return false;
        }
        // Set confirm password error
        else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }
        // Check if confirm password contains special chars
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!inputConfirmPassword.matches(specialChars)) {
            confirmPassword.setError("Password must have at least one special character among @#$%");
            return false;
        }
        // Set confirm password error
        else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }
        // Check if the confirm password check password
        if (!inputConfirmPassword.equals(inputPassword)) {
            confirmPassword.setError("ConfirmPassword not match password");
            return false;
        }
        // Set confirm password error
        else {
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
            return true;
        }
    }
}