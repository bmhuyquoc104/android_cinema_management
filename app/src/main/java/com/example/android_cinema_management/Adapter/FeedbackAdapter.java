package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {
    //Initialize context
    private Context context;

    //Initialize ArrayList for Feedback
    private ArrayList<Feedback> feedbackArrayList;

    public FeedbackAdapter(Context context, ArrayList<Feedback> feedbackArrayList) {
        this.context = context;
        this.feedbackArrayList = feedbackArrayList;
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
        holder.feedbackTopic.setText("Topic: "+ feedbackArrayList.get(position).getTopic());
        holder.feedbackDate.setText("Date: "+ feedbackArrayList.get(position).getDate());
        holder.feedbackContent.setText("Message: "+ feedbackArrayList.get(position).getFeedbackContent());
    }

    @Override
    public int getItemCount() {
        return feedbackArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView feedbackTopic, feedbackDate, feedbackContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            feedbackTopic = itemView.findViewById(R.id.list_of_feedback_topic_tv);
            feedbackDate = itemView.findViewById(R.id.list_of_feedback_date_tv);
            feedbackContent = itemView.findViewById(R.id.list_of_feedback_message_tv);
        }
    }
}
