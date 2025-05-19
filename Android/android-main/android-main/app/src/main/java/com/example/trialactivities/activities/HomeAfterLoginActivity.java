package com.example.trialactivities.activities;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trialactivities.R;
import com.example.trialactivities.adapters.CategoriesAdapter;
import com.example.trialactivities.adapters.MoviesAdapter;
import com.example.trialactivities.entities.Category;
import com.example.trialactivities.entities.Movie;
import com.example.trialactivities.viewModel.CategoryViewModel;


import java.util.ArrayList;
import java.util.List;

public class HomeAfterLoginActivity extends AppCompatActivity {
    Button categoriesButton;
    SwitchCompat switchMode;
    ImageView adminManager;
    ImageView searchButton;
    ImageView logoutButton;
    boolean darkMode = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<Category> categories = new ArrayList<>();
    List<Movie> movies = new ArrayList<>();
    RecyclerView rvCategories;
    CategoriesAdapter categoriesAdapter;
    CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_after_login);

        //initialize the viewsModel
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        // Initialize views
        adminManager = findViewById(R.id.admin_manager);
        categoriesButton = findViewById(R.id.category_button);
        switchMode = findViewById(R.id.switchMode);
        searchButton = findViewById(R.id.search_button);
        logoutButton = findViewById(R.id.logout);
        rvCategories= findViewById(R.id.rvCategories);
        // set up adapters
        categoriesAdapter = new CategoriesAdapter(this);
        rvCategories.setAdapter(categoriesAdapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        // set up observer for changes in the categories data
        categoryViewModel.getCategories().observe(this, categories -> {
            categoriesAdapter.setCategories(categories);
        });
        // set up listeners
        buttonsListeners();
        // set up switch mode
        switchTheme();
    }
    protected void switchTheme(){
        // Set up the switch mode click listener
        sharedPreferences = getSharedPreferences("MODE", MODE_PRIVATE);
        darkMode = sharedPreferences.getBoolean("darkMode", false);
        if(darkMode){
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(darkMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("darkMode", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("darkMode", true);
                }
                editor.apply();
            }
        });
    }
    private void displayMoviesCards(){

        rvCategories.setHasFixedSize(true);
        MoviesAdapter moviesAdapter = new MoviesAdapter(this);
        // set up some data to check TODO: remove later

        movies.add(new Movie("Movie 1", new String[]{"Category 1", "Category 2"} , "thumbnail1"));
        movies.add(new Movie("Movie 2", new String[]{"Category 2", "Category 3"}, "thumbnail2"));

        categoriesAdapter.setCategories(categories);
        moviesAdapter.setMovies(movies);
    }
    private void buttonsListeners(){
        // set up category button listener
        categoriesButton.setOnClickListener(v-> {
            Intent i = new Intent(HomeAfterLoginActivity.this, CategoriesActivity.class);
            startActivity(i);
        });
        searchButton.setOnClickListener(v-> {
            Intent i = new Intent(HomeAfterLoginActivity.this, SearchResultsActivity.class);
            startActivity(i);
        });
        adminManager.setOnClickListener(v-> {
            Intent i = new Intent(HomeAfterLoginActivity.this, MainAdminActivity.class);
            startActivity(i);
        });
        logoutButton.setOnClickListener(v-> {
            Intent i = new Intent(HomeAfterLoginActivity.this, MainActivity.class);
            startActivity(i);
        });
    }
}
