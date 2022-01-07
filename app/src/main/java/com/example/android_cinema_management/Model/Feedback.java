package com.example.android_cinema_management.Model;

import java.util.HashMap;

public class Feedback {

    private String id;
    private String topic;
    private String feedbackContent;
    private String date;
    private HashMap<String, Object> user;

    public Feedback() {
    }

    public Feedback(String id, String topic, String feedbackContent, String date, HashMap<String, Object> user) {
        this.id = id;
        this.topic = topic;
        this.feedbackContent = feedbackContent;
        this.date = date;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, Object> getUser() {
        return user;
    }

    public void setUser(HashMap<String, Object> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", date='" + date + '\'' +
                ", user=" + user +
                '}';
    }
}
