package com.example.carlos.smartthermostat;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlos.smartthermostat.jsonmodel.SensorValues;
import com.example.carlos.smartthermostat.jsonmodel.Data;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Carlos Flores on 10/7/2017.
 */

public class SensorQuery {

    //Used as a custom client that ignores SSL Certificate issues.
    private OkHttpClient okHttpClient;
    private Context context;

    //Constants to use in the Retrofit calls.
    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "http://ffe7e972.ngrok.io/";

    /**
     * Constructs a SensorQuery and sets needed parameters for HTTP GET Requests.
     * @param c Context to extend.
     */
    public SensorQuery(Context c)
    {
        //Creating the HTTP client that ignores SSL Certificates (since server is Self-Signed).
        //okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        context = c; //Passing in the context from main.
    }

    /**
     * Sends the HTTP Server a GET request for the sensors, and returns a HashMap of desired values.
     */
    public void sensorInfoQuery(final TextView temp, final TextView pressure,
                                                   final TextView humidity, final TextView timeStamp,
                                                    final TextView timeStamp2, final TextView timeStamp3)
    {
        Toast.makeText(context, "Refreshing...", Toast.LENGTH_SHORT).show();

        //Create retrofit object to communicate with the webserver.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Creating a sensorData interface and making a Call with SensorValues.
        SensorData sensorData = retrofit.create(SensorData.class);
        Call<SensorValues> call = sensorData.getSensorValues();

        //Create an enqueue to make the call asynchronous to free up the GUI.
        call.enqueue(new Callback<SensorValues>() {
            @Override
            public void onResponse(Call<SensorValues> call, Response<SensorValues> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: Server JSON Response: " + response.body().toString());

                //Grabbing the list of : "values" from the JSON, and making a temp value var
                //to hold the actual numerical value.
                ArrayList<Data> sensorValues = response.body().getData();
                Double value;

                        /* Iterrating through each value to check if it is a temp, pressure,
                         * or humidity, and then parsing the value.
                         */
                for(int i=0; i < sensorValues.size(); i++)
                {
                    //Grabing the value to later parse into temp, humidity, or pressure.
                    value = sensorValues.get(i).getValue();

                    if("Temperature".equals(sensorValues.get(i).getVariableName()))
                    {
                        temp.setText(Double.toString(value) + " C");
                    }
                    else if ("Pressure".equals(sensorValues.get(i).getVariableName()))
                    {
                        pressure.setText(Double.toString(value) + " hPa");
                    }
                    else if("Humidity".equals(sensorValues.get(i).getVariableName()))
                    {
                        humidity.setText(Double.toString(value) + " %");
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
                                .getTimeStamp());
                        timeStamp2.setText("Last Update:      " + sensorValues.get(i)
                                .getTimeStamp());
                        timeStamp3.setText("Last Update:      " + sensorValues.get(i)
                                .getTimeStamp());
                    }
                }

                Toast.makeText(context, "Refreshed!", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<SensorValues> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage() );
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

//                //Replace the following with actual information grabbed from the server:
//                temp.setText("Temperature:     " + Double.toString(40.25) + " C");
//                pressure.setText("Pressure:            " + Double.toString(40.25) + " hPa");
//                humidity.setText("Humidity:            " + Double.toString(40.10) + " %");
//                timeStamp.setText("Last Update:      " + "09.30.2017 10:43 AM");
    }

}
