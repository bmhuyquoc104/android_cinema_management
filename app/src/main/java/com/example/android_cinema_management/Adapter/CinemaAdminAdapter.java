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

import com.example.android_cinema_management.CinemaManagement.Update_and_DeleteCinema;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CinemaAdminAdapter extends RecyclerView.Adapter<CinemaAdminAdapter.MyViewHolder> {

    //Initialize context
    private Context context;

    //Initialize ArrayList for review
    private ArrayList<Cinema> cinemaArrayList;

    //Create Constructor for CinemaAdminAdapter
    public CinemaAdminAdapter(Context context, ArrayList<Cinema> cinemaArrayList) {
        this.context = context;
        this.cinemaArrayList = cinemaArrayList;
    }

    //Create onCreateViewHolder class
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Binding all content from MyViewHolder into one_row_of_cinema_admin
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_cinema_admin,parent,false);
        return new CinemaAdminAdapter.MyViewHolder(view);
    }

    //Binding to xml file one_row_cinema_admin
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Set holder to display each field
        holder.cinemaAddress.setText(cinemaArrayList.get(position).getAddress());
        holder.cinemaName.setText(cinemaArrayList.get(position).getName());
        holder.cinemaLocationName.setText(cinemaArrayList.get(position).getLocationName());
        holder.cinemaContact.setText(cinemaArrayList.get(position).getContactNumber());
        holder.cinemaCity.setText(cinemaArrayList.get(position).getCity());
        Picasso.get().load(cinemaArrayList.get(position).getImageUrl()).into(holder.cinemaImage);
    }

    //Get all document from collection cinema from Firestore
    @Override
    public int getItemCount() {
        return cinemaArrayList.size();
    }

    //Set up ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cinemaImage;
        TextView cinemaName, cinemaLocationName,cinemaAddress,cinemaContact,cinemaCity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaImage = itemView.findViewById(R.id.cinemaImage);
            cinemaName = itemView.findViewById(R.id.cinemaName);
            cinemaLocationName = itemView.findViewById(R.id.cinemaLocationName);
            cinemaAddress = itemView.findViewById(R.id.cinemaAddress);
            cinemaContact = itemView.findViewById(R.id.cinemaPhoneContact);
            cinemaCity = itemView.findViewById(R.id.cinemaCity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cinema cinema = cinemaArrayList.get(getAbsoluteAdapterPosition());
                    Intent intent = new Intent(context, Update_and_DeleteCinema.class);
                    intent.putExtra("cinema", cinema);
                    context.startActivity(intent);
                }
            });
        }
    }
}
