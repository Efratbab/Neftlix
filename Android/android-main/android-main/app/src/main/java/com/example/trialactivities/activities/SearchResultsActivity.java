package com.example.trialactivities.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;

import com.example.trialactivities.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchResultsActivity extends AppCompatActivity {
    Button categoriesButton;
    ImageView logoutButton;
    ImageView homeButton;
    SearchView search;
    SwitchCompat switchMode;
    boolean darkMode = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // find the buttons view
        categoriesButton = findViewById(R.id.category_button);
        switchMode = findViewById(R.id.switchMode);
        search = findViewById(R.id.search_view);
        homeButton = findViewById(R.id.back_home);
        logoutButton = findViewById(R.id.logout);
        //set list
        listView = findViewById(R.id.list_view);

        // set up buttons listeners
        buttonsListeners();
        // set up switch mode
        switchMode();

        //set up some data
        ArrayList<String> originalMovies = new ArrayList<>(Arrays.asList(
                "Lion King", "ABBA", "Tom & Jerry", "Lion King 2", "Lion King 3"
        ));
        ArrayList<String> displayedMovies = new ArrayList<>(originalMovies);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayedMovies);
        listView.setAdapter(adapter);


        // Set up the SearchView
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                displayedMovies.clear();

                if (userInput.isEmpty()) {
                    displayedMovies.addAll(originalMovies);
                } else {
                    for (String movie : originalMovies) {
                        if (movie.toLowerCase().contains(userInput)) {
                            displayedMovies.add(movie);
                        }
                    }
                }

                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void buttonsListeners(){
        categoriesButton.setOnClickListener(v -> {
            Intent i = new Intent(SearchResultsActivity.this, CategoriesActivity.class);
            startActivity(i);
        });
        // set up the back button click listener
        homeButton.setOnClickListener(v -> {
            Intent i = new Intent(this, HomeAfterLoginActivity.class);
            startActivity(i);
        });
        // set up the logout button click listener
        logoutButton.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }

    private void switchMode(){
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
