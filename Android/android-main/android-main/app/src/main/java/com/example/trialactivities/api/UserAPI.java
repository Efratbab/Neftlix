package com.example.trialactivities.api;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.trialactivities.entities.User;
import com.example.trialactivities.room.UserDao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    private MutableLiveData<List<User>> userListData;
    private UserDao userDao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    public Context context;

    public UserAPI(MutableLiveData<List<User>> userListData, UserDao userDao) {
        this.userListData = userListData;
        this.userDao = userDao;

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.32.114:3000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

//    public void getUser(int userId) {
//        new Thread(() -> {
//            Log.d("TEST", "Checking Room DB for user...");
//            User existingUser = userDao.get(userId);  // Check if user exists
//
//            if (existingUser != null) {
//                Log.d("TEST", "User already exists in Room: " + existingUser);
//                return;  // ✅ Stop here! No need to fetch from API.
//            }
//
//            Log.d("TEST", "User not found in Room, fetching from API...");
//            Call<User> call = webServiceAPI.getUser(userId);
//            call.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    if (response.isSuccessful() && response.body() != null) {
//                        Log.d("TEST", "hi");
//                        User apiUser = response.body();
//                        Log.d("TEST", "User fetched from API: " + apiUser);
//
//                        new Thread(() -> {
//                            if (userDao.get(apiUser.getId()) == null) {  // Check again before inserting
//                                userDao.insert(Collections.singletonList(apiUser));
//                                Log.d("TEST", "User inserted into Room: " + apiUser);
//                                userListData.postValue(userDao.index());  // Update LiveData
//                            } else {
//                                Log.d("TEST", "Skipping insert, user already in Room.");
//                            }
//                        }).start();
//                    } else {
//                        Log.e("TEST", "API call succeeded but user not found.");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Log.e("TEST", "Error fetching user from API", t);
//                }
//            });
//        }).start();
//    }


    public void signUpUser(User user, MultipartBody.Part profilePicture, String username,String password,String nameForDisplay) {
        RequestBody usernamePart = RequestBody.create(MediaType.parse("text/plain"), username);
        RequestBody passwordPart = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody nameForDisplayPart = RequestBody.create(MediaType.parse("text/plain"), nameForDisplay);

        Call<Void> call = webServiceAPI.signUp(profilePicture,usernamePart, passwordPart, nameForDisplayPart);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                new Thread(() -> {
                    List<User> users = new ArrayList<>();
                    users.add(user);
                    userDao.insert(users);
                    userListData.postValue(userDao.index());  // Updates LiveData with new data
                }).start();
                Log.d("UserAPI", "✅ Response Code: " + response.code());
                if (response.isSuccessful()) {
                    Log.d("UserAPI", "🎉 Sign-up successful: " + user);
                } else {
                    Log.e("UserAPI", "❌ Sign-up failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UserAPI", "⚠️ API call failed", t);
            }
        });
    }

//    public void updateUser(int userID, User user){
//        Call<Void> call = webServiceAPI.updateUser(userID, user.getProfileImage(),user.getUsername(), user.getPassword(),user.getNameForDisplay());
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                //Update on local db
//                new Thread(() -> {
//                    userDao.update(user);
//                    userListData.postValue(userDao.index()); // UI updates immediately
//                }).start();
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//            }
//        });
//    }
}
