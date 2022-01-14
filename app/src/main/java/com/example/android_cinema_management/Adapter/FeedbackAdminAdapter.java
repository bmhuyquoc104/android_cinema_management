package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.Model.ReplyFeedback;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class FeedbackAdminAdapter extends RecyclerView.Adapter<FeedbackAdminAdapter.MyViewHolder>{

    //Initialize context
    private Context context;

    //Initialize ArrayList for Feedback
    private ArrayList<ReplyFeedback> replyFeedbackArrayList;

    public FeedbackAdminAdapter(Context context, ArrayList<ReplyFeedback> replyFeedbackArrayList) {
        this.context = context;
        this.replyFeedbackArrayList = replyFeedbackArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView feedbackTopic, feedbackDate, feedbackContent,feedbackTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            feedbackTopic = itemView.findViewById(R.id.list_of_feedback_topic_tv);
            feedbackDate = itemView.findViewById(R.id.list_of_feedback_date_tv);
            feedbackContent = itemView.findViewById(R.id.list_of_feedback_message_tv);
            feedbackTime = itemView.findViewById(R.id.list_of_feedback_time_tv);
        }
    }
}
