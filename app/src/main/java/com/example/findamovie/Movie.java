package com.example.findamovie;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private String date;

    @SerializedName("original_language")
    private String language;

    @SerializedName("overview")
    private String description;

    @SerializedName("vote_average")
    private String vote;

    public Movie(String title, String date, String language, String description, String vote) {
        this.title = title;
        this.date = date;
        this.language = language;
        this.description = description;
        this.vote=vote;
    }

    public Movie() {

    }

    public String getTitle() {
        return title;
    }

    public String getVote() {
        return vote;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
