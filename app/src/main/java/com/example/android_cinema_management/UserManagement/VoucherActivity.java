package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Adapter.VoucherAdapter;
import com.example.android_cinema_management.Model.Voucher;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.VoucherDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {
    //Declare voucher list
    ArrayList<Voucher> voucherList;

    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private VoucherAdapter voucherAdapter;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser mUser = firebaseAuth.getCurrentUser();
    String userId = mUser.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        //Binding XML values
        recyclerView = findViewById(R.id.voucher_recycler_view);
        recyclerView.setHasFixedSize(true);

        //Instantiate array list
        voucherList = new ArrayList<>();
        // Instantiate adapter

        VoucherDatabase.fetchVoucherDatabase(db,voucherList,()->{
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);

            voucherAdapter = new VoucherAdapter(voucherList, this);
            // Set layout for recycler view
            recyclerView.setLayoutManager(gridLayoutManager);
            // Set adapter for recycler view
            recyclerView.setAdapter(voucherAdapter);
        });

    }
}