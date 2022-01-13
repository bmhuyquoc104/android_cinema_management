package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Transaction;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    BottomSheetDialog globalSheetTracker;
    //Initialize context
   private Context context;
    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();
    //Initialize ArrayList
    private ArrayList<Transaction> transactionArrayList;
    //Declare vietnamese currency format
    DecimalFormat formatter = new DecimalFormat("###,###,###");


    public TransactionAdapter(Context context, ArrayList<Transaction> transactionArrayList) {
        this.context = context;
        this.transactionArrayList = transactionArrayList;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_row_of_transaction, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String priceFormat = formatter.format(Double.parseDouble(transactionArrayList.get(position).getPrice()));
        holder.screen.setText(transactionArrayList.get(position).getScreen());
        holder.movie.setText("Movie: " + transactionArrayList.get(position).getMovie());
        holder.date.setText("Date: " + transactionArrayList.get(position).getDate());
        holder.time.setText("Time: " + transactionArrayList.get(position).getTime());
        holder.seat.setText("Seat: " + transactionArrayList.get(position).getSeat());
        holder.price.setText("Total Price: "  + priceFormat + " VNĐ");
        holder.seeMore.setOnClickListener(view ->{
            View viewDialog = LayoutInflater.from(context).inflate(R.layout.transaction_bottom_sheet,null);
            // Create bottom sheet dialog with layout and theme
            BottomSheetDialog cinemaBottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetDialogTheme);
            globalSheetTracker = cinemaBottomSheetDialog;
            cinemaBottomSheetDialog.setCancelable(false);
            cinemaBottomSheetDialog.setCanceledOnTouchOutside(false);
            // Set content for bottom sheet
            cinemaBottomSheetDialog.setContentView(viewDialog);
            //Start bottom sheet
            cinemaBottomSheetDialog.show();

            // Declare and binding the value to XML layout
            TextView cinema = viewDialog.findViewById(R.id.transaction_cinema_name_tv);
            TextView ticketType = viewDialog.findViewById(R.id.transaction_ticket_type_tv);
            TextView ticketQuantity = viewDialog.findViewById(R.id.transaction_ticket_quantity_tv);
            TextView comboType = viewDialog.findViewById(R.id.transaction_combo_type_tv);
            TextView comboQuantity = viewDialog.findViewById(R.id.transaction_combo_quantity_tv);
            TextView paymentMethod = viewDialog.findViewById(R.id.transaction_payment_method_tv);
            TextView savingPoint = viewDialog.findViewById(R.id.transaction_point_tv);
            TextView name = viewDialog.findViewById(R.id.transaction_fullName_tv);
            TextView email = viewDialog.findViewById(R.id.transaction_email_tv);
            TextView phone = viewDialog.findViewById(R.id.transaction_phone_tv);
            ImageView close = viewDialog.findViewById(R.id.transaction_bottom_sheet_close);

            DocumentReference documentReference = db.collection("Users").document(mUser.getUid());
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot docSnap = task.getResult();
                    if (docSnap != null) {
//                        userId = docSnap.getString("Id");
//                        userName = docSnap.getString("fullName");
                        name.setText(docSnap.getString("fullName"));
                        phone.setText(docSnap.getString("phone"));
                        email.setText(docSnap.getString("email"));
                    }
                }
            });
            // Render the UI dynamically by the current cinema
            // Use this library to render the image by url
            cinema.setText(transactionArrayList.get(position).getCinema());
            ticketType.setText(transactionArrayList.get(position).getTicketType());
            ticketQuantity.setText(transactionArrayList.get(position).getTicketQuantity());
            comboType.setText(transactionArrayList.get(position).getComboType());
            comboQuantity.setText(transactionArrayList.get(position).getComboQuantity());
            paymentMethod.setText(transactionArrayList.get(position).getPaymentMethod());
            savingPoint.setText(transactionArrayList.get(position).getPoint() + "points");

            // close the bottom sheet
            close.setOnClickListener(view2 ->{
                cinemaBottomSheetDialog.dismiss();
            });
        });
    }

    @Override
    public int getItemCount() {
        return transactionArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView screen, movie, date, time, seat,  price,seeMore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            screen = itemView.findViewById(R.id.list_of_transaction_screen_tv);
            movie = itemView.findViewById(R.id.list_of_transaction_movie_tv);
            date = itemView.findViewById(R.id.list_of_transaction_date_tv);
            time = itemView.findViewById(R.id.list_of_transaction_time_tv);
            price = itemView.findViewById(R.id.list_of_transaction_price_tv);
            seat = itemView.findViewById(R.id.list_of_transaction_seat_tv);
            seeMore = itemView.findViewById(R.id.list_of_transaction_seeMore_tv);
        }
    }


}
