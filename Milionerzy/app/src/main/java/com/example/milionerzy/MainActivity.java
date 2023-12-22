package com.example.milionerzy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton, loginButton;
    private TextInputEditText login, password;

    public static MediaPlayer music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);

        music = MediaPlayer.create(this, R.raw.music);
        music.start();

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);


    };

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        switch (v.getId()){
            case R.id.register_button:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button:

                login = findViewById(R.id.username_text);
                password = findViewById(R.id.password_text);

                // Pobieranie 'response' z bazy
                ApiRequest task = new ApiRequest();
                try {
                    String result = task.execute("http://10.0.2.2:8080/login?login="+login.getText()+"&password="+password.getText()).get();
                    if(result.equals("true")) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", login.getText().toString().trim());
                        editor.apply();


                        Intent intent1 = new Intent(MainActivity.this, MainButtons.class);
                        startActivity(intent1);
                    }
                    else{
                        alertIncorrectData();
                    }
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;


        }

    }

    private void alertIncorrectData() {

        // Wyświetl okno dialogowe z alertem o nieprawidłowych danych logowania
        String message = "Incorect pasword or login";
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();


    }





}
