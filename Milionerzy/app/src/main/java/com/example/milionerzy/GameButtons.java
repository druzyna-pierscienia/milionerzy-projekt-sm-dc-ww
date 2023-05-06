package com.example.milionerzy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameButtons extends AppCompatActivity implements View.OnClickListener {

    public Integer score = 0, questionLvl = 1;
    //TODO: Cała poniższa linijka do zmiany na pobieranie z bazy
    public String correctAnswer = "A", questionFromDataBase = "Pytanie z bazy pytań", answerAFromDataBase = "Odpowiedź A", answerBFromDataBase = "Odpowiedź B", answerCFromDataBase = "Odpowiedź C", answerDFromDataBase = "Odpowiedź D";

    private TextView question;
    private Button answerA, answerB, answerC, answerD, hint5050, hintAudience, hintPhone, backToMenu;



    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.question);

        Intent intent = getIntent();
        questionLvl = intent.getIntExtra("questionLvl", 1); // pobranie przekazanej wartości 'questionLvl'
        score = intent.getIntExtra("score", 0); // pobranie przekazanej wartości 'score'

        question = findViewById(R.id.question_text);
        answerA = findViewById(R.id.odpA);
        answerB = findViewById(R.id.odpB);
        answerC = findViewById(R.id.odpC);
        answerD = findViewById(R.id.odpD);
        hint5050 = findViewById(R.id.hint5050);
        hintAudience = findViewById(R.id.hintAudience);
        hintPhone = findViewById(R.id.hintPhone);
        backToMenu = findViewById(R.id.backToMenuButton);

        question.setText(questionFromDataBase + questionLvl);
        answerA.setText(answerAFromDataBase);
        answerB.setText(answerBFromDataBase);
        answerC.setText(answerCFromDataBase);
        answerD.setText(answerDFromDataBase);

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
                answerA.setBackgroundColor(getResources().getColor(R.color.orange));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (correctAnswer == "A") {
                            answerA.setBackgroundColor(getResources().getColor(R.color.green));
                            score = score + (questionLvl * 10);
                            questionLvl += 1;

                            Intent intent = new Intent(GameButtons.this, GameButtons.class);
                            intent.putExtra("questionLvl", questionLvl);
                            intent.putExtra("score", score);
                            startActivity(intent);

                            //TODO: Dodać warunek końca gry
                        } else {
                            answerA.setBackgroundColor(getResources().getColor(R.color.red));
                            Intent intent = new Intent(GameButtons.this, ending_screen.class);
                            intent.putExtra("score", score);
                            intent.putExtra("question", questionFromDataBase);
                            if(correctAnswer == "A") {
                                String correctAnswerAway = answerAFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer == "B") {
                                String correctAnswerAway = answerBFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer == "C") {
                                String correctAnswerAway = answerCFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else {
                                String correctAnswerAway = answerDFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }

                            startActivity(intent);
                        }
                    }
                }, 1000);
                break;
            case R.id.odpB:
                answerB.setBackgroundColor(getResources().getColor(R.color.orange));
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    public void run() {
                        if (correctAnswer == "B") {
                            answerB.setBackgroundColor(getResources().getColor(R.color.green));
                            score = score + (questionLvl * 10);
                            questionLvl += 1;

                            Intent intent = new Intent(GameButtons.this, GameButtons.class);
                            intent.putExtra("questionLvl", questionLvl);
                            intent.putExtra("score", score);
                            startActivity(intent);

                            //TODO: Dodać warunek końca gry
                        } else {
                            answerB.setBackgroundColor(getResources().getColor(R.color.red));
                            Intent intent = new Intent(GameButtons.this, ending_screen.class);
                            intent.putExtra("score", score);
                            intent.putExtra("question", questionFromDataBase);
                            if(correctAnswer == "A") {
                                String correctAnswerAway = answerAFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer == "B") {
                                String correctAnswerAway = answerBFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer == "C") {
                                String correctAnswerAway = answerCFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else {
                                String correctAnswerAway = answerDFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            startActivity(intent);
                        }
                    }
                }, 1000);
                break;
            case R.id.odpC:
                answerC.setBackgroundColor(getResources().getColor(R.color.orange));
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    public void run() {
                        if (correctAnswer == "C") {
                            answerC.setBackgroundColor(getResources().getColor(R.color.green));
                            score = score + (questionLvl * 10);
                            questionLvl += 1;

                            Intent intent = new Intent(GameButtons.this, GameButtons.class);
                            intent.putExtra("questionLvl", questionLvl);
                            intent.putExtra("score", score);
                            startActivity(intent);

                            //TODO: Dodać warunek końca gry
                        } else {
                            answerC.setBackgroundColor(getResources().getColor(R.color.red));
                            Intent intent = new Intent(GameButtons.this, ending_screen.class);
                            intent.putExtra("score", score);
                            intent.putExtra("question", questionFromDataBase);
                            if(correctAnswer == "A") {
                                String correctAnswerAway = answerAFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer == "B") {
                                String correctAnswerAway = answerBFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer == "C") {
                                String correctAnswerAway = answerCFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else {
                                String correctAnswerAway = answerDFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            startActivity(intent);
                        }
                    }
                }, 1000);
                break;
            case R.id.odpD:
                answerD.setBackgroundColor(getResources().getColor(R.color.orange));
                Handler handler3 = new Handler();
                handler3.postDelayed(new Runnable() {
                    public void run() {
                        if (correctAnswer == "D") {
                            answerD.setBackgroundColor(getResources().getColor(R.color.green));
                            score = score + (questionLvl * 10);
                            questionLvl += 1;

                            Intent intent = new Intent(GameButtons.this, GameButtons.class);
                            intent.putExtra("questionLvl", questionLvl);
                            intent.putExtra("score", score);
                            startActivity(intent);

                            //TODO: Dodać warunek końca gry
                        } else {
                            answerD.setBackgroundColor(getResources().getColor(R.color.red));
                            Intent intent = new Intent(GameButtons.this, ending_screen.class);
                            intent.putExtra("score", score);
                            intent.putExtra("question", questionFromDataBase);
                            if(correctAnswer == "A") {
                                String correctAnswerAway = answerAFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer == "B") {
                                String correctAnswerAway = answerBFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer == "C") {
                                String correctAnswerAway = answerCFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else {
                                String correctAnswerAway = answerDFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            startActivity(intent);
                        }
                    }
                }, 1000);
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