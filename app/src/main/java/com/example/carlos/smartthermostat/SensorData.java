package com.example.carlos.smartthermostat;

import com.example.carlos.smartthermostat.jsonmodel.SensorValues;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Carlos Flores on 10/7/2017.
 *
 */

public interface SensorData {

    @GET("getsensorvalues")
    Call<SensorValues> getSensorValues();
}
