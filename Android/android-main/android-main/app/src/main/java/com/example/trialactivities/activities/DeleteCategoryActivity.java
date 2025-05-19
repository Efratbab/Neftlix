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

public class DeleteCategoryActivity extends AppCompatActivity {

    private EditText categoryNameInput;
    private Button confirmButton;
    private ImageView homeButton;
    private ImageView logoutButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        // Initialize UI elements
        categoryNameInput = findViewById(R.id.categoryNameInput);
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
            String categoryName = categoryNameInput.getText().toString().trim();
            if (categoryName.isEmpty()) {
                Toast.makeText(this, "Please enter a category name.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Category Deleted: " + categoryName, Toast.LENGTH_SHORT).show();
                //TODO: Add code to delete the category from the database
            }
        });
    }
}
