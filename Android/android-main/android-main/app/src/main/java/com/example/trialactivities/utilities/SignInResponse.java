package com.example.trialactivities.utilities;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SignInResponse {
    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("user")
    private ApiUser user;

    @SerializedName("createdAt") // ✅ Match MongoDB field
    private Date createdAt;

    public String getAccessToken() {
        return accessToken;
    }

    public ApiUser getUser() {
        return user;
    }
    public Date getCreatedAt() { // ✅ Returns Java Date object
        return createdAt;
    }

}
