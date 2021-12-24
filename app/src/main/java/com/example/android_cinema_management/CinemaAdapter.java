package com.example.android_cinema_management;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.edit_name.setText(model.getName());
        holder.edit_address.setText(model.getAddress());
        holder.edit_latitude.setText(model.getLatitude());
        holder.edit_longitude.setText(model.getLongitude());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinema_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView edit_name, edit_address, edit_latitude, edit_longitude;

        public myViewHolder(@NonNull View itemView){
            super(itemView);

            edit_name = (TextView) itemView.findViewById(R.id.edit_name);
            edit_address = (TextView) itemView.findViewById(R.id.edit_address);
            edit_latitude = (TextView) itemView.findViewById(R.id.edit_latitude);
            edit_longitude = (TextView) itemView.findViewById(R.id.edit_longitude);
        }
    }
}
