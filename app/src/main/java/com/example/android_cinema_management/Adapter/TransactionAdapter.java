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
        holder.date.setText("Date: " + transactionArrayList.get(position).getDate());
        holder.time.setText("Time: " + transactionArrayList.get(position).getTime());
        holder.ticketType.setText(transactionArrayList.get(position).getTicketType());
        holder.quantity.setText("Quantity: " + transactionArrayList.get(position).getQuantity());
        holder.point.setText("Point: " + transactionArrayList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return transactionArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date, time, ticketType, quantity, point;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.list_of_transaction_date_tv);
            time = itemView.findViewById(R.id.list_of_transaction_time_tv);
            ticketType = itemView.findViewById(R.id.list_of_transaction_ticket_type_tv);
            quantity = itemView.findViewById(R.id.list_of_transaction_quantity_tv);
            point = itemView.findViewById(R.id.list_of_transaction_point_tv);
        }
    }
}
