package com.example.milionerzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ending_screen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0); // pobranie przekazanej wartości 'score'

        TextView scoreTextView = findViewById(R.id.final_score);
        scoreTextView.setText("Twój wynik: " + score);

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ending_screen.this,MainButtons.class);
                startActivity(intent);
            }
        });
    }
}
