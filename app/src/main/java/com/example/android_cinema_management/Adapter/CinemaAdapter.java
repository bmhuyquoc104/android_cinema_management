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
        holder.city.setText(model.getCity());
        holder.district.setText(model.getDistrict());
        holder.ward.setText(model.getWard());
        holder.address.setText(model.getAddress());
        holder.latitude.setText(model.getLatitude());
        holder.longitude.setText(model.getLongitude());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinema_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView name, city, district, ward, address, latitude, longitude;

        public myViewHolder(@NonNull View itemView){
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.edit_name);
            city = (TextView) itemView.findViewById(R.id.edit_city);
            district = (TextView) itemView.findViewById(R.id.edit_district);
            ward = (TextView) itemView.findViewById(R.id.edit_ward);
            address = (TextView) itemView.findViewById(R.id.edit_address);
            latitude = (TextView) itemView.findViewById(R.id.edit_latitude);
            longitude = (TextView) itemView.findViewById(R.id.edit_longitude);
        }

    }
}
