package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.TransactionAdapter;
import com.example.android_cinema_management.Model.Transaction;
import com.example.android_cinema_management.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    //Declare imageview
    ImageView close;
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private TransactionAdapter transactionAdapter;
    //Declare Movie list
    public static ArrayList<Transaction> transactionArrayList;
    //Declare bottom sheet dialog

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    String userId = user.getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        //Binding with XML values
        close = findViewById(R.id.transaction_close_iv);
        // Initialize fragment manager

        close.setOnClickListener(view->{
            finish();
        });
        transactionArrayList = new ArrayList<>();
        //Call function getTransaction
        getTransaction(db, transactionArrayList, () -> {
            System.out.println("TRANSACTION LIST: " + transactionArrayList);

            recyclerView = findViewById(R.id.list_transaction_recycler_view);
            recyclerView.setHasFixedSize(true);
            transactionAdapter = new TransactionAdapter(this, transactionArrayList);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            // Specify an adapter
            recyclerView.setAdapter(transactionAdapter);

        });

    }
    //Function get transaction data
    private void getTransaction(FirebaseFirestore db, ArrayList<Transaction> transactionArrayList, Runnable callback){
        transactionArrayList.clear();
        db.collection("transaction").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                int count = 0;
                for (DocumentSnapshot doc : task.getResult()){
                    count++;
                    Transaction dateContainer = doc.toObject(Transaction.class);
                    transactionArrayList.add(dateContainer);
                    if (count == task.getResult().size()){
                        callback.run();
                    }
                }
            }
        });
    }

}