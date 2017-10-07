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

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Use to set up Retrofit calls.
    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://10.109.143.88:8443/";
    OkHttpClient okHttpClient; //Used as a custom client that ignores SSL Certificate issues.

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

        //Creating the HTTP client that ignores SSL Certificates (since server is Self-Signed).
        okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

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

                //Create retrofit object to communicate with the webserver.
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Creating a sensorData interface and making a Call with SensorValues.
                SensorData sensorData = retrofit.create(SensorData.class);
                Call<SensorValues> call = sensorData.getSensorValues("5160328");
                
                //Create an enqueue to make the call asynchronous to free up the GUI.
                call.enqueue(new Callback<SensorValues>() {
                    @Override
                    public void onResponse(Call<SensorValues> call, Response<SensorValues> response) {
                        Log.d(TAG, "onResponse: Server Response: " + response.toString());
                        Log.d(TAG, "onResponse: Server JSON Response: " + response.body().toString());

                        //Grabbing the list of : "values" from the JSON, and making a temp value var
                        //to hold the actual numerical value.
                        ArrayList<Values> sensorValues = response.body().getValues();
                        Double value;

                        /* Iterrating through each value to check if it is a temp, pressure,
                         * or humidity, and then parsing the value.
                         */
                        for(int i=0; i < sensorValues.size(); i++)
                        {
                            //Grabing the value to later parse into temp, humidity, or pressure.
                            value = sensorValues.get(i).getValue().getValue();

                            if("Temperature".equals(sensorValues.get(i).getVariableName()))
                            {
                                temp.setText("Temperature:     " + Double.toString(value) + " C");
                            }
                            else if ("Pressure".equals(sensorValues.get(i).getVariableName()))
                            {
                                pressure.setText("Pressure:            " + Double.toString(value) + " hPa");
                            }
                            else if("Humidity".equals(sensorValues.get(i).getVariableName()))
                            {
                                humidity.setText("Humidity:            " + Double.toString(value) + " %");
                            }
                            else
                            {
                                Log.d(TAG, "JSON does not contain \"Temperature\"," +
                                        "\"Pressure\" or \"Humidity\" tags.");
                            }

                            //Append the timestamp when on the final value.
                            if(i == sensorValues.size() - 1)
                            {
                                timeStamp.setText("Last Update:      " + sensorValues.get(i)
                                        .getValue().getTimeStamp());
                            }
                        }

                        Toast.makeText(MainActivity.this, "Refreshed!", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<SensorValues> call, Throwable t) {
                        Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage() );
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

//                //Replace the following with actual information grabbed from the server:
//                temp.setText("Temperature:     " + Double.toString(40.25) + " C");
//                pressure.setText("Pressure:            " + Double.toString(40.25) + " hPa");
//                humidity.setText("Humidity:            " + Double.toString(40.10) + " %");
//                timeStamp.setText("Last Update:      " + "09.30.2017 10:43 AM");
            }
        });

    }
}
