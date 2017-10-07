package com.example.carlos.smartthermostat.jsonmodel.value;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos Flores on 10/7/2017.
 *
 */

public class Value {

    @SerializedName("value")
    @Expose
    private Double value;

    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Value{" +
                "value=" + value +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
