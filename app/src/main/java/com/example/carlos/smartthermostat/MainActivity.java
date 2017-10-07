package com.example.carlos.smartthermostat;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlos.smartthermostat.jsonmodel.SensorValues;
import com.example.carlos.smartthermostat.jsonmodel.Values;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    //Used to grab information from the MongoDB server.
    TextView temp, pressure, humidity, timeStamp;
    HashMap<String, String> queryResult;
    SensorQuery sensorQuery;

    Button refreshButton; //Sends another HTTP GET request to refresh sensor values.

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Append Java objects with those generated from XML.
        temp = (TextView) findViewById(R.id.temp);
        pressure = (TextView) findViewById(R.id.pressure);
        humidity = (TextView) findViewById(R.id.humidity);
        timeStamp = (TextView) findViewById(R.id.timeStamp);
        refreshButton = (Button) findViewById(R.id.bntRefresh);

        //Creating a SensorQuery object
        sensorQuery = new SensorQuery(MainActivity.this);

        //Performing one query to grab initial values.
        queryResult = sensorQuery.sensorInfoQuery();

        temp.setText("Temperature:     " + queryResult.get("temp") + " C");
        pressure.setText("Pressure:            " + queryResult.get("pressure") + " hPa");
        humidity.setText("Humidity:            " + queryResult.get("humidity") + " %");
        timeStamp.setText("Last Update:      " + queryResult.get("timeStamp"));


//        //Replace the following with actual information grabbed from the server:
//        temp.setText("Temperature:     " + Double.toString(0.0) + " C");
//        pressure.setText("Pressure:            " + Double.toString(0.0) + " hPa");
//        humidity.setText("Humidity:            " + Double.toString(0.0) + " %");
//        timeStamp.setText("Last Update:      " + "10.07.2017 12:00 AM");


        //Query the MongoDB and parse information to the TextView objects.
        refreshButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                queryResult = sensorQuery.sensorInfoQuery();

                temp.setText("Temperature:     " + queryResult.get("temp") + " C");
                pressure.setText("Pressure:            " + queryResult.get("pressure") + " hPa");
                humidity.setText("Humidity:            " + queryResult.get("humidity") + " %");
                timeStamp.setText("Last Update:      " + queryResult.get("timeStamp"));
            }
        });

    }
}
