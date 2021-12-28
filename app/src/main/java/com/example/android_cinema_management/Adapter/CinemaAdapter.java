package com.example.android_cinema_management.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.CinemaManagement.CinemaLocation;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.MyViewHolder>{
    // Initialize array list for movie
    private ArrayList<Cinema> cinemaList;
    // Initialize context
    private Context context;

    public CinemaAdapter(Context context,ArrayList<Cinema> cinemaList){
        this.context = context;
        this.cinemaList = cinemaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_cinemas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.cinemaAddress.setText(cinemaList.get(position).getAddress());
            holder.cinemaName.setText(cinemaList.get(position).getName());
            holder.cinemaLocationName.setText(cinemaList.get(position).getLocationName());
            holder.cinemaContact.setText(cinemaList.get(position).getContactNumber());
            Picasso.get().load(cinemaList.get(position).getImageUrl()).into(holder.cinemaImage);
            holder.showMap.setOnClickListener(View ->{
                Intent intent = new Intent(context, CinemaLocation.class);
                // Start intent
                try {
                    context.startActivity(intent);
                }
                // Exception if activity is not found
                catch (ActivityNotFoundException e){
                    Toast.makeText(context,"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
                }
            });
    }


    @Override
    public int getItemCount() {
        return cinemaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cinemaImage;
        TextView cinemaName, cinemaLocationName,cinemaAddress,cinemaContact;
        Button showMap;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaImage = itemView.findViewById(R.id.cinemaImage);
            cinemaName = itemView.findViewById(R.id.cinemaName);
            cinemaLocationName = itemView.findViewById(R.id.cinemaLocationName);
            cinemaAddress = itemView.findViewById(R.id.cinemaAddress);
            cinemaContact = itemView.findViewById(R.id.cinemaPhoneContact);
            showMap = itemView.findViewById(R.id.cinema_showMap);
        }
    }
}
