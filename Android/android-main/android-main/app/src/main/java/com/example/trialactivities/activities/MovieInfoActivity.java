package com.example.trialactivities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trialactivities.R;
import com.example.trialactivities.activities.CategoriesActivity;
import com.example.trialactivities.activities.MainActivity;


public class MovieInfoActivity extends AppCompatActivity {

    ImageView homeButton;
    Button categories;
    ImageView searchButton;
    SwitchCompat switchMode;
    boolean darkMode = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_movie);

        //initialize UI elements
        categories = findViewById(R.id.category_button);
        homeButton = findViewById(R.id.back_home);
        switchMode = findViewById(R.id.switchMode);
        searchButton = findViewById(R.id.search_button);

        // set up buttons listeners
        buttonsListeners();
        // Set up the switch mode
        switchTheme();

    }

    private void switchTheme(){
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

    private void buttonsListeners(){
        //set up the categories button click listener
        categories.setOnClickListener(v-> {
            Intent i = new Intent(MovieInfoActivity.this, CategoriesActivity.class);
            startActivity(i);
        });

        // Set up the home button click listener
        homeButton.setOnClickListener(v->{
            Intent i = new Intent(MovieInfoActivity.this, MainActivity.class);
            startActivity(i);
        });
        //set up the search button click listener
        searchButton.setOnClickListener(v-> {
            Intent i = new Intent(MovieInfoActivity.this, SearchResultsActivity.class);
            startActivity(i);
        });
    }
}
