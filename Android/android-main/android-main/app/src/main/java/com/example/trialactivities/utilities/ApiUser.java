package com.example.trialactivities.utilities;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ApiUser {
    @SerializedName("nameForDisplay")
    private String nameForDisplay;

    @SerializedName("roles")
    private Map<String, Integer> roles;

    public String getNameForDisplay() {
        return nameForDisplay;
    }

    public Map<String, Integer> getRoles() {
        return roles;
    }
}

