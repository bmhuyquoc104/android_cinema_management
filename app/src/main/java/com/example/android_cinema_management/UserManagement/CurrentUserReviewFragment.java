package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.R;
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
    private ReviewAdapter reviewAdapter;
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
        return inflater.inflate(R.layout.fragment_current_user_review, container, false);
    }

//    //Function get reviews data
//    private void getReviews(FirebaseFirestore db, ArrayList<Review> reviewArrayList, Runnable callback) {
//        reviewArrayList.clear();
//        db.collection("reviews").whereEqualTo("").get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                int count = 0;
//                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
//                    count++;
//                    Review dataContainer = doc.toObject(Review.class);
//                    reviewArrayList.add(dataContainer);
//                    if (count == Objects.requireNonNull(task.getResult()).size()) {
//                        callback.run();
//                    }
//                }
//            }
//        });
//    }
}