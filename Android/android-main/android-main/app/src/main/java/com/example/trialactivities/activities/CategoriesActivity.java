package com.example.trialactivities.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
import com.example.trialactivities.viewModel.CategoryViewModel;

public class CategoriesActivity extends AppCompatActivity {
    SwitchCompat switchMode;
    ImageView adminManager;
    ImageView homeButton;
    ImageView searchButton;
    ImageView logoutButton;
    boolean darkMode = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RecyclerView rvCategories;
    CategoriesAdapter categoriesAdapter;
    CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        // Initialize views
        homeButton = findViewById(R.id.home_button);
        switchMode = findViewById(R.id.switchMode);
        searchButton = findViewById(R.id.search_button);
        logoutButton = findViewById(R.id.logoutButton);
        rvCategories = findViewById(R.id.rvCategories);

        // set up adapters
        categoriesAdapter = new CategoriesAdapter(this);
        rvCategories.setAdapter(categoriesAdapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false));

        // set up buttons listeners
        buttonsListeners();
        // set switch theme mode
        switchTheme();
        // set up the observer for changes in the categories data
        categoryViewModel.getCategories().observe(this, categories -> {
            categoriesAdapter.setCategories(categories);
        });





    }

    private void buttonsListeners(){
        homeButton.setOnClickListener(v-> {
            Intent i = new Intent(CategoriesActivity.this, HomeAfterLoginActivity.class);
            startActivity(i);
        });

        searchButton.setOnClickListener(v-> {
            Intent i = new Intent(CategoriesActivity.this, SearchResultsActivity.class);
            startActivity(i);
        });
        logoutButton.setOnClickListener(v-> {
            Intent i = new Intent(CategoriesActivity.this, MainActivity.class);
            startActivity(i);
        });
    }

    protected void switchTheme(){
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
}
