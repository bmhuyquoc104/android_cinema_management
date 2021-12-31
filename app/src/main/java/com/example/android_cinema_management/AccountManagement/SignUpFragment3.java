package com.example.android_cinema_management.AccountManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


public class SignUpFragment3 extends Fragment {
    //Declare checkbox and button
    CheckBox payment,security,privacy,terms;
    Button register;
    // Declare several booleans for checkbox
    boolean checkPaymentPolicy = false;
    boolean checkSecurityPolicy = false;
    boolean checkPrivacyPolicy = false;
    boolean checkTermsPolicy = false;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    String userId;
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
        String status = "true";
        String role = "VIP";
        String id = UUID.randomUUID().toString();
        System.out.println("fullName: " +fullName + "email: "+email + "password: "+password + confirmPassword + dateOfBirth + phone+address+gender);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        User user = new User(email, fullName, password, gender, dateOfBirth, address, status, phone, role, id);

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                userId = firebaseAuth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("Users").document(userId);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: user has been created: Successfully" + userId);
                    }
                });

            }
        });

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