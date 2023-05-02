package com.example.milionerzy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainButtons extends AppCompatActivity implements View.OnClickListener {

    private Button newGameButton, scoreBoardButton, aboutAppButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        newGameButton = findViewById(R.id.newGame_button);
        scoreBoardButton = findViewById(R.id.scoreBoard_button);
        aboutAppButton = findViewById(R.id.aboutApp_button);
        logoutButton = findViewById(R.id.logout_button);

        newGameButton.setOnClickListener(this);
        scoreBoardButton.setOnClickListener(this);
        aboutAppButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newGame_button:
                setContentView(R.layout.question);
                break;
            case R.id.scoreBoard_button:
                setContentView(R.layout.scoreboard);
                break;
            case R.id.aboutApp_button:
                setContentView(R.layout.about_app);
                break;
            case R.id.logout_button:
                setContentView(R.layout.activity_main);
                break;
        }
    }
}
