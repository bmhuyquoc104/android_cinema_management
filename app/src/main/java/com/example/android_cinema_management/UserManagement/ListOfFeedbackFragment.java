package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.FeedbackAdapter;
import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;


public class ListOfFeedbackFragment extends Fragment {
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private FeedbackAdapter feedbackAdapter;
    //Declare Movie list
    public static ArrayList<Feedback> globalFeedbackArrayList;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    String userId = user.getUid();
    public ListOfFeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_feedback, container, false);

        globalFeedbackArrayList = new ArrayList<>();
        getFeedbacks(db, globalFeedbackArrayList, () -> {
            System.out.println("Feedback ArrayList: " + globalFeedbackArrayList);

            // Set fixed size for recycler view
            recyclerView = view.findViewById(R.id.user_feedback_recyclerview);
            recyclerView.setHasFixedSize(true);
            feedbackAdapter = new FeedbackAdapter(getContext(),globalFeedbackArrayList);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            // Specify an adapter
            recyclerView.setAdapter(feedbackAdapter);
        });

        return view;
    }


    //Function get reviews data
    private void getFeedbacks(FirebaseFirestore db, ArrayList<Feedback> feedbackArrayList, Runnable callback) {
        globalFeedbackArrayList.clear();
        db.collection("feedback").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    count++;
                    Feedback dataContainer = doc.toObject(Feedback.class);
                    feedbackArrayList.add(dataContainer);
                    if (count == Objects.requireNonNull(task.getResult()).size()) {
                        callback.run();
                    }
                }
            }
        });
    }
}