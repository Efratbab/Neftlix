package com.example.trialactivities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import com.example.trialactivities.R;

public class EditCategoryActivity extends AppCompatActivity {
    private ImageView homeButton;
    private ImageView logoutButton;
    private ListView categoriesListView;
    private EditText categoryNameInput;
    private ListView moviesListView;
    private RadioGroup promotedRadioGroup;
    private ArrayList<String> selectedMovies = new ArrayList<>();
    private ArrayList<String> selectedCategories = new ArrayList<>();
    private Button confirmButton;
    private TextView selectedMoviesPlaceholder;
    private TextView selectedCategoryPlaceholder;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        // initialize UI elements
        homeButton = findViewById(R.id.homeButton);
        logoutButton = findViewById(R.id.logoutButton);
        categoriesListView = findViewById(R.id.categoryListView);
        categoryNameInput = findViewById(R.id.categoryNameInput);
        moviesListView = findViewById(R.id.moviesListView);
        promotedRadioGroup = findViewById(R.id.promotedRadioGroup);
        confirmButton = findViewById(R.id.confirmButton);
        selectedMoviesPlaceholder = findViewById(R.id.selectedMoviesPlaceholder);
        selectedCategoryPlaceholder = findViewById(R.id.selectedCategoryPlaceholder);

        //set up click buttons listeners
        buttonsListeners();

        // Sample movie data
        String[] movies = {"Movie 1", "Movie 2", "Movie 3", "Movie 4"};
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4"};

        // Set up MoviesListView with movie data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, movies);
        moviesListView.setAdapter(adapter);
        moviesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Set up CategoriesListView with movie data
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, categories);
        categoriesListView.setAdapter(adapter2);
        categoriesListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Handle MoviesListView item clicks
        moviesListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMovie = movies[position];
            if (selectedMovies.contains(selectedMovie)) {
                selectedMovies.remove(selectedMovie);
            } else {
                selectedMovies.add(selectedMovie);
            }
            updateSelectedMovies();
        });

        // Handle CategoriesListView item clicks
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
            selectedCategoryPlaceholder.setText("No category selected");
        } else {
            selectedCategoryPlaceholder.setText("Selected categories: " + String.join(", ", selectedCategories));

        }
    }
    private void buttonsListeners(){
        // handle home button click
        homeButton.setOnClickListener(v -> {
            Intent i = new Intent(this,HomeAfterLoginActivity.class);
            startActivity(i);
        });
        // handle logout button click
        logoutButton.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
        // Handle Confirm button click
        confirmButton.setOnClickListener(v -> {
            String categoryName = categoryNameInput.getText().toString().trim();
            int selectedPromotedId = promotedRadioGroup.getCheckedRadioButtonId();

            String promoted = "No";
            if (selectedPromotedId != -1) {
                RadioButton selectedRadio = findViewById(selectedPromotedId);
                promoted = selectedRadio.getText().toString();
            }
            // check for valid edit request
            if (selectedCategories.isEmpty()) {
                Toast.makeText(this, "Please enter a category name.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (categoryName.isEmpty() && selectedMovies.isEmpty() && selectedPromotedId == -1) {
                Toast.makeText(this, "You don't made any changes.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Category Added:\nName: " + categoryName + "\nPromoted: " + promoted + "\nMovies: " + String.join(", ", selectedMovies), Toast.LENGTH_LONG).show();
                //TODO: update category in database
            }
        });
    }
}
