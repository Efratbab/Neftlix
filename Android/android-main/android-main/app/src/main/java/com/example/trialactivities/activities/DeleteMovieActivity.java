package com.example.trialactivities.activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trialactivities.R;

public class DeleteMovieActivity extends AppCompatActivity {

    private EditText movieNameInput;
    private Button confirmButton;
    private ImageView homeButton;
    private ImageView logoutButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_movie);

        // Initialize UI elements
        movieNameInput = findViewById(R.id.movieNameInput);
        confirmButton = findViewById(R.id.confirmButton);
        homeButton = findViewById(R.id.homeButton);
        logoutButton = findViewById(R.id.logoutButton);

        //set up buttons listener
        buttonsListeners();

    }
    private void buttonsListeners(){
        homeButton.setOnClickListener(v ->{
            Intent i = new Intent(this, HomeAfterLoginActivity.class);
            startActivity(i);
        });

        logoutButton.setOnClickListener(v ->{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        // Handle Confirm button click
        confirmButton.setOnClickListener(v -> {
            String movieName = movieNameInput.getText().toString().trim();
            if (movieName.isEmpty()) {
                Toast.makeText(this, "Please enter a movie name.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Movie Deleted: " + movieName, Toast.LENGTH_SHORT).show();
                //TODO: Add code to delete the category from the database
            }
        });
    }
}
