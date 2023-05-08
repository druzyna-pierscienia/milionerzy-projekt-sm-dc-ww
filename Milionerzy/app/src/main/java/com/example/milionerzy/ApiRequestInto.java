package com.example.milionerzy;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequestInto extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        //String urlParameters = urls[1]; // pobierz parametry do wysłania z drugiego argumentu

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Ustawienie metody żądania na POST
            connection.setRequestMethod("POST");

            // Wysłanie danych w linku
            connection.setDoOutput(true);
            /*
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(urlParameters);
            writer.flush();
            */
            // Odbiór odpowiedzi
            InputStream in = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();
            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }

            // Zamknięcie połączenia
            //writer.close();
            reader.close();
            connection.disconnect();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(e);
        }
    }
}