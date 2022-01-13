package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Transaction;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    //Initialize context
   private Context context;

    //Initialize ArrayList
    private ArrayList<Transaction> transactionArrayList;

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
        holder.screen.setText(transactionArrayList.get(position).getScreen());
        holder.movie.setText("Movie: " + transactionArrayList.get(position).getMovie());
        holder.date.setText("Date: " + transactionArrayList.get(position).getDate());
        holder.time.setText("Time: " + transactionArrayList.get(position).getTime());
        holder.seat.setText("Seat: " + transactionArrayList.get(position).getSeat());
        holder.price.setText(transactionArrayList.get(position).getPrice());
        holder.seeMore.setOnClickListener(view ->{

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
