package com.example.carlos.smartthermostat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Used to grab information from the MongoDB server.
    TextView temp, pressure, humidity, timeStamp;
    Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Append Java objects with those generated from XML.
        temp = (TextView) findViewById(R.id.temp);
        pressure = (TextView) findViewById(R.id.pressure);
        humidity = (TextView) findViewById(R.id.humidity);
        timeStamp = (TextView) findViewById(R.id.timeStamp);
        refreshButton = (Button) findViewById(R.id.bntRefresh);

        //Replace the following with actual information grabbed from the server:
        temp.setText("Temperature:     " + Double.toString(35.25));
        pressure.setText("Pressure:            " + Double.toString(35.25));
        humidity.setText("Humidity:            " + Double.toString(27.10));
        timeStamp.setText("Last Update:      " + "09.30.2017 10:43 AM");

        refreshButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Replace the following with actual information grabbed from the server:
                temp.setText("Temperature:     " + Double.toString(40.25));
                pressure.setText("Pressure:            " + Double.toString(40.25));
                humidity.setText("Humidity:            " + Double.toString(40.10));
                timeStamp.setText("Last Update:      " + "09.30.2017 10:43 AM");
            }
        });

    }
}
