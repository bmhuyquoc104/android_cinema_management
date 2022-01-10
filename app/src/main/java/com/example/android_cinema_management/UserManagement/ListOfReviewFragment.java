package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class ListOfReviewFragment extends Fragment {
    //Declare EditText and Button
    EditText reviewMovieName, reviewBox, ratingMovie;
    Button postReviewButton;

    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String userId;

    public ListOfReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_of_review, container, false);


        return view;
    }
}