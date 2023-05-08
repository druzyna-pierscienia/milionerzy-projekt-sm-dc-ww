package com.example.milionerzy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button createAcount;
    private TextInputEditText login,pass01,pass02,mail;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        createAcount = findViewById(R.id.createAccount_button);
        createAcount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        mail = findViewById(R.id.registration_email_text);
        login = findViewById(R.id.registration_username_text);
        pass01 = findViewById(R.id.registration_password_text);
        pass02 = findViewById(R.id.registration_repeatPassword_text);

        String regexMail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern patternMail = Pattern.compile(regexMail);
        Matcher matcher = patternMail.matcher(mail.getText().toString().trim());

        if (matcher.matches()){
            if (pass01.getText().toString().trim().equals(pass02.getText().toString().trim())){
                // Pobieranie 'response' z bazy
                ApiRequestInto task = new ApiRequestInto();
                try {
                    String result = task.execute("http://10.0.2.2:8080/register?login="+login.getText().toString().trim()+"&password="+pass01.getText().toString().trim()+"&mail="+mail.getText().toString().trim()).get();

                        if(result.equals("420")) {
                            Intent intent1 = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent1);
                        } else if (result.equals("69")) {
                            alertSameLogin();
                        }else if (result.equals("0")) {
                            alertError();
                        }

                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                alertPassNotTheSame();
            }
        }
        else {
            alertBadMail();
        }


    }

    private void alertBadMail() {
        // Wyświetl okno dialogowe z alertem o nieprawidłowym adresie e-mail
        String message = "Provide valid e-mail";
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();

    }

    private void alertPassNotTheSame() {
        // Wyświetl okno dialogowe z alertem o nie zgadzająctch się hasłach
        String message = "Passwords does not match";
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();

    }

    private void alertSameLogin() {

        // Wyświetl okno dialogowe z alertem o istnieniu konta z takim loginem
        String message = "There is account with the same login";
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();


    }
    private void alertError() {

        // Wyświetl okno dialogowe z alertem o niepowodzeniu podczas rejestracjii
        String message = "There was an unexpected error during registration";
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();


    }
}
