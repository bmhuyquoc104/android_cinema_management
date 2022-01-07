package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {

    private Context context;

    private ArrayList<Feedback> feedbackArrayList;

    public FeedbackAdapter(Context context, ArrayList<Feedback> feedbackArrayList) {
        this.context = context;
        this.feedbackArrayList = feedbackArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userFullName.setText(feedbackArrayList.get(position).getUser().get("fullName").toString());
        holder.feedbackTopic.setText(feedbackArrayList.get(position).getTopic());
        holder.feedbackDate.setText(feedbackArrayList.get(position).getDate());
        holder.feedbackContent.setText(feedbackArrayList.get(position).getFeedbackContent());
    }

    @Override
    public int getItemCount() {
        return feedbackArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userFullName, feedbackTopic, feedbackDate, feedbackContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userFullName = itemView.findViewById(R.id.userName);
            feedbackTopic = itemView.findViewById(R.id.feedbackTopic);
            feedbackDate = itemView.findViewById(R.id.feedbackDate);
            feedbackContent = itemView.findViewById(R.id.feedbackContent);
        }
    }
}
