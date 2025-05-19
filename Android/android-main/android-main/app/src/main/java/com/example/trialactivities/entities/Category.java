package com.example.trialactivities.entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.trialactivities.utilities.Converters;

import java.util.List;

@TypeConverters(Converters.class)
@Entity
public class Category {
    //Maybe we dont need the id because we dont generate it
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Boolean promoted;
    @TypeConverters(Converters.class)
    private List<Integer> movies;

    public Category(String name, Boolean promoted, List<Integer> movies) {
        this.name = name;
        this.promoted = promoted;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;  // Setter for Room auto-generation
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPromoted(Boolean promoted) {
        this.promoted = promoted;
    }

    public void setMovies(List<Integer> movies) {
        this.movies = movies;
    }

    public Boolean getPromoted() {
        return promoted;
    }

    public List<Integer> getMovies() {
        return movies;
    }
}