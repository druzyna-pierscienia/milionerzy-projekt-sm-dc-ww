package com.example.milionerzy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class ending_screen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0); // pobranie przekazanej wartości 'score'
        int questionLvl = intent.getIntExtra("questionLvl", 0); // pobranie przekazanej wartości 'score'
        String question = intent.getStringExtra("question"); // pobranie przekazanego tekstu pytania
        String correctAnswer = intent.getStringExtra("correctAnswer"); // pobranie przekazanej poprawnej odpowiedzi

        MediaPlayer winSound;
        MediaPlayer loseSound;

        TextView scoreTextView = findViewById(R.id.final_score);
        TextView questionTV = findViewById(R.id.question);
        TextView correctAnswerTV = findViewById(R.id.correct_answer);

        winSound = MediaPlayer.create(this, R.raw.win);
        loseSound = MediaPlayer.create(this, R.raw.lose);

        if(questionLvl < 10) {
            loseSound.start();
            scoreTextView.setText("Twój wynik: " + score);
            questionTV.setText("Pytanie: " + question);
            correctAnswerTV.setText("Poprawna odpowiedź: " + correctAnswer);
        }
        else {
            winSound.start();
            scoreTextView.setText("Twój końcowy wynik to: " + score);
            questionTV.setText(" ");
            correctAnswerTV.setText(" ");
        }
        //Wyślij do bazy danych wynik
        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "domyślna_wartość");

        ApiRequestInto task = new ApiRequestInto();
        try {
            String result = task.execute("http://10.0.2.2:8080/saveScore?login="+username+"&score="+Integer.toString(score)).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
//String username = sharedPreferences.getString("username", "domyślna_wartość");