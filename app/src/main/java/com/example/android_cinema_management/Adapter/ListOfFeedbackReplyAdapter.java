package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.ReplyFeedback;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminManagment.UpdateAndDeleteCombo;
import com.example.android_cinema_management.UserManagement.UserNotification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;

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
                .inflate(R.layout.one_row_of_notification, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Set the notification string
        SpannableStringBuilder builder = new SpannableStringBuilder();
        Spanned message = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                .setHtml(
                        "<p><strong>Universal Customer Service</strong> have replied your feedback on </p>" ));
        // Text and color for string 1
        SpannableString str1 = new SpannableString(message);
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161, 161, 161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString topic = new SpannableString(replyFeedbackArrayList.get(position).getTopic());
        topic.setSpan(new ForegroundColorSpan(Color.rgb(222, 22, 25)), 0, topic.length(), 0);
        builder.append(topic);


        holder.reply.setText(builder, Button.BufferType.SPANNABLE);
        Picasso.get().load(replyFeedbackArrayList.get(position).getAdminImage()).into(holder.image);
        // Show detail when click item
        holder.itemView.setOnClickListener(View ->{
            String dateTime =   replyFeedbackArrayList.get(position).getDate() + " " +
                    replyFeedbackArrayList.get(position).getTime();
            openDetailFeedback(R.layout.one_row_of_reply_feedback,
                    replyFeedbackArrayList.get(position).getTopic(),
                    dateTime,
                    replyFeedbackArrayList.get(position).getReplyId(),
                    replyFeedbackArrayList.get(position).getAdminEmail(),
                    replyFeedbackArrayList.get(position).getUserEmail(),
                    replyFeedbackArrayList.get(position).getFeedbackContent(),
                    replyFeedbackArrayList.get(position).getReplyFeedbackContent());
        });
    }

    @Override
    public int getItemCount() {
        return replyFeedbackArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView reply;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.notification_image_iv);
            reply = itemView.findViewById(R.id.notification_mentioned_tv);

        }
    }

    public ArrayList<ReplyFeedback> getReplyFeedbackArrayList() {
        return replyFeedbackArrayList;
    }

    /*
    * Function to remove feedback object from array list
    * */
    public void removeItem(int position) {
        replyFeedbackArrayList.remove(position);
        notifyItemRemoved(position);
    }

    /*
     * Function to restore feedback object from array list
     * */
    public void restoreItem(ReplyFeedback item, int position) {
        System.out.println(item);
        replyFeedbackArrayList.add(position, item);
        System.out.println(replyFeedbackArrayList);
        notifyItemInserted(position);
    }

    /*
     * Function to remove feedback object from database
     * */
    public void removeFromFireStore (FirebaseFirestore db,String id){
        System.out.println("positionId" + id);
        db.collection("replyToFeedback").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                        }else {
                        }
                    }
                });
    }


    /*
     * Function to open detail feedback dialog
     * */
    @SuppressLint("SetTextI18n")
    private void openDetailFeedback(int layout, String topic,String date,String id,
                                    String adminEmail,String userEmail,String userContent,
                                    String adminContent) {
        // Initialize new dialog
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set content for dialog
        dialog.setContentView(layout);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        // set the dialog to top
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        // Disable cancel by clicking randomly on the screen
//        dialog.setCancelable(false);


        TextView topicTextView  = dialog.findViewById(R.id.list_of_reply_feedback_topic_tv);
        TextView dateTextView  = dialog.findViewById(R.id.list_of_feedback_reply_date_tv);
        TextView idTextView  = dialog.findViewById(R.id.list_of_feedback_reply_id_tv);
        TextView adminEmailTextView  = dialog.findViewById(R.id.list_of_feedback_reply_admin_email_tv);
        TextView userEmailTextView  = dialog.findViewById(R.id.list_of_feedback_reply_user_email_tv);
        TextView userContentTextView  = dialog.findViewById(R.id.list_of_feedback_reply_message_from_user_tv);
        TextView adminContentTextView  = dialog.findViewById(R.id.list_of_feedback_reply_message_from_admin_tv);
        ImageView close = dialog.findViewById(R.id.feedback_reply_close_bt);


        topicTextView.setText(topic);
        dateTextView.setText(date);
        idTextView.setText("Feedback ID: " +id);
        adminEmailTextView.setText("From: "+adminEmail);
        adminContentTextView.setText("Response feedback: " + adminContent);
        userEmailTextView.setText("To: "+userEmail);
        userContentTextView.setText("Feedback: "+ userContent);

        close.setOnClickListener(View ->{
            dialog.dismiss();
        });

        dialog.show();
    }
}
