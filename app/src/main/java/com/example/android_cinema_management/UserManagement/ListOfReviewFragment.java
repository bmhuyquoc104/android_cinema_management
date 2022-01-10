package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_cinema_management.Adapter.FeedbackAdapter;
import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class ListOfReviewFragment extends Fragment {
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private ReviewAdapter reviewAdapter;
    //Declare Movie list
    public static ArrayList<Review> reviewArrayList;

    public ListOfReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_of_review, container, false);
        // Set fixed size for recycler view
        recyclerView = view.findViewById(R.id.user_list_reviews_recycler_view);
        recyclerView.setHasFixedSize(true);
        reviewAdapter = new ReviewAdapter(getContext(),reviewArrayList);
        recyclerView.setLayoutManager(layoutManager);
        // Specify an adapter
        recyclerView.setAdapter(reviewAdapter);
        return view;
    }
}