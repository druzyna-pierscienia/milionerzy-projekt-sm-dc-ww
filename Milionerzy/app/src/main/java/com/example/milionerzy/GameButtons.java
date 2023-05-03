package com.example.milionerzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameButtons extends AppCompatActivity implements View.OnClickListener {

    public Integer score = 0, questionLvl = 1;
    public String correctAnswer = "A";

    private TextView question;
    private Button answerA, answerB, answerC, answerD, hint5050, hintAudience, hintPhone, backToMenu;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.question);

        question = findViewById(R.id.question_text);
        answerA = findViewById(R.id.odpA);
        answerB = findViewById(R.id.odpB);
        answerC = findViewById(R.id.odpC);
        answerD = findViewById(R.id.odpD);
        hint5050 = findViewById(R.id.hint5050);
        hintAudience = findViewById(R.id.hintAudience);
        hintPhone = findViewById(R.id.hintPhone);
        backToMenu = findViewById(R.id.backToMenuButton);

        question.setText("PYTANIE ASD - test");
        answerA.setText("ODPOWIEDŹ A - test");
        answerB.setText("ODPOWIEDŹ B - test");
        answerC.setText("ODPOWIEDŹ C - test");
        answerD.setText("ODPOWIEDŹ D - test");

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        hint5050.setOnClickListener(this);
        hintAudience.setOnClickListener(this);
        hintPhone.setOnClickListener(this);
        backToMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.odpA:
                if (correctAnswer == "A") {
                    score = score + (questionLvl * 10);
                    questionLvl += 1;
                    //TODO: Wywołanie gry jeszcze raz z nowymi pytaniami
                } else {
                    Intent intent = new Intent(GameButtons.this, AboutApp.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
                break;
            case R.id.odpB:
                if (correctAnswer == "B") {
                    score = score + (questionLvl * 10);
                    questionLvl += 1;
                    //TODO: Wywołanie gry jeszcze raz z nowymi pytaniami
                } else {
                    Intent intent = new Intent(GameButtons.this, ending_screen.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
                break;
            case R.id.odpC:
                if (correctAnswer == "C") {
                    score = score + (questionLvl * 10);
                    questionLvl += 1;
                    //TODO: Wywołanie gry jeszcze raz z nowymi pytaniami
                } else {
                    Intent intent = new Intent(GameButtons.this, ending_screen.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
                break;
            case R.id.odpD:
                if (correctAnswer == "D") {
                    score = score + (questionLvl * 10);
                    questionLvl += 1;
                    //TODO: Wywołanie gry jeszcze raz z nowymi pytaniami
                } else {
                    Intent intent = new Intent(GameButtons.this, ending_screen.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
                break;
            case R.id.hint5050:
                // TODO: Koło ratunkowe - 50/50
                break;
            case R.id.hintAudience:
                // TODO: Koło ratunkowe - pytanie do publiczności
                break;
            case R.id.hintPhone:
                // TODO: Koło ratunkowe - pytanie do przyjaciela
                break;
            case R.id.backToMenuButton:
                Intent intent = new Intent(GameButtons.this, MainButtons.class);
                startActivity(intent);
                break;
        }
    }
}