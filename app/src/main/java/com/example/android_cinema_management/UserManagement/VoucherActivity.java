package com.example.android_cinema_management.UserManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Adapter.VoucherAdapter;
import com.example.android_cinema_management.Model.Voucher;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.VoucherDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {
    //Declare voucher list
    ArrayList<Voucher> voucherList;
    //Declare imageView,textview
    ImageView close;
    public static TextView point;
    public static String currentPoint;
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private VoucherAdapter voucherAdapter;

    //Declare Firebase services
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
        close = findViewById(R.id.voucher_close_iv);
        point = findViewById(R.id.voucher_current_point_tv);
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

        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot docSnap = task.getResult();
                    if (docSnap != null){
                        currentPoint = docSnap.getString("point");
                        point.setText("Total Points: " + currentPoint);
                    }
                }
            }
        });

        close.setOnClickListener(view->{
            finish();
        });

    }

    @SuppressLint("SetTextI18n")
    public static void updateTotalPoints(TextView textView, String point){
        textView.setText("Total Points: " + point);
    }
}