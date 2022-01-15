package com.example.android_cinema_management.Adapter;

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

    @Override
    public void onBindViewHolder(@NonNull MyViewHold holder, int position) {
        holder.comboName.setText(comboArrayList.get(position).getComboName());
        holder.comboAvailableDate.setText(comboArrayList.get(position).getAvailableDate());
        holder.comboExpireDate.setText(comboArrayList.get(position).getExpireDate());
        holder.comboDescription.setText(comboArrayList.get(position).getDescription());
        Picasso.get().load(comboArrayList.get(position).getImageURL()).into(holder.comboImage);
    }

    @Override
    public int getItemCount() {
        return comboArrayList.size();
    }


    class MyViewHold extends RecyclerView.ViewHolder{
        ImageView comboImage;
        TextView comboName, comboAvailableDate, comboExpireDate, comboDescription;
        public MyViewHold(@NonNull View itemView) {
            super(itemView);

            comboName = itemView.findViewById(R.id.comboName);
            comboAvailableDate = itemView.findViewById(R.id.comboAvailableDate);
            comboExpireDate = itemView.findViewById(R.id.comboExpireDate);
            comboDescription = itemView.findViewById(R.id.comboDescription);
            comboImage = itemView.findViewById(R.id.comboImage);

            itemView.setOnClickListener(view -> {
                Combo combo = comboArrayList.get(getAbsoluteAdapterPosition());
                Intent intent = new Intent(context, UpdateAndDeleteCombo.class);
                intent.putExtra("combo", combo);
                context.startActivity(intent);
            });
        }
    }
}
