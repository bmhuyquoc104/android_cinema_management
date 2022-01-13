package com.example.android_cinema_management.CinemaManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.CinemaAdminAdapter;
import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class ListOfCinemaFragment extends Fragment {

    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private CinemaAdminAdapter cinemaAdminAdapter;
    //Declare Movie list
    public static ArrayList<Cinema> cinemaArrayList;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    public ListOfCinemaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_of_cinema, container, false);

        cinemaArrayList = new ArrayList<>();
        //call function get reviews data
        getCinemas(db, cinemaArrayList, () -> {
            System.out.println("REVIEWS LIST: " + cinemaArrayList);

            // Set fixed size for recycler view
            recyclerView = view.findViewById(R.id.user_list_reviews_recycler_view);
            recyclerView.setHasFixedSize(true);
            cinemaAdminAdapter = new CinemaAdminAdapter(getActivity(), cinemaArrayList);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            // Specify an adapter
            recyclerView.setAdapter(cinemaAdminAdapter);
        });

        return view;
    }

    //Function get reviews data
    private void getCinemas(FirebaseFirestore db, ArrayList<Cinema> cinemaArrayList, Runnable callback) {
        cinemaArrayList.clear();
        db.collection("Cinema").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    count++;
                    Cinema dataContainer = doc.toObject(Cinema.class);
                    cinemaArrayList.add(dataContainer);
                    if (count == Objects.requireNonNull(task.getResult()).size()) {
                        callback.run();
                    }
                }
            }
        });
    }
}