package com.example.android_cinema_management.Adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class UserComboAdapter extends RecyclerView.Adapter<UserComboAdapter.MyViewHolder> {

    private ArrayList<Combo> comboArrayList;
    Context context;
    //Declare vietnamese currency format
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public UserComboAdapter(ArrayList<Combo> comboArrayList, Context context) {
        this.comboArrayList = comboArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserComboAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_row_of_combo, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserComboAdapter.MyViewHolder holder, int position) {
        String priceFormat = formatter.format(Double.parseDouble(comboArrayList.get(position).getPrice()));
        holder.price.setText(priceFormat + " VNĐ");
        holder.name.setText(comboArrayList.get(position).getName());
        holder.description.setText(comboArrayList.get(position).getDescription());
        // Load image to the holder using Picasso library
        Picasso.get().load(comboArrayList.get(position).getImage()).into(holder.comboImage);
    }

    @Override
    public int getItemCount() {
        return comboArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView price, name,description;
        ImageView comboImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.user_combo_price_tv);
            name = itemView.findViewById(R.id.user_combo_name_tv);
            description = itemView.findViewById(R.id.user_combo_description_tv);
            comboImage = itemView.findViewById(R.id.user_combo_image_iv);
        }
    }

}
