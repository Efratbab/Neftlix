package com.example.trialactivities.activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import com.example.trialactivities.R;

public class EditMovieActivity extends AppCompatActivity {

    private ImageView homeButton;
    private ImageView logoutButton;
    private ListView categoriesListView;
    private EditText movieNameInput;
    private ListView moviesListView;
    private ArrayList<String> selectedMovies = new ArrayList<>();
    private ArrayList<String> selectedCategories = new ArrayList<>();
    private Button confirmButton;
    private TextView selectedMoviesPlaceholder;
    private TextView selectedCategoriesPlaceholder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        // initialize UI elements
        homeButton = findViewById(R.id.homeButton);
        logoutButton = findViewById(R.id.logoutButton);
        categoriesListView = findViewById(R.id.categoriesListView);
        movieNameInput = findViewById(R.id.movieNameInput);
        moviesListView = findViewById(R.id.movieListView);
        confirmButton = findViewById(R.id.confirmButton);
        selectedMoviesPlaceholder = findViewById(R.id.selectedMoviePlaceholder);
        selectedCategoriesPlaceholder = findViewById(R.id.selectedCategoriesPlaceholder);

        //set up click listeners for navbar buttons
        buttonsListeners();

        // Sample movie data
        String[] movies = {"Movie 1", "Movie 2", "Movie 3", "Movie 4"};
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4"};

        // Set up MoviesListView with movie data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, movies);
        moviesListView.setAdapter(adapter);
        moviesListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set up CategoriesListView with movie data
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, categories);
        categoriesListView.setAdapter(adapter2);
        categoriesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Handle MoviesListView item clicks
        handleMovieListView(movies);

        // Handle CategoriesListView item clicks
        handleCategoryListView(categories);

    }

    // Update the placeholder for selected movies
    private void updateSelectedMovies() {
        if (selectedMovies.isEmpty()) {
            selectedMoviesPlaceholder.setText("No movies selected");
        } else {
            selectedMoviesPlaceholder.setText("Selected movies: " + String.join(", ", selectedMovies));
        }
    }
    // Update the placeholder for selected movies
    private void updateSelectedCategories() {
        if (selectedCategories.isEmpty()) {
            selectedCategoriesPlaceholder.setText("No category selected");
        } else {
            selectedCategoriesPlaceholder.setText("Selected categories: " + String.join(", ", selectedCategories));

        }
    }
    private void buttonsListeners(){
        homeButton.setOnClickListener(v -> {
            Intent i = new Intent(this,HomeAfterLoginActivity.class);
            startActivity(i);
        });
        logoutButton.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
        // Handle Confirm button click
        confirmButton.setOnClickListener(v -> {
            String categoryName = movieNameInput.getText().toString().trim();
            // check for valid edit request
            if (selectedMovies.isEmpty()) {
                Toast.makeText(this, "Please enter a movie name.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (categoryName.isEmpty() && selectedCategories.isEmpty()) {
                Toast.makeText(this, "You don't made any changes.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Movie Updated:\nName: " + categoryName + "\nMovies: " + String.join(", ", selectedMovies), Toast.LENGTH_LONG).show();
                //TODO: update movie in database
            }
        });
    }

    private void handleCategoryListView(String[] categories){
        categoriesListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCategory = categories[position];
            if (selectedCategories.contains(selectedCategory)) {
                selectedCategories.remove(selectedCategory);
            } else {
                selectedCategories.add(selectedCategory);
            }
            updateSelectedCategories();
        });
    }
    private  void handleMovieListView(String[] movies){
        moviesListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMovie = movies[position];
            if (selectedMovies.contains(selectedMovie)) {
                selectedMovies.remove(selectedMovie);
            } else {
                selectedMovies.add(selectedMovie);
            }
            updateSelectedMovies();
        });
    }
}
