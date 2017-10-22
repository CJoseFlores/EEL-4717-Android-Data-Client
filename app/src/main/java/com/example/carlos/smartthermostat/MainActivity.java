package com.example.carlos.smartthermostat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {



    //Used to grab information from the MongoDB server.
    TextView temp, pressure, humidity, timeStamp, timeStamp2, timeStamp3;
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
        timeStamp = (TextView) findViewById(R.id.temp_TimeStamp);
        timeStamp2 = (TextView) findViewById(R.id.pressureTimeStamp);
        timeStamp3 = (TextView)  findViewById(R.id.humidityTimeStamp);

        refreshButton = (Button) findViewById(R.id.bntRefresh);

        //Creating a SensorQuery object
        sensorQuery = new SensorQuery(MainActivity.this);

        //Performing one query to grab initial values.
        sensorQuery.sensorInfoQuery(temp, pressure, humidity, timeStamp, timeStamp2, timeStamp3);

        //Query the MongoDB and parse information to the TextView objects.
        refreshButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sensorQuery.sensorInfoQuery(temp, pressure, humidity, timeStamp, timeStamp2,
                        timeStamp3);
            }
        });

    }
}
