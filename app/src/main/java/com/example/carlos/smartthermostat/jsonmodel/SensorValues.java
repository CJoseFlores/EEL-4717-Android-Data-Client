package com.example.carlos.smartthermostat.jsonmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Carlos Flores on 10/7/2017.
 *
 */

public class SensorValues {

    @SerializedName("data")
    @Expose
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SensorValues{" +
                "data=" + data +
                '}';
    }
}
