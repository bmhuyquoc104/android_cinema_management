package com.example.android_cinema_management.AccountManagement;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.HomeAdapter;
import com.example.android_cinema_management.Adapter.LogInAdapter;
import com.example.android_cinema_management.MovieManagement.MovieInfoTabLayout;
import com.example.android_cinema_management.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class Accounts extends Fragment {
    //Declare adapter and button
    Button register, signIn;
    LogInAdapter adapter;
    public Accounts() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        register = view.findViewById(R.id.ac_register);
        signIn = view.findViewById(R.id.ac_signIn);
        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();
        // Initialize adapter
        adapter = new LogInAdapter(fm,getLifecycle());
        // Function click change to another fragment
        register.setOnClickListener(View ->{
            Intent intent = new Intent(getContext(),SignUp.class);
            //Send intent to sign Up activity

            // Start intent
            try {
                startActivity(intent);
            }
            // Exception if activity is not found
            catch (ActivityNotFoundException e){
                Toast.makeText(getContext(),"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
            }
        });

        signIn.setOnClickListener(View ->{
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.ma_container, new SignInFragment()).commit();
        });
        return view;

    }
}