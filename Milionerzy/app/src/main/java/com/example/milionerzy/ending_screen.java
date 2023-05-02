package com.example.milionerzy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ending_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0); // pobranie przekazanej wartości 'score'

        TextView scoreTextView = findViewById(R.id.final_score);
        scoreTextView.setText("Twój wynik: " + score);
    }
}
