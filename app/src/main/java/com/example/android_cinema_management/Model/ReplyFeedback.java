package com.example.android_cinema_management.Model;

import java.util.HashMap;

public class ReplyFeedback {

    private String replyId;
    private String date;
    private String time;
    private String adminEmail;
    private String feedbackContent;
    private String replyFeedbackContent;
    private String userName;
    private String topic;
    private String userEmail;
    private String adminImage;


    public ReplyFeedback() {
    }

    public ReplyFeedback(String replyId, String date, String time, String adminEmail, String feedbackContent, String replyFeedbackContent, String userName, String topic, String userEmail, String adminImage) {
        this.replyId = replyId;
        this.date = date;
        this.time = time;
        this.adminEmail = adminEmail;
        this.feedbackContent = feedbackContent;
        this.replyFeedbackContent = replyFeedbackContent;
        this.userName = userName;
        this.topic = topic;
        this.userEmail = userEmail;
        this.adminImage = adminImage;
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

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getReplyFeedbackContent() {
        return replyFeedbackContent;
    }

    public void setReplyFeedbackContent(String replyFeedbackContent) {
        this.replyFeedbackContent = replyFeedbackContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAdminImage() {
        return adminImage;
    }

    public void setAdminImage(String adminImage) {
        this.adminImage = adminImage;
    }

    @Override
    public String toString() {
        return "ReplyFeedback{" +
                "replyId='" + replyId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", replyFeedbackContent='" + replyFeedbackContent + '\'' +
                ", userName='" + userName + '\'' +
                ", topic='" + topic + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", adminImage='" + adminImage + '\'' +
                '}';
    }
}
