package com.example.android_cinema_management.UserManagement.AdminManagment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.ComboAdminAdapter;
import com.example.android_cinema_management.Adapter.DiscountAdminAdapter;
import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class ListOfAllDiscount extends Fragment {

    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private DiscountAdminAdapter discountAdminAdapter;
    //Declare Movie list
    public static ArrayList<Discount> discountArrayList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ListOfAllDiscount() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_of_all_discount, container, false);
        discountArrayList = new ArrayList<>();
        //call function get reviews data
        getDiscounts(db, discountArrayList, () -> {
            System.out.println("DISCOUNT LIST: " + discountArrayList);

            // Set fixed size for recycler view
            recyclerView = view.findViewById(R.id.admin_discount_list_recycler_view);
            recyclerView.setHasFixedSize(true);
            discountAdminAdapter = new DiscountAdminAdapter(getActivity(), discountArrayList);
            layoutManager = new LinearLayoutManager(getActivity());
            // use grid layout manager to display 2 items in one row
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(gridLayoutManager);
            // Specify an adapter
            recyclerView.setAdapter(discountAdminAdapter);
        });

        return view;
    }

    //Function get combos data
    private void getDiscounts(FirebaseFirestore db, ArrayList<Discount> discountArrayList, Runnable callback) {
        discountArrayList.clear();
        db.collection("Discounts").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    count++;
                    Discount dataContainer = doc.toObject(Discount.class);
                    discountArrayList.add(dataContainer);
                    if (count == Objects.requireNonNull(task.getResult()).size()) {
                        callback.run();
                    }
                }
            }
        });
    }
}