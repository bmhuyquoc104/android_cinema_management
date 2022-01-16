package com.example.android_cinema_management.database;

import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.Model.Transaction;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class TransactionDatabase {
    //Function get transaction data
    public static void getTransactionByEmail(FirebaseFirestore db, ArrayList<Transaction> transactionArrayList, Runnable callback, FirebaseUser user){
        String email = user.getEmail();
        System.out.println("my email" + email);
        ArrayList<Transaction> transactionArrayList2 = new ArrayList<>();
        transactionArrayList.clear();
        db.collection("transaction").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    count++;
                    Transaction dataContainer = doc.toObject(Transaction.class);
                    transactionArrayList2.add(dataContainer);
                    System.out.println(transactionArrayList2.size());

//                    System.out.println(transactionArrayList);

                }
                for ( Transaction transaction:
                        transactionArrayList2) {
                    System.out.println("database" + transaction.getUser().get("email"));
                    System.out.println("my email" + email);
                    if(Objects.equals(transaction.getUser().get("email"), email)){
                        System.out.println(Objects.equals(transaction.getUser().get("email"), email));
                        transactionArrayList.add(transaction);
                        System.out.println(transactionArrayList);
                    }
                }
                if (count == Objects.requireNonNull(task.getResult()).size()) {
                    callback.run();
                }
            }

        });
    }
}
