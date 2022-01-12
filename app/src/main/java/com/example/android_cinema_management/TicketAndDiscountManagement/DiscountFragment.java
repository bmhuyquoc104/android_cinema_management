package com.example.android_cinema_management.TicketAndDiscountManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.DiscountAdapter;
import com.example.android_cinema_management.Model.Discount;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import com.example.android_cinema_management.R;

public class DiscountFragment extends Fragment {
    private RecyclerView Discount;
    private DiscountAdapter discountAdapter;
    private TextInputLayout monthMenu;
    private AutoCompleteTextView monthACT;
    private ArrayList<Discount> discountArrayList;
    FirebaseFirestore db;

    public DiscountFragment() {
        // Required empty public constructor
    }


    public static DiscountFragment newInstance(String param1, String param2) {
        return new DiscountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discount, container, false);
        Discount = view.findViewById(R.id.discount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        Discount.setLayoutManager(gridLayoutManager);

        //        Discount database
        db = FirebaseFirestore.getInstance();
        discountArrayList = new ArrayList<Discount>();

        discountAdapter = new DiscountAdapter(getContext(), discountArrayList);
        EventChangeListener();
        Discount.setAdapter(discountAdapter);

//        Month drop-down menu
        monthMenu = view.findViewById(R.id.month);
        monthACT = view.findViewById(R.id.monthItems);

//        Insert month data and set on click
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(getContext(), R.layout.month_list, months);
        monthACT.setAdapter(monthAdapter);
        monthACT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), (String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //    Get the data from Firestore
    private void EventChangeListener() {
        db.collection("Discounts").orderBy("Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                discountArrayList.add(dc.getDocument().toObject(com.example.android_cinema_management.Model.Discount.class));
                            }

                            discountAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //    Clear the data on destroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (discountAdapter != null) {
            discountAdapter.release();
        }
    }
}