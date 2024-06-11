package com.example.dreamstory.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity (tableName = "story")
public class Story implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String Title;
    private String originLabel;
    private String date;
    private String textStory;
    private String language;
    private String authorId;
    private String location;
    private String favStatus;
    private String storyStatus;

    public Story(){}

    public Story(String title, String originLabel, String date, String textStory,
                 String language, String authorId, String location, String favStatus,
                 String storyStatus) {
        Title = title;
        this.originLabel = originLabel;
        this.date = date;
        this.textStory = textStory;
        this.language = language;
        this.authorId = authorId;
        this.location = location;
        this.favStatus = favStatus;
        this.storyStatus = storyStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOriginLabel() {
        return originLabel;
    }

    public void setOriginLabel(String originLabel) {
        this.originLabel = originLabel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextStory() {
        return textStory;
    }

    public void setTextStory(String textStory) {
        this.textStory = textStory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public String getStoryStatus() {
        return storyStatus;
    }

    public void setStoryStatus(String storyStatus) {
        this.storyStatus = storyStatus;
    }
}
