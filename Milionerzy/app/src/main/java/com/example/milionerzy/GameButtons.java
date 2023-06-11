package com.example.milionerzy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class GameButtons extends AppCompatActivity implements View.OnClickListener {

    public Integer score=0,scoreGuaranteed=0, questionLvl=1, hint5050Use = 0, hintAudienceUse = 0, hintPhoneUse = 0;
    private static final int ANSWERS_NUMBER = 4;
    private static final int HALF_ANSWERS_NUMBER = ANSWERS_NUMBER / 2;


    //TODO: Cała poniższa linijka do zmiany na pobieranie z bazy
    public String correctAnswer, questionFromDataBase, answerAFromDataBase, answerBFromDataBase, answerCFromDataBase, answerDFromDataBase;
    private TextView question;
    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private Button hint5050;
    private ImageButton hintAudience;
    private ImageButton hintPhone;
    private Button backToMenu;
    private ArrayList<Button> answerButtons = new ArrayList<>();
    public GameButtons() throws IOException {
    }

    private void correct_function(){
        score = score + (questionLvl * 10);
        if(questionLvl%3 == 0){
            scoreGuaranteed = score;
        }
        questionLvl += 1;

        if(questionLvl <= 10) {
            Intent intent = new Intent(GameButtons.this, GameButtons.class);
            intent.putExtra("questionLvl", questionLvl);
            intent.putExtra("score", score);
            intent.putExtra("scoreGuaranteed", scoreGuaranteed);
            intent.putExtra("hint5050Use", hint5050Use);
            intent.putExtra("hintAudienceUse", hintAudienceUse);
            intent.putExtra("hintPhoneUse", hintPhoneUse);

            startActivity(intent);
        }
        else{
            Intent intent = new Intent(GameButtons.this, ending_screen.class);
            intent.putExtra("questionLvl", questionLvl);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    }

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.question);



        Intent intent = getIntent();
        questionLvl = intent.getIntExtra("questionLvl", 1); // pobranie przekazanej wartości 'questionLvl'
        score = intent.getIntExtra("score", 0); // pobranie przekazanej wartości 'score'
        scoreGuaranteed = intent.getIntExtra("scoreGuaranteed", 0); // pobranie przekazanej wartości 'scoreGuaranteed'
        hint5050Use = intent.getIntExtra("hint5050Use",0);
        hintAudienceUse = intent.getIntExtra("hintAudienceUse", 0);
        hintPhoneUse = intent.getIntExtra("hintPhoneUse", 0);

        // Pobieranie 'response' z bazy
        ApiRequest task = new ApiRequest();
        try {
            String result = task.execute("http://10.0.2.2:8080/question?roundNumber="+String.valueOf(questionLvl)).get();
            if (result.equals("blad")){
                result = task.execute("http://10.0.2.2:8080/question?roundNumber="+String.valueOf(questionLvl)).get();
            }
            else {
                String[] quest = result.split("/");
                questionFromDataBase = quest[0]; //+ " " + quest[5];
                answerAFromDataBase = quest[1];
                answerBFromDataBase = quest[2];
                answerCFromDataBase = quest[3];
                answerDFromDataBase = quest[4];
                correctAnswer = quest[5];
            }

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        question = findViewById(R.id.question_text);
        answerA = findViewById(R.id.odpA);
        answerB = findViewById(R.id.odpB);
        answerC = findViewById(R.id.odpC);
        answerD = findViewById(R.id.odpD);
        hint5050 = findViewById(R.id.hint5050);
        hintAudience = findViewById(R.id.hintAudience);
        hintPhone = findViewById(R.id.hintPhone);
        backToMenu = findViewById(R.id.backToMenuButton);

        //question.setText(" Pytanie numer: "+ questionLvl +" "+ questionFromDataBase );
        question.setText(questionFromDataBase);
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

        if(hint5050Use != 0){
            hint5050.setVisibility(View.INVISIBLE);
        }
        if(hintAudienceUse != 0){
            hintAudience.setVisibility(View.INVISIBLE);
        }
        if(hintPhoneUse != 0){
            hintPhone.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.odpA:
                answerA.setBackgroundColor(getResources().getColor(R.color.orange));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (correctAnswer.equals("a")) {
                            answerA.setBackgroundColor(getResources().getColor(R.color.green));
                            correct_function();


                            //TODO: Dodać warunek końca gry
                        } else {
                            answerA.setBackgroundColor(getResources().getColor(R.color.red));
                            Intent intent = new Intent(GameButtons.this, ending_screen.class);
                            intent.putExtra("score", scoreGuaranteed);
                            intent.putExtra("question", questionFromDataBase);
                            intent.putExtra("hint5050Use",hint5050Use);
                            intent.putExtra("hintAudienceUse",hintAudienceUse);
                            intent.putExtra("hintPhoneUse",hintPhoneUse);
                            if(correctAnswer.equals("a")) {
                                String correctAnswerAway = answerAFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("b")){
                                String correctAnswerAway = answerBFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("c")) {
                                String correctAnswerAway = answerCFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("d")){
                                String correctAnswerAway = answerDFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else {
                                String correctAnswerAway = "BŁAD";
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
                        if (correctAnswer.equals("b")) {
                            answerB.setBackgroundColor(getResources().getColor(R.color.green));
                            correct_function();

                            //TODO: Dodać warunek końca gry
                        } else {
                            answerB.setBackgroundColor(getResources().getColor(R.color.red));
                            Intent intent = new Intent(GameButtons.this, ending_screen.class);
                            intent.putExtra("score", scoreGuaranteed);
                            intent.putExtra("question", questionFromDataBase);
                            intent.putExtra("hint5050Use",hint5050Use);
                            intent.putExtra("hintAudienceUse",hintAudienceUse);
                            intent.putExtra("hintPhoneUse",hintPhoneUse);
                            if(correctAnswer.equals("a")) {
                                String correctAnswerAway = answerAFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("b")){
                                String correctAnswerAway = answerBFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("c")) {
                                String correctAnswerAway = answerCFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("d")){
                                String correctAnswerAway = answerDFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else {
                                String correctAnswerAway = "BŁAD";
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
                        if (correctAnswer.equals("c")) {
                            answerC.setBackgroundColor(getResources().getColor(R.color.green));
                            correct_function();

                            //TODO: Dodać warunek końca gry
                        } else {
                            answerC.setBackgroundColor(getResources().getColor(R.color.red));
                            Intent intent = new Intent(GameButtons.this, ending_screen.class);
                            intent.putExtra("score", scoreGuaranteed);
                            intent.putExtra("question", questionFromDataBase);
                            intent.putExtra("hint5050Use",hint5050Use);
                            intent.putExtra("hintAudienceUse",hintAudienceUse);
                            intent.putExtra("hintPhoneUse",hintPhoneUse);
                            if(correctAnswer.equals("a")) {
                                String correctAnswerAway = answerAFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("b")){
                                String correctAnswerAway = answerBFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("c")) {
                                String correctAnswerAway = answerCFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("d")){
                                String correctAnswerAway = answerDFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else {
                                String correctAnswerAway = "BŁAD";
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
                        if (correctAnswer.equals("d")) {
                            answerD.setBackgroundColor(getResources().getColor(R.color.green));
                            correct_function();

                            //TODO: Dodać warunek końca gry
                        } else {
                            answerD.setBackgroundColor(getResources().getColor(R.color.red));
                            Intent intent = new Intent(GameButtons.this, ending_screen.class);
                            intent.putExtra("score", scoreGuaranteed);
                            intent.putExtra("question", questionFromDataBase);
                            intent.putExtra("hint5050Use",hint5050Use);
                            intent.putExtra("hintAudienceUse",hintAudienceUse);
                            intent.putExtra("hintPhoneUse",hintPhoneUse);
                            if(correctAnswer.equals("a")) {
                                String correctAnswerAway = answerAFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("b")){
                                String correctAnswerAway = answerBFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("c")) {
                                String correctAnswerAway = answerCFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else if(correctAnswer.equals("d")){
                                String correctAnswerAway = answerDFromDataBase;
                                intent.putExtra("correctAnswer", correctAnswerAway);
                            }
                            else {
                                String correctAnswerAway = "BŁAD";
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
                hint5050Use += 1;

                break;
            case R.id.hintAudience:
                // TODO: Koło ratunkowe - pytanie do publiczności

                showAudienceDialog();
                hintAudienceUse += 1;
                break;
            case R.id.hintPhone:
                // TODO: Koło ratunkowe - pytanie do przyjaciela

                usePhoneAFriend();
                hintPhoneUse += 1;

                break;
            case R.id.backToMenuButton:
                Intent intent = new Intent(GameButtons.this, MainButtons.class);
                startActivity(intent);
                break;
        }
    }

    private void disableTwoIncorrectAnswers() {
        List<Button> wrongAnswers = new ArrayList<>();

        if (correctAnswer.equals("a")) {
            wrongAnswers.add(answerB);
            wrongAnswers.add(answerC);
            wrongAnswers.add(answerD);
        } else if (correctAnswer.equals("b")) {
            wrongAnswers.add(answerA);
            wrongAnswers.add(answerC);
            wrongAnswers.add(answerD);
        } else if (correctAnswer.equals("c")) {
            wrongAnswers.add(answerA);
            wrongAnswers.add(answerB);
            wrongAnswers.add(answerD);
        } else {
            wrongAnswers.add(answerA);
            wrongAnswers.add(answerB);
            wrongAnswers.add(answerC);
        }

        Random random = new Random();
        Button button1 = wrongAnswers.get(random.nextInt(wrongAnswers.size()));
        wrongAnswers.remove(button1);
        Button button2 = wrongAnswers.get(random.nextInt(wrongAnswers.size()));

        button1.setEnabled(false);
        button2.setEnabled(false);

        hint5050.setVisibility(View.INVISIBLE);
    }
    private void showAudienceDialog() {
        // Sprawdzenie, czy koło "50/50" zostało już użyte
        boolean is5050Used = !answerA.isEnabled() || !answerB.isEnabled() || !answerC.isEnabled() || !answerD.isEnabled();

        // Wylosuj procentowe szanse odpowiedzi
        int chanceA, chanceB, chanceC, chanceD;

        if (is5050Used) {
            // Koło "50/50" było już użyte, rozdziel procenty pomiędzy pozostałe dwie odpowiedzi
            chanceA = new Random().nextInt(71) + 15; // szansa na odpowiedź A to 15-85%
            chanceB = 100 - chanceA; // szansa na odpowiedź B to reszta
            chanceC = 0;
            chanceD = 0;
        } else {
            // Koło "50/50" jeszcze nie zostało użyte, losuj normalny rozkład procentowy
            chanceA = new Random().nextInt(41) + 50; // szansa na odpowiedź A to 50-90%
            chanceB = new Random().nextInt(100 - chanceA) + 1; // szansa na odpowiedź B to 1-80%
            chanceC = new Random().nextInt(100 - chanceA - chanceB) + 1; // szansa na odpowiedź C to 1-70%
            chanceD = 100 - chanceA - chanceB - chanceC; // szansa na odpowiedź D to reszta
        }

        // Tworzenie wiadomości dla okna dialogowego
        
        String message;
        if (correctAnswer.equals("a") && !answerB.isEnabled() && !answerC.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceA + "%\nB: " + chanceD + "%\nC: " + chanceC + "%\nD: " + chanceB + "%";
        } else if (correctAnswer.equals("a") && !answerB.isEnabled() && !answerD.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceA + "%\nB: " + chanceC + "%\nC: " + chanceB + "%\nD: " + chanceD + "%";
        } else if (correctAnswer.equals("a") && !answerC.isEnabled() && !answerD.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceA + "%\nB: " + chanceC + "%\nC: " + chanceB + "%\nD: " + chanceD + "%";
        } else if (correctAnswer.equals("b") && !answerA.isEnabled() && !answerD.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceC + "%\nB: " + chanceA + "%\nC: " + chanceB + "%\nD: " + chanceD + "%";
        } else if (correctAnswer.equals("b") && !answerC.isEnabled() && !answerA.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceC + "%\nB: " + chanceA + "%\nC: " + chanceD + "%\nD: " + chanceB + "%";
        } else if (correctAnswer.equals("b") && !answerC.isEnabled() && !answerD.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceB + "%\nB: " + chanceA + "%\nC: " + chanceC + "%\nD: " + chanceD + "%";
        } else if (correctAnswer.equals("c") && !answerA.isEnabled() && !answerD.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceC + "%\nB: " + chanceB + "%\nC: " + chanceA + "%\nD: " + chanceD + "%";
        } else if (correctAnswer.equals("c") && !answerB.isEnabled() && !answerA.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceC + "%\nB: " + chanceD + "%\nC: " + chanceA + "%\nD: " + chanceB + "%";
        } else if (correctAnswer.equals("c") && !answerB.isEnabled() && !answerD.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceB + "%\nB: " + chanceC + "%\nC: " + chanceA + "%\nD: " + chanceD + "%";
        } else if (correctAnswer.equals("d") && !answerA.isEnabled() && !answerB.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceC + "%\nB: " + chanceD + "%\nC: " + chanceB + "%\nD: " + chanceA + "%";
        } else if (correctAnswer.equals("d") && !answerC.isEnabled() && !answerA.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceC + "%\nB: " + chanceB + "%\nC: " + chanceD + "%\nD: " + chanceA + "%";
        } else if (correctAnswer.equals("d") && !answerB.isEnabled() && !answerC.isEnabled()) {
            message = "Pytanie do publiczności:\nA: " + chanceB + "%\nB: " + chanceC + "%\nC: " + chanceD + "%\nD: " + chanceA + "%";
        } else if (correctAnswer.equals("a")) {
            message = "Pytanie do publiczności:\nA: " + chanceA + "%\nB: " + chanceB + "%\nC: " + chanceC + "%\nD: " + chanceD + "%";
        } else if (correctAnswer.equals("b")) {
            message = "Pytanie do publiczności:\nA: " + chanceB + "%\nB: " + chanceA + "%\nC: " + chanceC + "%\nD: " + chanceD + "%";
        } else if (correctAnswer.equals("c")) {
            message = "Pytanie do publiczności:\nA: " + chanceC + "%\nB: " + chanceB + "%\nC: " + chanceA + "%\nD: " + chanceD + "%";
        } else {
            message = "Pytanie do publiczności:\nA: " + chanceD + "%\nB: " + chanceB + "%\nC: " + chanceC + "%\nD: " + chanceA + "%";
        }

        // Wyświetlanie okna dialogowego
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pytanie do publiczności");
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();



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