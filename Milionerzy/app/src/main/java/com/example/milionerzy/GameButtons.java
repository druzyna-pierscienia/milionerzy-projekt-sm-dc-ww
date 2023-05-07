package com.example.milionerzy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameButtons extends AppCompatActivity implements View.OnClickListener {

    public Integer score = 0, questionLvl = 1;


    //TODO: Pobieranie 'response' z bazy


    private static final int ANSWERS_NUMBER = 4;
    private static final int HALF_ANSWERS_NUMBER = ANSWERS_NUMBER / 2;


    //TODO: Cała poniższa linijka do zmiany na pobieranie z bazy
    public String correctAnswer = "A", questionFromDataBase = response, answerAFromDataBase = "Odpowiedź A", answerBFromDataBase = "Odpowiedź B", answerCFromDataBase = "Odpowiedź C", answerDFromDataBase = "Odpowiedź D";

    private TextView question;
    private Button answerA, answerB, answerC, answerD, hint5050, hintAudience, hintPhone, backToMenu;

    private ArrayList<Button> answerButtons = new ArrayList<>();

    public GameButtons() throws IOException {
    }

    private void correct_function(){
        score = score + (questionLvl * 10);
        questionLvl += 1;

        Intent intent = new Intent(GameButtons.this, GameButtons.class);
        intent.putExtra("questionLvl", questionLvl);
        intent.putExtra("score", score);
        startActivity(intent);
    }
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



        answerButtons.add(answerA);
        answerButtons.add(answerB);
        answerButtons.add(answerC);
        answerButtons.add(answerD);

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
                            correct_function();


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
                            correct_function();

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
                            correct_function();

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
                            correct_function();

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

                // Disable 2 incorrect answers
                disableTwoIncorrectAnswers();
                // Disable 50/50 hint button after it's used
                hint5050.setEnabled(false);

                break;
            case R.id.hintAudience:
                // TODO: Koło ratunkowe - pytanie do publiczności

                showAudienceDialog();

                break;
            case R.id.hintPhone:
                // TODO: Koło ratunkowe - pytanie do przyjaciela

                usePhoneAFriend();


                break;
            case R.id.backToMenuButton:
                Intent intent = new Intent(GameButtons.this, MainButtons.class);
                startActivity(intent);
                break;
        }
    }

    private void disableTwoIncorrectAnswers() {
        ArrayList<Integer> incorrectIndexes = new ArrayList<>();
        int correctIndex = -1;
        for (int i = 0; i < ANSWERS_NUMBER; i++) {
            Button button = answerButtons.get(i);
            if (correctAnswer.equals(button.getTag())) {
                correctIndex = i;
            } else {
                incorrectIndexes.add(i);
            }
        }

        Collections.shuffle(incorrectIndexes);
        int disabledCount = 0;
        for (int i = 0; i < incorrectIndexes.size(); i++) {
            if (disabledCount >= HALF_ANSWERS_NUMBER) {
                break;
            }
            int index = incorrectIndexes.get(i);
            Button button = answerButtons.get(index);
            if (button.isEnabled()) {
                button.setEnabled(false);
                disabledCount++;
            }
        }
        hint5050.setVisibility(View.INVISIBLE);
    }
    private void showAudienceDialog() {
        // Wylosuj procentowe szanse odpowiedzi


        int chanceA = new Random().nextInt(41) + 50; // szansa na odpowiedź A to 50-90%
        int chanceB = new Random().nextInt(100 - chanceA) + 1; // szansa na odpowiedź B to 1-80%
        int chanceC = new Random().nextInt(100 - chanceA - chanceB) + 1; // szansa na odpowiedź C to 1-70%
        int chanceD = 100 - chanceA - chanceB - chanceC; // szansa na odpowiedź D to reszta

        // Wyświetl okno dialogowe z wynikami
        String message = "Pytanie do publiczności:\nA: " + chanceA + "%\nB: " + chanceB + "%\nC: " + chanceC + "%\nD: " + chanceD + "%";
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();

        hintAudience.setVisibility(View.INVISIBLE);
    }

    private void usePhoneAFriend() {

    // Wylosuj procentową szansę na poprawną odpowiedź przy użyciu "telefonu do przyjaciela"
        boolean isAnswerCorrect = new Random().nextInt(100) < 75;

    // Wyświetl odpowiedni komunikat w zależności od wyniku losowania
        String message;
        if (isAnswerCorrect) {
            message = "Twoj przyjaciel pomógl Ci, poprawna odpowiedź to: " + correctAnswer;
        } else {
            message = "Twoj przyjaciel nie jest pewny odpowiedzi, trudno powiedzieć...";
        }

        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();

        hintPhone.setVisibility(View.INVISIBLE);
    }







}