package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminManagment.AddDiscount;
import com.example.android_cinema_management.UserManagement.AdminManagment.UpdateAndDeleteCombo;
import com.example.android_cinema_management.UserManagement.AdminManagment.UpdateAndDeleteDiscount;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DiscountAdminAdapter extends RecyclerView.Adapter<DiscountAdminAdapter.MyViewHold>{

    Context context;

    ArrayList<Discount> discountArrayList;

    public DiscountAdminAdapter(Context context, ArrayList<Discount> discountArrayList) {
        this.context = context;
        this.discountArrayList = discountArrayList;
    }

    @NonNull
    @Override
    public MyViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_discount,parent,false);
        return new MyViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHold holder, int position) {
        holder.name.setText(discountArrayList.get(position).getName());
        holder.month.setText(discountArrayList.get(position).getMonth());
        Picasso.get().load(discountArrayList.get(position).getImage()).into(holder.discountImage);
    }

    @Override
    public int getItemCount() {
        return discountArrayList.size();
    }

    class MyViewHold extends RecyclerView.ViewHolder{
        ImageView discountImage;
        TextView name, month;
        public MyViewHold(@NonNull View itemView) {
            super(itemView);

            discountImage = itemView.findViewById(R.id.discount);
            name = itemView.findViewById(R.id.name);
            month = itemView.findViewById(R.id.date);


            itemView.setOnClickListener(view -> {
                Discount discount = discountArrayList.get(getAbsoluteAdapterPosition());
                Intent intent = new Intent(context, UpdateAndDeleteDiscount.class);
                intent.putExtra("discount", discount);
                context.startActivity(intent);
            });
        }
    }
}
