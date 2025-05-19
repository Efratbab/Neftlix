package com.example.trialactivities.activities;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.trialactivities.R;

import java.util.ArrayList;

public class AddMovieActivity extends AppCompatActivity {

    private ArrayList<String> selectedCategories = new ArrayList<>();
    private TextView selectedCategoriesPlaceholder;
    private ImageView homeButton;
    private ImageView logoutButton;
    private Button confirmButton;
    private EditText movieNameInput;
    private ListView categoryListView;
    private EditText moviePath;
    private EditText movieThumbnailPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        // Reference UI elements
        movieNameInput = findViewById(R.id.movieNameInput);
        categoryListView = findViewById(R.id.categoriesListView);
        selectedCategoriesPlaceholder = findViewById(R.id.selectedMoviesPlaceholder);
        confirmButton = findViewById(R.id.confirmButton);
        moviePath = findViewById(R.id.add_movie_path_input);
        movieThumbnailPath = findViewById(R.id.add_thumbnail_path_input);
        homeButton = findViewById(R.id.homeButton);
        logoutButton = findViewById(R.id.logoutButton);

        //set up buttons listeners
        buttonsListeners();
        //set up category list view
        handleCategoryListView();
    }
    private void buttonsListeners(){
        // handle home button click
        homeButton.setOnClickListener(v ->{
            Intent i = new Intent(this, HomeAfterLoginActivity.class);
            startActivity(i);
        });
        // handle logout button click
        logoutButton.setOnClickListener(v ->{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
        // Handle Confirm button click
        // Handle Confirm button click
        confirmButton.setOnClickListener(v -> {
            String movieName = movieNameInput.getText().toString().trim();

            if (movieName.isEmpty()) {
                Toast.makeText(this, "Please enter a movie name.", Toast.LENGTH_SHORT).show();
            } else if (selectedCategories.isEmpty()) {
                Toast.makeText(this, "Please select at least one category.", Toast.LENGTH_SHORT).show();
            }else if(moviePath.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter a movie path.", Toast.LENGTH_SHORT).show();
            }else if(movieThumbnailPath.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter a thumbnail path.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Movie Added:\nName: " + movieName + "\nMovies: " + String.join(", ", selectedCategories), Toast.LENGTH_LONG).show();
                //TODO: Add movie to database
            }
        });
    }

    private void handleCategoryListView(){
        // Sample movie data
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4"};

        // Set up ListView with movie data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, categories);
        categoryListView.setAdapter(adapter);
        categoryListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Handle ListView item clicks
        categoryListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCategory = categories[position];
            if (selectedCategories.contains(selectedCategory)) {
                selectedCategories.remove(selectedCategory);
            } else {
                selectedCategories.add(selectedCategory);
            }
            updateSelectedCategories();
        });
    }

    // Update the placeholder for selected movies
    private void updateSelectedCategories() {
        if (selectedCategories.isEmpty()) {
            selectedCategoriesPlaceholder.setText("No category selected");
        } else {
            selectedCategoriesPlaceholder.setText("Selected categories: " + String.join(", ", selectedCategories));
        }
    }

}