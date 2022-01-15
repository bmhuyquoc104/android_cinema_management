package com.example.android_cinema_management.Model;

import java.util.HashMap;

public class ReplyFeedback {

    private String replyId;
    private String date;
    private String time;
    private String admin;
    private HashMap<String, String> feedback;

    public ReplyFeedback() {
    }

    public ReplyFeedback(String replyId, String date, String time, String admin, HashMap<String, String> feedback) {
        this.replyId = replyId;
        this.date = date;
        this.time = time;
        this.admin = admin;
        this.feedback = feedback;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public HashMap<String, String> getFeedback() {
        return feedback;
    }

    public void setFeedback(HashMap<String, String> feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "ReplyFeedback{" +
                "replyId='" + replyId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", admin='" + admin + '\'' +
                ", feedback=" + feedback +
                '}';
    }
}
