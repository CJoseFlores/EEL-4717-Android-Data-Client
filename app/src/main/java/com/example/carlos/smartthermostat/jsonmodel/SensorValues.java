package com.example.carlos.smartthermostat.jsonmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Carlos Flores on 10/7/2017.
 *
 */

public class SensorValues {

    @SerializedName("requestResult")
    @Expose
    private String requestResult;

    @SerializedName("values")
    @Expose
    private ArrayList<Values> values;

    @SerializedName("pantherId")
    @Expose
    private Double pantherId ;
}
