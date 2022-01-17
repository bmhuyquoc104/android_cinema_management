package com.example.android_cinema_management.UserManagement.AdminManagment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.ComboAdminAdapter;
import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.ComboDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class ListOfComboAdminFragment extends Fragment {

    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private ComboAdminAdapter comboAdminAdapter;
    //Declare Movie list
    public static ArrayList<Combo> comboArrayList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ListOfComboAdminFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_of_combo_admin, container, false);
        comboArrayList = new ArrayList<>();
        //call function get reviews data
        ComboDatabase.getCombos(db, comboArrayList, () -> {
            System.out.println("COMBO LIST: " + comboArrayList);

            // Set fixed size for recycler view
            recyclerView = view.findViewById(R.id.admin_combo_list_recycler_view);
            recyclerView.setHasFixedSize(true);
            comboAdminAdapter = new ComboAdminAdapter(getActivity(), comboArrayList);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            // Specify an adapter
            recyclerView.setAdapter(comboAdminAdapter);
        });

        return view;
    }

    //Function get combos data
    private void getCombos(FirebaseFirestore db, ArrayList<Combo> comboArrayList, Runnable callback) {
        comboArrayList.clear();
        db.collection("combo").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    count++;
                    Combo dataContainer = doc.toObject(Combo.class);
                    comboArrayList.add(dataContainer);
                    System.out.println(comboArrayList);
                    if (count == Objects.requireNonNull(task.getResult()).size()) {
                        callback.run();
                    }
                }
            }
        });
    }
}