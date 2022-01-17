package com.example.android_cinema_management.TicketAndDiscountManagement;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.HandlerThread;
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

import com.example.android_cinema_management.Adapter.CinemaAdapter;
import com.example.android_cinema_management.Adapter.DiscountAdapter;
import com.example.android_cinema_management.Model.Cinema;
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
    String month;

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
        String monthChosen;
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(getContext(), R.layout.month_list, months);
        monthACT.setAdapter(monthAdapter);
        monthACT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                month = parent.getItemAtPosition(position).toString();

                // Initialize new array list to store result after filtering
                ArrayList<Discount> filterByMonthList = new ArrayList<>();

                // Function filter by month
                filterByMonth(filterByMonthList, discountArrayList, month);

                // Instantiate adapter
                discountAdapter = new DiscountAdapter(getContext(), filterByMonthList);

                // Set layout for recycler view
                Discount.setLayoutManager(gridLayoutManager);

                // Set adapter for recycler view
                Discount.setAdapter(discountAdapter);
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

    public void filterByMonth(ArrayList<Discount> filterDiscountList, ArrayList<Discount> discountList, String inputMonth) {
        for (Discount discount : discountList) {
            if (discount.getMonth() != null && discount.getMonth().equals(inputMonth)) {
                filterDiscountList.add(discount);
            }
        }
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