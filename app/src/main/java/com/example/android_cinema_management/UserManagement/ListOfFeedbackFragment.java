package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.FeedbackAdapter;
import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;

import java.util.ArrayList;


public class ListOfFeedbackFragment extends Fragment {
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private FeedbackAdapter feedbackAdapter;
    //Declare Movie list
    public static ArrayList<Feedback> feedbackArrayList;
    public ListOfFeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_feedback, container, false);
        // Set fixed size for recycler view
        recyclerView = view.findViewById(R.id.user_feedback_recyclerview);
        recyclerView.setHasFixedSize(true);
        feedbackAdapter = new FeedbackAdapter(getContext(),feedbackArrayList);
        recyclerView.setLayoutManager(layoutManager);
        // Specify an adapter
        recyclerView.setAdapter(feedbackAdapter);
        return view;
    }
}