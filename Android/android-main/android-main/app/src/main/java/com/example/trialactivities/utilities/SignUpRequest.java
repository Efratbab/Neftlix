package com.example.trialactivities.utilities;

public class SignUpRequest {
    private String username;
    private String password;
    private String nameForDisplay;

    public SignUpRequest(String username, String password, String nameForDisplay) {
        this.username = username;
        this.password = password;
        this.nameForDisplay = nameForDisplay;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNameForDisplay(String nameForDisplay) {
        this.nameForDisplay = nameForDisplay;
    }

    public String getPassword() {
        return password;
    }

    public String getNameForDisplay() {
        return nameForDisplay;
    }
}
