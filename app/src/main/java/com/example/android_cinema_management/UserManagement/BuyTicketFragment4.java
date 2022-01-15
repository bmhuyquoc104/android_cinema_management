package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.VoucherAdapter;
import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.sql.SQLOutput;
import java.util.ArrayList;


public class BuyTicketFragment4 extends Fragment {
    // Declare textview
    TextView phone,email,fullName;
    //Declare Button
    Button purchaseBtn;

    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();

    //Declare boolean
    private boolean choosePayment = false;

    //Declare payment array options
    ArrayList<String> paymentsArray = new ArrayList<>();

    //Declare array adapter
    ArrayAdapter<String> paymentAdapterItems;

    // Declare auto complete text view
    AutoCompleteTextView paymentAutoCompleteTextView;

    //Declare text input layout
    TextInputLayout payment;
    String paymentChosen;

    //Declare button
    Button purchase;

    public BuyTicketFragment4() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_ticket4, container, false);

        //Binding to XML value
        phone = view.findViewById(R.id.buy_ticket_phone_tv);
        email = view.findViewById(R.id.buy_ticket_email_tv);
        fullName = view.findViewById(R.id.buy_ticket_name_tv);
        payment = view.findViewById(R.id.buy_by_movie_payment_text_layout);
        purchase = view.findViewById(R.id.buy_by_movie_purchase_bt);
        purchase.setEnabled(false);
        //Initialize mAth db
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //Initialize value for payment array
        paymentsArray.add("Cash");
        paymentsArray.add("Credit Card");
        paymentsArray.add("Debit Card");
        paymentsArray.add("Zalo Pay, Momo Pay");

        // Set the dropdown option for choosing payment method
        paymentAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, paymentsArray);
        paymentAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_payment_auto_complete_text);
        paymentAutoCompleteTextView.setAdapter(paymentAdapterItems);
        paymentAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                paymentChosen = parent.getItemAtPosition(position).toString();
                // payment layout is  chosen
                choosePayment = true;
                if (choosePayment){
                    //Enable the next button
                    purchase.setEnabled(true);
                }else{
                    //Keep disable next button
                    purchase.setEnabled(false);
                }
            }
        });

        //getting content of all previous user input data in previous fragments
        Bundle bundle = this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
        String movie = bundle.getString("movie");
        String cinema = bundle.getString("cinema");
        String date = bundle.getString("date");
        String screen = bundle.getString("screen");
        String time = bundle.getString("time");
        String ticketChosen = bundle.getString("ticket");
        String ticketTotalQuantity = bundle.getString("ticketQuantity");
        String totalPrice = bundle.getString("price");
        String comboChosen = bundle.getString("combo");
        String comboTotalQuantity = bundle.getString("comboQuantity");
        String seatChosen = bundle.getString("seat");
        String transactionId = UUID.randomUUID().toString();


        int pointInInteger = calculatePointByPrice(totalPrice);
        String point = Integer.toString(pointInInteger);
        DocumentReference documentReference = db.collection("Users").document(mUser.getUid());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot docSnap = task.getResult();
                if (docSnap != null) {
//                        userId = docSnap.getString("Id");
//                        userName = docSnap.getString("fullName");
                    fullName.setText(docSnap.getString("fullName"));
                    phone.setText(docSnap.getString("phone"));
                    email.setText(docSnap.getString("email"));
                }
            }
        });

        /*
        Function to put all input value from user to database
        **/
        purchase.setOnClickListener(View -> {

            Spanned successMessage = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                    .setHtml(
                            "<h3> You Purchase Have Successfully Confirmed. </p>" +
                                    "<p>Please come to the chosen cinema with your <strong > UniCard and ID </strong> to make an payment and receive " +
                                    "the ticket.</p>" +
                                    "<h3> Thank you for using our service! </h3>"));



            Map<String, Object> transactionMap = new HashMap<>();
            transactionMap.put("transactionId", transactionId);
            transactionMap.put("movie", movie);
            transactionMap.put("cinema", cinema);
            transactionMap.put("date", date);
            transactionMap.put("time", time);
            transactionMap.put("screen", screen);
            transactionMap.put("ticketType", ticketChosen);
            transactionMap.put("ticketQuantity", ticketTotalQuantity);
            transactionMap.put("comboType", comboChosen);
            transactionMap.put("comboQuantity", comboTotalQuantity);
            transactionMap.put("price", totalPrice);
            transactionMap.put("seat", seatChosen);
            transactionMap.put("paymentMethod", paymentChosen);
            transactionMap.put("point", point);

            //Get current user login
            DocumentReference docRef = db.collection("Users").document(mUser.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot docSnap = task.getResult();
                        User user = docSnap.toObject(User.class);
                        Map<String, String> userMap = new HashMap<>();
                        userMap.put("fullName", Objects.requireNonNull(user).getFullName());
                        userMap.put("email", user.getEmail());
                        userMap.put("phone", user.getPhone());
                        //Then put userMap into reviewMap
                        transactionMap.put("user",userMap);

                        //Saving buy ticket information into transaction's collection
                        DocumentReference documentReferenceForReview = db.collection("transaction")
                                .document(transactionId);
                        documentReferenceForReview.set(transactionMap).addOnCompleteListener(taskInner -> {
                            if (taskInner.isSuccessful()) {
                                VoucherAdapter.openSuccessfulDialog(R.raw.book_success,successMessage,getContext());
                            }
                        });
                    }
                }
            });


        });
        return view;
    }

    private int calculatePointByPrice(String price){
        int point = 0;
        double priceToGetPoint = 100000;
        if(priceToGetPoint > Double.parseDouble(price)){
            point = 0;
        }
        else {
            while (priceToGetPoint < Double.parseDouble(price)) {
                point += 10;
                priceToGetPoint += 100000;
            }
        }
        return  point;
    };
}