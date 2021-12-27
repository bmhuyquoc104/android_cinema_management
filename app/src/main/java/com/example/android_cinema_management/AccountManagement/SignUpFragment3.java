package com.example.android_cinema_management.AccountManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.android_cinema_management.R;

import java.util.ArrayList;


public class SignUpFragment3 extends Fragment {
    //Declare checkbox and button
    CheckBox payment,security,privacy,terms;
    Button register;
    // Declare several booleans for checkbox
    boolean checkPaymentPolicy = false;
    boolean checkSecurityPolicy = false;
    boolean checkPrivacyPolicy = false;
    boolean checkTermsPolicy = false;
    public SignUpFragment3() {
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
        View view = inflater.inflate(R.layout.fragment_sign_up3, container, false);
        // Binding to value in XML layout
        payment = view.findViewById(R.id.frag3_signUp_payment_policy);
        privacy = view.findViewById(R.id.frag3_signUp_privacyPolicy);
        security = view.findViewById(R.id.frag3_signUp_security_policy);
        terms = view.findViewById(R.id.frag3_signUp_termAndCondition);
        register = view.findViewById(R.id.frag3SignUp_RegisterButton);
        // Disable register button
        register.setEnabled(false);

        // Check if the payment checkbox is checked or not
        payment.setOnClickListener(View -> {
            // Announce the message when checkbox is checked and set the boolean to true
            if (payment.isChecked()) {
                Toast.makeText(getContext(), "You have agree payment policy", Toast.LENGTH_SHORT).show();
                checkPaymentPolicy = true;
                // Announce the message when checkbox is unchecked and set the boolean to false
            } else {
                Toast.makeText(getContext(), "You must agree the policy to continue", Toast.LENGTH_SHORT).show();
                checkPaymentPolicy = false;
            }
            setButtonEnabled();
        });

        privacy.setOnClickListener(View -> {
            // Announce the message when checkbox is checked and set the boolean to true
            if (privacy.isChecked()) {
                Toast.makeText(getContext(), "You have agree payment policy", Toast.LENGTH_SHORT).show();
                checkPrivacyPolicy = true;
                // Announce the message when checkbox is unchecked and set the boolean to false
            } else {
                Toast.makeText(getContext(), "You must agree the policy to continue", Toast.LENGTH_SHORT).show();
                checkPrivacyPolicy = false;
            }
            setButtonEnabled();
        });

        security.setOnClickListener(View -> {
            // Announce the message when checkbox is checked and set the boolean to true
            if (security.isChecked()) {
                Toast.makeText(getContext(), "You have agree payment policy", Toast.LENGTH_SHORT).show();
                checkSecurityPolicy = true;
                // Announce the message when checkbox is unchecked and set the boolean to false
            } else {
                Toast.makeText(getContext(), "You must agree the policy to continue", Toast.LENGTH_SHORT).show();
                checkSecurityPolicy =  false;
            }
            setButtonEnabled();
        });


        terms.setOnClickListener(View -> {
            // Announce the message when checkbox is checked and set the boolean to true
            if (terms.isChecked()) {
                Toast.makeText(getContext(), "You have agree payment policy", Toast.LENGTH_SHORT).show();
                checkTermsPolicy = true;
                // Announce the message when checkbox is unchecked and set the boolean to false
            } else {
                Toast.makeText(getContext(), "You must agree the policy to continue", Toast.LENGTH_SHORT).show();
                checkTermsPolicy = false;
            }
            setButtonEnabled();
        });

        register.setOnClickListener(View -> {
            Toast.makeText(getContext(), "You have register your account successfully", Toast.LENGTH_SHORT).show();
        });

        Bundle bundle =this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
        String fullName = bundle.getString("fullName");
        String email = bundle.getString("email");
        String password = bundle.getString("password");
        String confirmPassword = bundle.getString("confirmPassword");
        String dateOfBirth = bundle.getString("dateOfBirth");
        String phone = bundle.getString("phone");
        String address = bundle.getString("address");
        String gender = bundle.getString("gender");
        System.out.println("fullName: " +fullName + "email: "+email + "password: "+password + confirmPassword + dateOfBirth + phone+address+gender);
        return view;
    }

    // Function to setEnable for register button
    private void setButtonEnabled(){
        if (checkTermsPolicy & checkSecurityPolicy & checkPaymentPolicy & checkPrivacyPolicy){
            System.out.println(register);
            register.setEnabled(true);
        }
        else{
            register.setEnabled(false);
        }
    }
}