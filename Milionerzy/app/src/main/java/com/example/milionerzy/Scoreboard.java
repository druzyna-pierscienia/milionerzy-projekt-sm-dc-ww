package com.example.milionerzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class Scoreboard extends AppCompatActivity {

    private TextView pos1;
    private TextView pos2;
    private TextView pos3;
    private TextView pos4;
    private TextView pos5;
    private TextView pos6;
    private TextView pos7;
    private TextView pos8;
    private TextView pos9;
    private TextView pos10;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        Button backButton = (Button) findViewById(R.id.back_button);



        // Pobieranie 'response' z bazy
        ApiRequest task = new ApiRequest();
        try {
            pos1 = findViewById(R.id.position1);
            pos2 = findViewById(R.id.position2);
            pos3 = findViewById(R.id.position3);
            pos4 = findViewById(R.id.position4);
            pos5 = findViewById(R.id.position5);
            pos6 = findViewById(R.id.position6);
            pos7 = findViewById(R.id.position7);
            pos8 = findViewById(R.id.position8);
            pos9 = findViewById(R.id.position9);
            pos10 = findViewById(R.id.position10);


            String result = task.execute("http://10.0.2.2:8080/ranking").get();

                pos1.setText("0");
                pos2.setText("0");
                pos3.setText("0");
                pos4.setText("0");
                pos5.setText("0");
                pos6.setText("0");
                pos7.setText("0");
                pos8.setText("0");
                pos9.setText("0");
                pos10.setText("0");




                String[] ranking = result.split(";");
            String[] tmp = new String[2];
            if(ranking.length>1) {
                tmp = ranking[0].split("/");
                pos1.setText(tmp[0] + "    -    " + tmp[1]);
                }
                if(ranking.length>=2) {
                tmp = ranking[1].split("/");
                pos2.setText(tmp[0] + "    -    " + tmp[1]);
            }
            if(ranking.length>=3) {
                tmp = ranking[2].split("/");
                pos3.setText(tmp[0] + "    -    " + tmp[1]);
            }
            if(ranking.length>=4) {
                tmp = ranking[3].split("/");
                pos4.setText(tmp[0] + "    -    " + tmp[1]);
            }
            if(ranking.length>=5) {
                tmp = ranking[4].split("/");
                pos5.setText(tmp[0] + "    -    " + tmp[1]);
            }
            if(ranking.length>=6) {
                tmp = ranking[5].split("/");
                pos6.setText(tmp[0] + "    -    " + tmp[1]);
            }
            if(ranking.length>=7) {
                tmp = ranking[6].split("/");
                pos7.setText(tmp[0] + "    -    " + tmp[1]);
            }
            if(ranking.length>=8) {
                tmp = ranking[7].split("/");
                pos8.setText(tmp[0] + "    -    " + tmp[1]);
            }
            if(ranking.length>=9) {
                tmp = ranking[8].split("/");
                pos9.setText(tmp[0] + "    -    " + tmp[1]);
            }
            if(ranking.length>=10) {
                tmp = ranking[9].split("/");
                pos10.setText(tmp[0] + "    -    " + tmp[1]);
            }


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scoreboard.this,MainButtons.class);
                startActivity(intent);
            }
        });

    }
}
