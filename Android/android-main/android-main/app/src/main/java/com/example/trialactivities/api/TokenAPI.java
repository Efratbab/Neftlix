package com.example.trialactivities.api;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.example.trialactivities.utilities.LoginRequest;

import com.example.trialactivities.entities.User;
import com.example.trialactivities.room.UserDao;
import com.example.trialactivities.utilities.SignInResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAPI {
    private MutableLiveData<SignInResponse> tokenListData;
    //private UserDao userDao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    public Context context;

    public TokenAPI(/*MutableLiveData<SignInResponse> tokenListData, UserDao userDao*/) {
        this.tokenListData = tokenListData;
        //this.userDao = userDao;

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.32.114:3000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void signInUser(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(loginRequest);
        Log.d("SignInDebug", "📤 Sending JSON to Server: " + jsonRequest); // ✅ Debug

        Call<SignInResponse> call = webServiceAPI.signIn(loginRequest);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                Log.d("SignInDebug", "🚀 API Response Received");

                if (response.isSuccessful() && response.body() != null) {
                    SignInResponse signInInfo = response.body();
                    Log.d("LoginSuccess", "✅ Token: " + signInInfo.getAccessToken());
                } else {
                    Log.e("LoginError", "❌ Login failed - HTTP Status: " + response.code());

                    try {
                        String errorResponse = response.errorBody().string();
                        Log.e("LoginError", "❌ Server Response Body: " + errorResponse);
                    } catch (IOException e) {
                        Log.e("LoginError", "❌ Failed to read error body", e);
                    }
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.e("UserAPI", "⚠️ API call failed", t);
            }
        });
    }

}
