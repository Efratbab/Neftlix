package com.example.trialactivities.entities;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.trialactivities.utilities.Converters;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;

@Entity(tableName = "user")
@TypeConverters(Converters.class)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;

    private String password;

    private String nameForDisplay;
    @TypeConverters(Converters.class)
    private String profileImage;
    @TypeConverters(Converters.class)
    private Map<String, Integer> roles;
    @TypeConverters(Converters.class) // Use the converter for int[]
    private int[] watchedMovies;
    public User(String username, String password, String nameForDisplay, String profileImage, int[] watchedMovies) {
        this.username = username;
        this.password = password;
        this.nameForDisplay = nameForDisplay;
        this.profileImage = profileImage;
        this.roles = (roles != null) ? roles : new HashMap<>(); // Use empty map if null
        this.watchedMovies = (watchedMovies != null) ? watchedMovies : new int[0]; // Use empty array if null
    }

    // Getters
    public String getUsername() { return username; }

    public void setWatchedMovies(int[] watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public int[] getWatchedMovies() {
        return watchedMovies;
    }

    public String getPassword() { return password; }
    public String getNameForDisplay() { return nameForDisplay; }

    public Map<String, Integer> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Integer> roles) {
        this.roles = roles;
    }

    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setNameForDisplay(String nameForDisplay) { this.nameForDisplay = nameForDisplay; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

}
