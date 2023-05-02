package com.example.milionerzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GameButtons {
    private Button odpA;
    private Button odpB;
    private Button odpC;
    private Button odpD;
    private Button hint50_50;
    private Button hintAudience;
    private Button hintPhone;

    private Integer questionLvl = 1; //Do usunięcia i pobieranie z bazy
    private Integer score = 0; //Do usunięcia i pobieranie z bazy
    private String correctAnswer = "A"; //DO USUNIĘCIA I ZMIANY NA POBRANIE Z BAZY DANYCH

    private void pobieraniePytanZBazy(int questionLvl){
        //To powinno być osobną klasą DO POPRAWY RÓWNIEŻ W TYM KODZIE
    }

    public GameButtons(AppCompatActivity activity) {
        odpA = new Button(activity);
        odpB = new Button(activity);
        odpC = new Button(activity);
        odpD = new Button(activity);
        hint50_50 = new Button(activity);
        hintAudience = new Button(activity);
        hintPhone = new Button(activity);


        odpA.setText("odpA");
        odpB.setText("odpB");
        odpC.setText("odpC");
        odpD.setText("odpD");
        hint50_50.setText("hint50_50");
        hintAudience.setText("hintAudience");
        hintPhone.setText("hintPhone");

        odpA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: wprowadź swój kod dla przycisku odpA
                if(correctAnswer.equals("A")){
                    questionLvl++;
                    score = score + (questionLvl * 10);
                    pobieraniePytanZBazy(questionLvl);
                }
                else {
                    Intent intent = new Intent(v.getContext(), ending_screen.class);
                    intent.putExtra("score", score); // przekazanie wartości 'score'
                    v.getContext().startActivity(intent);
                }
            }
        });

        odpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: wprowadź swój kod dla przycisku odpB
                if(correctAnswer.equals("B")){
                    questionLvl++;
                    score = score + (questionLvl * 10);
                    pobieraniePytanZBazy(questionLvl);
                }
                else {
                    Intent intent = new Intent(v.getContext(), ending_screen.class);
                    intent.putExtra("score", score); // przekazanie wartości 'score'
                    v.getContext().startActivity(intent);
                }
            }
        });

        odpC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: wprowadź swój kod dla przycisku odpC
                if(correctAnswer.equals("C")){
                    questionLvl++;
                    score = score + (questionLvl * 10);
                    pobieraniePytanZBazy(questionLvl);
                }
                else {
                    Intent intent = new Intent(v.getContext(), ending_screen.class);
                    intent.putExtra("score", score); // przekazanie wartości 'score'
                    v.getContext().startActivity(intent);
                }
            }
        });

        odpD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: wprowadź swój kod dla przycisku odpD
                if(correctAnswer.equals("D")){
                    questionLvl++;
                    score = score + (questionLvl * 10);
                    pobieraniePytanZBazy(questionLvl);
                }
                else {
                    Intent intent = new Intent(v.getContext(), ending_screen.class);
                    intent.putExtra("score", score); // przekazanie wartości 'score'
                    v.getContext().startActivity(intent);
                }
            }
        });

        hint50_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: wprowadź swój kod dla przycisku hint50_50

            }
        });

        hintAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: wprowadź swój kod dla przycisku hintAudience

            }
        });

        hintPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: wprowadź swój kod dla przycisku hintPhone

            }
        });
    }
}