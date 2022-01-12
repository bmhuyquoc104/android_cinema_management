package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Adapter.TransactionAdapter;
import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.Model.Transaction;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class ListOfTransactionFragment extends Fragment {

    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private TransactionAdapter transactionAdapter;
    //Declare Movie list
    public static ArrayList<Transaction> transactionArrayList;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    String userId = user.getUid();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_of_transaction, container, false);
        Toast.makeText(getActivity(), "TAO NEEEEEEEEEEEEEE", Toast.LENGTH_SHORT).show();
        transactionArrayList = new ArrayList<>();
        //Call function getTransaction
        getTransaction(db, transactionArrayList, () -> {
            System.out.println("TRANSACTION LIST: " + transactionArrayList);

            recyclerView = view.findViewById(R.id.list_transaction_recycler_view);
            recyclerView.setHasFixedSize(true);
            transactionAdapter = new TransactionAdapter(getActivity(), transactionArrayList);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            // Specify an adapter
            recyclerView.setAdapter(transactionAdapter);

        });

        return view;
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