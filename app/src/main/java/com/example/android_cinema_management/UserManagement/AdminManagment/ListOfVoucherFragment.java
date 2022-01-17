package com.example.android_cinema_management.UserManagement.AdminManagment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.ComboAdminAdapter;
import com.example.android_cinema_management.Adapter.VoucherAdminAdapter;
import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.Model.Voucher;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.ComboDatabase;
import com.example.android_cinema_management.database.VoucherDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class ListOfVoucherFragment extends Fragment {

    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private VoucherAdminAdapter voucherAdminAdapter;
    //Declare Movie list
    public static ArrayList<Voucher> voucherArrayList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ListOfVoucherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_of_voucher, container, false);
        voucherArrayList = new ArrayList<>();
        //call function get reviews data
        VoucherDatabase.fetchVoucherDatabase(db, voucherArrayList, () -> {
            System.out.println("VOUCHER LIST: " + voucherArrayList);

            // Set fixed size for recycler view
            recyclerView = view.findViewById(R.id.admin_voucher_list_recycler_view);
            recyclerView.setHasFixedSize(true);
            voucherAdminAdapter = new VoucherAdminAdapter(getActivity(), voucherArrayList);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            // Specify an adapter
            recyclerView.setAdapter(voucherAdminAdapter);
        });

        return view;
    }

}