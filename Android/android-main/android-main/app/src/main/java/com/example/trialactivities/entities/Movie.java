package com.example.trialactivities.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.trialactivities.utilities.Converters;

import java.util.List;

@TypeConverters(Converters.class)
@Entity (tableName = "movie")
public class Movie {
    @PrimaryKey
    private int id;
    private String title;

    @TypeConverters(Converters.class)
    private String[] categories;

    public Movie(String title, String[] categories) {
        this.title = title;
        this.categories = categories;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
