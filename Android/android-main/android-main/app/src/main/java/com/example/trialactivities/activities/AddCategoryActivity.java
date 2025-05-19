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

import androidx.appcompat.app.AppCompatActivity;

import com.example.trialactivities.R;

import java.util.ArrayList;

public class AddCategoryActivity extends AppCompatActivity {

    private ArrayList<String> selectedMovies = new ArrayList<>();
    private TextView selectedMoviesPlaceholder;
    private RadioGroup promotedRadioGroup;
    private ImageView homeButton;
    private ImageView logoutButton;
    private Button confirmButton;
    private ListView movieListView;
    private EditText categoryNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        // Reference UI elements
        categoryNameInput = findViewById(R.id.categoryNameInput);
        movieListView = findViewById(R.id.movieListView);
        selectedMoviesPlaceholder = findViewById(R.id.selectedMoviesPlaceholder);
        promotedRadioGroup = findViewById(R.id.promotedRadioGroup);
        confirmButton = findViewById(R.id.confirmButton);
        homeButton = findViewById(R.id.homeButton);
        logoutButton = findViewById(R.id.logoutButton);

        //set up buttons listeners
        buttonsListeners();
        // set up movie list view
        handleMovieListView();
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
        confirmButton.setOnClickListener(v -> {
            String categoryName = categoryNameInput.getText().toString().trim();
            int selectedPromotedId = promotedRadioGroup.getCheckedRadioButtonId();

            String promoted = "No";
            if (selectedPromotedId != -1) {
                RadioButton selectedRadio = findViewById(selectedPromotedId);
                promoted = selectedRadio.getText().toString();
            }

            if (categoryName.isEmpty()) {
                Toast.makeText(this, "Please enter a category name.", Toast.LENGTH_SHORT).show();
            } else if (selectedMovies.isEmpty()) {
                Toast.makeText(this, "Please select at least one movie.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Category Added:\nName: " + categoryName + "\nPromoted: " + promoted + "\nMovies: " + String.join(", ", selectedMovies), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleMovieListView(){
        // Sample movie data
        String[] movies = {"Movie 1", "Movie 2", "Movie 3", "Movie 4"};

        // Set up ListView with movie data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, movies);
        movieListView.setAdapter(adapter);
        movieListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Handle ListView item clicks
        movieListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMovie = movies[position];
            if (selectedMovies.contains(selectedMovie)) {
                selectedMovies.remove(selectedMovie);
            } else {
                selectedMovies.add(selectedMovie);
            }
            updateSelectedMovies();
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
}