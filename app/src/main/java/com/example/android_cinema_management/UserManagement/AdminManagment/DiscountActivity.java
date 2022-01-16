package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.android_cinema_management.Adapter.DiscountAdminAdapter;
import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DiscountActivity extends AppCompatActivity {
    private RecyclerView Discount;
    private DiscountAdminAdapter discountAdapter;
    private ArrayList<String> idArrayList;
    private ArrayList<com.example.android_cinema_management.Model.Discount> discountArrayList;
    FirebaseFirestore db;

    Button postDiscount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        postDiscount = findViewById(R.id.addDiscount);

        Discount = findViewById(R.id.discount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(DiscountActivity.this,2,GridLayoutManager.VERTICAL,false);
        Discount.setLayoutManager(gridLayoutManager);

        //        Discount database
        db = FirebaseFirestore.getInstance();
        discountArrayList = new ArrayList<Discount>();
        idArrayList = new ArrayList<String>();

        discountAdapter = new DiscountAdminAdapter(DiscountActivity.this, discountArrayList, idArrayList);
        EventChangeListener();
        Discount.setAdapter(discountAdapter);

        postDiscount.setOnClickListener(View -> {
            Intent intent = new Intent(DiscountActivity.this, AddDiscount.class);
            startActivity(intent);
        });
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
                                String docID = dc.getDocument().getId();
                                if (docID == null) {
                                    docID = "no id";
                                }
                                idArrayList.add(docID);
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