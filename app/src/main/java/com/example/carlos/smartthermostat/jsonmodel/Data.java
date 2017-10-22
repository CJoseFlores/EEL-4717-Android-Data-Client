package com.example.carlos.smartthermostat.jsonmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos Flores on 10/7/2017.
 *
 */

public class Data {

    @SerializedName("variableName")
    @Expose
    private String variableName;

    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;

    @SerializedName("value")
    @Expose
    private Double value;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Data{" +
                "variableName='" + variableName + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", value=" + value +
                '}';
    }
}
