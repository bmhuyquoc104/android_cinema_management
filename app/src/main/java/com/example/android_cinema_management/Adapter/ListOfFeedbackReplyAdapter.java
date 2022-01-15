package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.ReplyFeedback;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class ListOfFeedbackReplyAdapter extends RecyclerView.Adapter<ListOfFeedbackReplyAdapter.MyViewHolder> {

    //Declare context
    Context context;

    //Declare ArrayList
    ArrayList<ReplyFeedback> replyFeedbackArrayList;

    public ListOfFeedbackReplyAdapter(Context context, ArrayList<ReplyFeedback> replyFeedbackArrayList) {
        this.context = context;
        this.replyFeedbackArrayList = replyFeedbackArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_row_of_reply_feedback, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date.setText("Date: " + replyFeedbackArrayList.get(position).getDate());
        holder.time.setText("time: " + replyFeedbackArrayList.get(position).getTime());
        holder.topic.setText(replyFeedbackArrayList.get(position).getTopic());
        holder.feedbackContent.setText(replyFeedbackArrayList.get(position).getFeedbackContent());
        holder.replyContent.setText(replyFeedbackArrayList.get(position).getReplyFeedbackContent());
    }

    @Override
    public int getItemCount() {
        return replyFeedbackArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date, time, feedbackContent, topic, replyContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.list_of_feedback_reply_date_tv);
            time = itemView.findViewById(R.id.list_of_feedback_reply_time_tv);
            feedbackContent = itemView.findViewById(R.id.list_of_feedback_reply_from_user_message_tv);
            topic = itemView.findViewById(R.id.list_of_feedback_topic_tv);
            replyContent = itemView.findViewById(R.id.list_of_feedback_reply_from_admin_message_tv);
        }
    }
}
