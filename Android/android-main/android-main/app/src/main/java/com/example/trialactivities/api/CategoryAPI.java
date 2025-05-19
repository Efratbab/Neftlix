package com.example.trialactivities.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.trialactivities.NetflixApplication;
import com.example.trialactivities.R;
import com.example.trialactivities.room.CategoryDao;
import com.example.trialactivities.entities.Category;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryAPI {
    private MutableLiveData<List<Category>> categoryListData;
    private CategoryDao categoryDao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    public CategoryAPI(MutableLiveData<List<Category>> categoryListData, CategoryDao categoryDao) {
        this.categoryListData = categoryListData;
        this.categoryDao = categoryDao;

        // Added OkHttpClient with timeout settings
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.21.176.1:3000/api/")  // Use your PC's IP instead of 10.0.2.2
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void getCategories(String token) {
        Call<List<Category>> call = webServiceAPI.getCategories(token);
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> category = response.body();
                    new Thread(() -> {
                        categoryDao.clear();  // Clears old data
                        categoryDao.insert(response.body());  // Inserts new categories into the database
                        categoryListData.postValue(categoryDao.index());  // Updates LiveData with new data
                    }).start();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("CategoryAPI", "API call failed", t);
            }
        });
    }

    public void createCategory(Category category, String token){
        Call<Void> call = webServiceAPI.createCategory(token, category);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                new Thread(() -> {
                    categoryDao.insert((List<Category>) category);  // Inserts the new category without clearing existing data
                    categoryListData.postValue(categoryDao.index());  // Updates LiveData with new data
                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public void getCategory(String token, int categoryID){
        //how about the movie under the category
        Call<Category> call = webServiceAPI.getCategory(token, categoryID);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Category category = response.body();
                    new Thread(() -> {
                        categoryDao.insert((List<Category>) category);  // Save the category in the local DB
                        categoryListData.postValue(categoryDao.index());  //Update LiveData
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
            }
        });
    }

    public void deleteCategory(String token, int categoryID){
        //Delete from server
        Call<Void> call = webServiceAPI.deleteCategory(token, categoryID);
        call.enqueue(new Callback<Void>() {
             @Override
             public void onResponse(Call<Void> call, Response<Void> response) {
                 //Delete from
                 new Thread(() -> {
                     categoryDao.deleteById(categoryID);
                     categoryListData.postValue(categoryDao.index()); // UI updates immediately
                 }).start();
             }

             @Override
             public void onFailure(Call<Void> call, Throwable t) {}
        });
    }

    public void updateCategory(String token, Category category){
        //Update on server
        Call<Void> call = webServiceAPI.updateCategory(token, category.getId(), category);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Update on categoryDB
                new Thread(() -> {
                    categoryDao.update(category);
                    categoryListData.postValue(categoryDao.index()); // UI updates immediately
                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });

    }
}
