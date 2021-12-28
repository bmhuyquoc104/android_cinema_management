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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class CinemaAdapter extends FirebaseRecyclerAdapter<Cinema,CinemaAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CinemaAdapter(@NonNull FirebaseRecyclerOptions<Cinema> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Cinema model) {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.latitude.setText(model.getLatitude());
        holder.longitude.setText(model.getLongitude());
        holder.contactNumber.setText(model.getContactNumber());
        holder.imageUrl.setText(model.getImageUrl());
        holder.locationName.setText(model.getLocationName());
        holder.cinemaId.setText(model.getCinemaId());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinema_item,parent,false);
        return new myViewHolder(view);
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
            locationName = (TextView) itemView.findViewById(R.id.edit_locationName);
            cinemaId = (TextView) itemView.findViewById(R.id.edit_cinemaId);
        }

    }
}
