package com.example.milionerzy;

import android.content.Intent;
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

                Intent intent = new Intent(MainButtons.this, GameButtons.class);
                startActivity(intent);
                break;
            case R.id.scoreBoard_button:

                Intent intent1 = new Intent(MainButtons.this, Scoreboard.class);
                startActivity(intent1);
                break;
            case R.id.aboutApp_button:

                Intent intent2 = new Intent(MainButtons.this, AboutApp.class);
                startActivity(intent2);
                break;
            case R.id.logout_button:

                Intent intent3 = new Intent(MainButtons.this, MainActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);
                break;
        }
    }
}
