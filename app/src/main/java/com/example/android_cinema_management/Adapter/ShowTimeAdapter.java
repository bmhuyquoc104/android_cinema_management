package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Screen;
import com.example.android_cinema_management.Model.ShowTime;
import com.example.android_cinema_management.R;

import java.util.ArrayList;
import java.util.List;

public class ShowTimeAdapter extends RecyclerView.Adapter<ShowTimeAdapter.MyViewHolder> {
    // Initialize array list for movie
    private ArrayList<Cinema> cinemaList;
    private ArrayList<Screen> screenList;
    private List<ShowTime> showTimeList;
    // Initialize context
    private Context context;

    public ShowTimeAdapter(Context context,ArrayList<Cinema> cinemaList){
        this.context = context;
        this.cinemaList = cinemaList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_cinema_showtime,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowTimeAdapter.MyViewHolder holder, int position) {
        holder.cinemaTitle.setText(cinemaList.get(position).getName());
        System.out.println("huynenne" + cinemaList.get(position).getName());
        for (Cinema cinema:cinemaList
             ) {
            screenList = cinema.getScreens();
            System.out.println("sgnskjlgnalk" + screenList);
        }

        for(Screen screen:screenList){
            showTimeList = screen.getShowTime();
            System.out.println("hndgkjng" + showTimeList);
        }

//        holder.cinemaScreen.setText(screenList.get(position).getScreenId());
//        holder.cinemaTime.setText(showTimeList.get(position).getTime());
//        holder.cinemaType.setText(showTimeList.get(position).getShowTimeType());
    }

    @Override
    public int getItemCount() {
        return cinemaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cinemaTitle,cinemaScreen,cinemaTime,cinemaType;
        LinearLayout linearLayoutVertical;
        LinearLayout linearLayoutHorizontal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaTitle = itemView.findViewById(R.id.showtime_cinema_title_tv);
            cinemaScreen = itemView.findViewById(R.id.showtime_cinema_screen_tv);
            cinemaTime = itemView.findViewById(R.id.showtime_cinema_time_tv);
            cinemaType = itemView.findViewById(R.id.showtime_cinema_type_tv);
            linearLayoutVertical = itemView.findViewById(R.id.showtime_linear_layout_vertical_ll);
            linearLayoutHorizontal = itemView.findViewById(R.id.showtime_linear_layout_horizontal_container_ll);
        }
    }
}
