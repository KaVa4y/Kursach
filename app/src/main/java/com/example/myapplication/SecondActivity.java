package com.kursach.app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();
        displayWelcomeMessage();
    }

    private void initViews() {
        textViewWelcome = findViewById(R.id.textViewWelcome);
    }

    private void displayWelcomeMessage() {
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            textViewWelcome.setText("Добро пожаловать, " + username + "!");
        } else {
            textViewWelcome.setText("Добро пожаловать!");
        }
    }
}