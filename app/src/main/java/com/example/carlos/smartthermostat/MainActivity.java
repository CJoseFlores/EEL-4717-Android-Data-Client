package com.example.carlos.smartthermostat;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Use to set up Retrofit calls.
    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://10.109.143.88:8443/";

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
        temp.setText("Temperature:     " + Double.toString(0.0) + " C");
        pressure.setText("Pressure:            " + Double.toString(0.0) + " hPa");
        humidity.setText("Humidity:            " + Double.toString(0.0) + " %");
        timeStamp.setText("Last Update:      " + "10.07.2017 12:00 AM");

        refreshButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();

                //Replace the following with actual information grabbed from the server:
                temp.setText("Temperature:     " + Double.toString(40.25) + " C");
                pressure.setText("Pressure:            " + Double.toString(40.25) + " hPa");
                humidity.setText("Humidity:            " + Double.toString(40.10) + " %");
                timeStamp.setText("Last Update:      " + "09.30.2017 10:43 AM");

                Toast.makeText(MainActivity.this, "Finished Refreshing!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
