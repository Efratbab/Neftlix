package com.example.trialactivities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trialactivities.NetflixApplication;
import com.example.trialactivities.R;

public class MainActivity extends AppCompatActivity {

    private Button button_signIn;
    private Button button_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize ui elements
        button_signIn = findViewById(R.id.button_signIn);
        button_signUp = findViewById(R.id.button_signUp);

        // set up click listeners
        buttonsListeners();

        // Navigate to HomeAfterLoginActivity from textview TODO: remove later
        TextView textView = findViewById(R.id.title);
        textView.setOnClickListener(v -> {
            Intent i = new Intent(this, HomeAfterLoginActivity.class);
            startActivity(i);
        });

    }
    private void buttonsListeners(){
        button_signIn.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        });
        button_signUp.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });
    }
}
