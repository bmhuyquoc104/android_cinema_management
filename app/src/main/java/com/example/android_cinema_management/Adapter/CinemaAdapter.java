package com.example.android_cinema_management.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.SearchCinema;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;


public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.myViewHolder>  {

    private SearchCinema searchCinema;
    private List<Cinema> cinemaList;

    public CinemaAdapter(SearchCinema searchCinema, List<Cinema> cinemaList){
        this.searchCinema = searchCinema;
        this.cinemaList = cinemaList;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinema_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.name.setText(cinemaList.get(position).getName());
        holder.address.setText(cinemaList.get(position).getAddress());
        holder.latitude.setText(cinemaList.get(position).getLatitude());
        holder.longitude.setText(cinemaList.get(position).getLongitude());
        holder.contactNumber.setText(cinemaList.get(position).getContactNumber());
        holder.imageUrl.setText(cinemaList.get(position).getImageUrl());
        holder.locationName.setText(cinemaList.get(position).getLocationName());
        holder.cinemaId.setText(cinemaList.get(position).getCinemaId());
    }

    @Override
    public int getItemCount() {
        return cinemaList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView name, address, latitude, longitude, contactNumber, imageUrl, locationName, cinemaId;

        public myViewHolder(@NonNull View itemView){
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.edit_name);
            address = (TextView) itemView.findViewById(R.id.edit_address);
            latitude = (TextView) itemView.findViewById(R.id.edit_latitude);
            longitude = (TextView) itemView.findViewById(R.id.edit_longitude);
            contactNumber = (TextView) itemView.findViewById(R.id.edit_contactNumber);
            imageUrl = (TextView) itemView.findViewById(R.id.edit_imgURL);
            locationName = (TextView) itemView.findViewById(R.id.edit_locationName);
            cinemaId = (TextView) itemView.findViewById(R.id.edit_cinemaId);
        }

    }
}
