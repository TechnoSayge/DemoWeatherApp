package com.example.android.sayge.newestweatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    class WeatherInfo extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream is = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);

                int data = reader.read();
                String apiDetails = "";
                char current;

                while (data != -1) {

                    current = (char) data;
                    apiDetails += current;
                    data = reader.read();
                }

                return apiDetails;


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void getWeatherInfo(View view) {
        WeatherInfo weatherInfo = new WeatherInfo();

        EditText cityName = (EditText) findViewById(R.id.cityEditText);

        try {

            String weatherApiDetails = weatherInfo.execute("http://samples.openweathermap.org/data/2.5/weather?q="
                    + cityName.getText().toString() + "&appid=4e8a112b4bfda3f29a37b32ecc9aa53d").get();
            Log.i("Weather Api Info", weatherApiDetails);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
