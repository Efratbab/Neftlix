package com.example.trialactivities.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trialactivities.NetflixApplication;
import com.example.trialactivities.api.CategoryAPI;
import com.example.trialactivities.database.CategoryDB;
import com.example.trialactivities.entities.Category;
import com.example.trialactivities.room.CategoryDao;

import java.util.LinkedList;
import java.util.List;

public class CategoryRepo {
    private CategoryDao categoryDao;
    private CategoryListData categoryListData;
    private CategoryAPI api;
    public CategoryRepo() {
        Log.d("CategoryRepo", "Initializing CategoryRepo");
        CategoryDB db = CategoryDB.getInstance(NetflixApplication.getAppContext());
        categoryDao = db.categoryDao();
        categoryListData = new CategoryListData();
        api= new CategoryAPI(categoryListData, categoryDao);
    }

    class CategoryListData extends MutableLiveData<List<Category>> {

        public CategoryListData() {
            super();
            setValue(new LinkedList<Category>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            Log.d("CategoryRepo", "Fetching data from LocalDB...");
            new Thread(() -> {
                categoryListData.postValue(categoryDao.index());
                //for logs
                List<Category> categories = categoryDao.index();
                Log.d("CategoryRepo", "Categories from DB: " + categories);
                //for logs
            }).start();
        }
    }
    public LiveData<List<Category>> getAll() {
        return categoryListData;
    }

    public void add(Category category, String token) {
        api.createCategory(category, token);
    }

    public void delete(String token, Category category) {
        api.deleteCategory(token, category.getId());
    }

    public void reload(String token) {
        api.getCategories(token);
    }

    /**
     * Get a specific category by ID.
     */
    public void getCategory(String token, int categoryID) {
        api.getCategory(token, categoryID);
    }

    /**
     * Update a category.
     */
    public void update(String token, Category category) {
        api.updateCategory(token, category);
    }
}
