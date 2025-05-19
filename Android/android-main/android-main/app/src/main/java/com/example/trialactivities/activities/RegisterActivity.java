package com.example.trialactivities.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.trialactivities.R;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    // Define the password pattern
    private static final String DIGIT_PATTERN = ".*[0-9].*";
    private static final String LOWERCASE_PATTERN = ".*[a-z].*";
    private static final String UPPERCASE_PATTERN = ".*[A-Z].*";
    private static final String SPECIAL_CHAR_PATTERN = ".*[@#$%^&+=].*";
    private static final String WHITESPACE_PATTERN = ".*\\s.*";

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 101;
    private ImageView profileImageView;
    private Button registerButton;
    private TextView username;
    private TextView password;
    private TextView confirmPassword;
    private TextView displayName;
    private Button btnUploadProfileImage;

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    profileImageView.setImageURI(result.getData().getData());
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize UI elements
        registerButton = findViewById(R.id.btnRegister);
        profileImageView = findViewById(R.id.profileImageView);
        btnUploadProfileImage = findViewById(R.id.btnUploadProfileImage);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        displayName = findViewById(R.id.editTextDisplay);

        //set up onclick listener for register button
        buttonsListeners();
    }

    private void buttonsListeners(){
        registerButton.setOnClickListener(v -> {
            if(username.getText().toString().isEmpty()
                    || password.getText().toString().isEmpty()
                    || confirmPassword.getText().toString().isEmpty()
                    || displayName.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter all fileds.", Toast.LENGTH_SHORT).show();
            }else if(isValid(password.getText().toString(), confirmPassword.getText().toString())){
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        });

        btnUploadProfileImage.setOnClickListener(v -> {
            if (checkStoragePermission()) {
                openGallery();
            } else {
                requestStoragePermission();
            }
        });
    }

    private boolean checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ requires READ_MEDIA_IMAGES
            return ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;
        } else {
            // For older Android versions
            return ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Request READ_MEDIA_IMAGES for Android 13+
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, STORAGE_PERMISSION_REQUEST_CODE);
        } else {
            // Request READ_EXTERNAL_STORAGE for older Android versions
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied to access storage", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public Boolean isValid(String password , String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "passwords Passwords do not match.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 8) {
            Toast.makeText(RegisterActivity.this, "Password must be at least 8 characters.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}