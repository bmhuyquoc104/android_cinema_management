package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Adapter.ReviewCurrentUserAdapter;
import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.ReviewDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;


public class CurrentUserReviewFragment extends Fragment {
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private ReviewCurrentUserAdapter reviewAdapter;
    //Declare Movie list
    public static ArrayList<Review> reviewArrayList;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    String userId = user.getUid();


    public CurrentUserReviewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_user_review, container, false);

        reviewArrayList = new ArrayList<>();
        ReviewDatabase.getReviewsByCurrentUser(db, reviewArrayList, () -> {

            // Set fixed size for recycler view
            recyclerView = view.findViewById(R.id.user_current_user_review_recycler_view);
            recyclerView.setHasFixedSize(true);
            reviewAdapter = new ReviewCurrentUserAdapter(getActivity(), reviewArrayList);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            // Specify an adapter
            recyclerView.setAdapter(reviewAdapter);
        },user);
        return view;
    }


}