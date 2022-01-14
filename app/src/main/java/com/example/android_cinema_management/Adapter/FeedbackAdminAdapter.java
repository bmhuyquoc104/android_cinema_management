package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.CinemaManagement.Update_and_DeleteCinema;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminManagment.ReplyFeedbackActivity;

import java.util.ArrayList;

public class FeedbackAdminAdapter extends RecyclerView.Adapter<FeedbackAdminAdapter.MyViewHolder>{

    Context context;

    ArrayList<Feedback> feedbackAdminArrayList;

    public FeedbackAdminAdapter(Context context, ArrayList<Feedback> feedbackAdminArrayList) {
        this.context = context;
        this.feedbackAdminArrayList = feedbackAdminArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_feedback, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.feedbackTime.setText("Time: " + feedbackAdminArrayList.get(position).getTime());
        holder.feedbackTopic.setText("Topic: "+ feedbackAdminArrayList.get(position).getTopic());
        holder.feedbackDate.setText("Date: "+ feedbackAdminArrayList.get(position).getDate());
        holder.feedbackContent.setText("Message: "+ feedbackAdminArrayList.get(position).getFeedbackContent());
    }

    @Override
    public int getItemCount() {
        return feedbackAdminArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView feedbackTopic, feedbackDate, feedbackContent,feedbackTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            feedbackTopic = itemView.findViewById(R.id.list_of_feedback_topic_tv);
            feedbackDate = itemView.findViewById(R.id.list_of_feedback_date_tv);
            feedbackContent = itemView.findViewById(R.id.list_of_feedback_message_tv);
            feedbackTime = itemView.findViewById(R.id.list_of_feedback_time_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Feedback feedback = feedbackAdminArrayList.get(getAbsoluteAdapterPosition());
                    Intent intent = new Intent(context, ReplyFeedbackActivity.class);
                    intent.putExtra("feedback", feedback);
                    context.startActivity(intent);
                }
            });
        }
    }
}
