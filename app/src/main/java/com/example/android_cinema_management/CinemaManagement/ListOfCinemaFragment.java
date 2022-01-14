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
            System.out.println("CINEMA LIST: " + cinemaArrayList);

            // Set fixed size for recycler view
            recyclerView = view.findViewById(R.id.admin_list_cinema_recycler_view);
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
                    Cinema cinema = new Cinema(
                            doc.getString("name"),
                            doc.getString("address"),
                            // Parse the database type string to double
                            Double.parseDouble(Objects.requireNonNull(doc.getString("latitude"))),
                            // Parse the database type string to double
                            Double.parseDouble(Objects.requireNonNull(doc.getString("longitude"))),
                            // Parse the database type string to double
                            Double.parseDouble(Objects.requireNonNull(doc.getString("rate"))),
                            doc.getString("contactNumber"),
                            doc.getString("imageURL"),
                            doc.getString("locationName"),
                            doc.getString("id"),
                            // Parse the database type string to int
                            Integer.parseInt(Objects.requireNonNull(doc.getString("review"))),
                            doc.getString("city"));
                    cinemaArrayList.add(cinema);
                    if (count == Objects.requireNonNull(task.getResult()).size()) {
                        callback.run();
                    }
                }
            }
        });
    }
}