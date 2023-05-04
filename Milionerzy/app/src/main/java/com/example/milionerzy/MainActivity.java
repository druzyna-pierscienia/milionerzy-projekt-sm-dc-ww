package com.example.milionerzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton, loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_button:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button:

                //TODO:Warunek sprawdzający czy zalogowano

                Intent intent1 = new Intent(MainActivity.this, MainButtons.class);
                startActivity(intent1);
        }
    }
}
