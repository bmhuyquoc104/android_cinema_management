package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.ImageView;


import com.example.android_cinema_management.Adapter.TransactionAdapter;
import com.example.android_cinema_management.Model.Transaction;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
    //Declare double totalSavingPoints
    double totalSavingPoints;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser mUser = firebaseAuth.getCurrentUser();

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

            // Calculate total saving points
            for (Transaction transaction:transactionArrayList){
                totalSavingPoints += Double.parseDouble(transaction.getPoint());
                System.out.println("huy ne" + totalSavingPoints);
            }

            UserHomeFragment.accountType = checkAccountType(totalSavingPoints);
            System.out.println(UserHomeFragment.accountType);
            db.collection("Users").document(mUser.getUid()).update("role", UserHomeFragment.accountType);
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

    /*
    *Function to update the account type base on all savingPoints
    * */
    public String checkAccountType (Double savingPoints){
        String accountType = "";
        if(savingPoints > 0 && savingPoints < 100){
            accountType = "Uni-Silver";
        }
        else if (savingPoints >= 100 && savingPoints < 500){
            accountType = "Uni-Gold";
        }
        else if (savingPoints >= 500 && savingPoints < 2000){
            accountType = "Uni-Platinum";
        }
        else if (savingPoints >= 2000) {
            accountType = "Uni-Diamond";
        }
        else{
            accountType = "Uni-Bronze";
        }
        return accountType;
    }
}