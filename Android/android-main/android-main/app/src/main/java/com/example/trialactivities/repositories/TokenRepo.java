package com.example.trialactivities.repositories;
import androidx.lifecycle.LiveData;
import com.example.trialactivities.utilities.SignInResponse;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.trialactivities.api.WebServiceAPI;
import com.example.trialactivities.utilities.LoginRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenRepo {
    private final WebServiceAPI webServiceAPI;
    private final MutableLiveData<String> tokenLiveData = new MutableLiveData<>();

    public TokenRepo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.32.114:3000/api/") // ✅ Replace with your actual API URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    // ✅ Fetch a new token from the server (No local storage)
    public void fetchToken(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);

        Call<SignInResponse> call = webServiceAPI.signIn(loginRequest);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getAccessToken();
                    tokenLiveData.postValue(token); // ✅ Notify observers
                    Log.d("TokenRepository", "✅ New token received: " + token);
                } else {
                    Log.e("TokenRepository", "❌ Token request failed: " + response.code());
                    tokenLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.e("TokenRepository", "⚠️ API call failed", t);
                tokenLiveData.postValue(null);
            }
        });
    }

}
