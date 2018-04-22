package com.example.aleksejkocergin.sportnews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SportNewsData {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("coefficient")
    @Expose
    private String coefficient;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("preview")
    @Expose
    private String preview;
    @SerializedName("article")
    @Expose
    private String article;
    @SerializedName("events")
    @Expose
    private List<SportNewsData> events = new ArrayList<>();

    public List<SportNewsData> getEvents() {
        return events;
    }

    public void setEvents(List<SportNewsData> events) {
        this.events = events;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
