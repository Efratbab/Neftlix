package com.example.trialactivities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.trialactivities.R;

public class MainAdminActivity extends AppCompatActivity {
    private boolean darkMode = false;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SwitchCompat switchMode;
    private Button addCategoryButton;
    private Button addMovieButton;
    private Button editCategoryButton;
    private Button editMovieButton;
    private Button deleteCategoryButton;
    private Button deleteMovieButton;
    private Button categoriesButton;
    private ImageView searchButton;
    private ImageView logoutButton;
    private ImageView homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        // initialize UI elements
        homeButton = findViewById(R.id.back_home);
        logoutButton = findViewById(R.id.logout);
        categoriesButton = findViewById(R.id.category_button);
        searchButton = findViewById(R.id.search_button);
        switchMode = findViewById(R.id.switchMode);
        addCategoryButton = findViewById(R.id.add_category_button);
        addMovieButton = findViewById(R.id.add_movie_button);
        editCategoryButton = findViewById(R.id.edit_category_button);
        editMovieButton = findViewById(R.id.edit_movie_button);
        deleteCategoryButton = findViewById(R.id.delete_category_button);
        deleteMovieButton = findViewById(R.id.delete_movie_button);


        // set up buttons listeners
        buttonsListeners();
        // Set up the switch mode
        switchTheme();
    }

    private void buttonsListeners(){
        categoriesButton.setOnClickListener(v -> {
            Intent i = new Intent(this, CategoriesActivity.class);
            startActivity(i);
        });
        searchButton.setOnClickListener(v -> {
            Intent i = new Intent(this, SearchResultsActivity.class);
            startActivity(i);
        });
        logoutButton.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
        homeButton.setOnClickListener(v -> {
            Intent i = new Intent(this, HomeAfterLoginActivity.class);
            startActivity(i);
        });

        addCategoryButton.setOnClickListener(v -> {
            Intent i = new Intent(this, AddCategoryActivity.class);
            startActivity(i);
        });
        addMovieButton.setOnClickListener(v -> {
            Intent i = new Intent(this, AddMovieActivity.class);
            startActivity(i);
        });
        editCategoryButton.setOnClickListener(v -> {
            Intent i = new Intent(this, EditCategoryActivity.class);
            startActivity(i);
        });
        editMovieButton.setOnClickListener(v -> {
            Intent i = new Intent(this, EditMovieActivity.class);
            startActivity(i);
        });
        deleteCategoryButton.setOnClickListener(v -> {
            Intent i = new Intent(this, DeleteCategoryActivity.class);
            startActivity(i);
        });
        deleteMovieButton.setOnClickListener(v -> {
            Intent i = new Intent(this, DeleteCategoryActivity.class);
            startActivity(i);
        });

    }

    private void switchTheme(){
        sharedPreferences = getSharedPreferences("MODE", MODE_PRIVATE);
        darkMode = sharedPreferences.getBoolean("darkMode", false);
        if (darkMode) {
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (darkMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("darkMode", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("darkMode", true);
                }
                editor.apply();
            }
        });
    }
}