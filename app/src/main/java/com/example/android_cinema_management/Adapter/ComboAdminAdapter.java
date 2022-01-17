package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminManagment.UpdateAndDeleteCombo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComboAdminAdapter extends RecyclerView.Adapter<ComboAdminAdapter.MyViewHold>{

    Context context;

    ArrayList<Combo> comboArrayList;

    public ComboAdminAdapter(Context context, ArrayList<Combo> comboArrayList) {
        this.context = context;
        this.comboArrayList = comboArrayList;
    }

    @NonNull
    @Override
    public MyViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Binding all content from MyViewHolder into one_row_of_cinema_admin
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_combo_admin,parent,false);
        return new MyViewHold(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHold holder, int position) {
        holder.comboName.setText("Combo name: " + comboArrayList.get(position).getName());
        holder.comboPrice.setText("Price: " + comboArrayList.get(position).getPrice());
        holder.comboDescription.setText(comboArrayList.get(position).getDescription());
        Picasso.get().load(comboArrayList.get(position).getImage()).into(holder.comboImage);
    }

    @Override
    public int getItemCount() {
        return comboArrayList.size();
    }


    class MyViewHold extends RecyclerView.ViewHolder{
        ImageView comboImage;
        TextView comboName, comboPrice, comboDescription;
        public MyViewHold(@NonNull View itemView) {
            super(itemView);

            comboName = itemView.findViewById(R.id.comboName);
            comboPrice = itemView.findViewById(R.id.comboPrice);
            comboDescription = itemView.findViewById(R.id.comboDescription);
            comboImage = itemView.findViewById(R.id.admin_combo_iv);

            itemView.setOnClickListener(view -> {
                Combo combo = comboArrayList.get(getAbsoluteAdapterPosition());
                Intent intent = new Intent(context, UpdateAndDeleteCombo.class);
                intent.putExtra("combo", combo);
                context.startActivity(intent);
            });
        }
    }
}
