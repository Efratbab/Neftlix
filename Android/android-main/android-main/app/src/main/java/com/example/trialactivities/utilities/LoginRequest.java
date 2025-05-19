package com.example.trialactivities.utilities;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.TypeAdapter;
import java.io.IOException;

// ✅ Use a custom adapter to force field order
@JsonAdapter(LoginRequest.LoginRequestAdapter.class)
public class LoginRequest {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // ✅ Custom adapter to ensure "username" appears first in JSON
    public static class LoginRequestAdapter extends TypeAdapter<LoginRequest> {
        @Override
        public void write(JsonWriter out, LoginRequest value) throws IOException {
            out.beginObject();
            out.name("username").value(value.username); // ✅ Username first
            out.name("password").value(value.password);
            out.endObject();
        }

        @Override
        public LoginRequest read(JsonReader in) throws IOException {
            return null; // Not needed for this use case
        }
    }
}


