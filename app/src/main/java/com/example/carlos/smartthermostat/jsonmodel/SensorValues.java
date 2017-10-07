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

    public String getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(String requestResult) {
        this.requestResult = requestResult;
    }

    public ArrayList<Values> getValues() {
        return values;
    }

    public void setValues(ArrayList<Values> values) {
        this.values = values;
    }

    public Double getPantherId() {
        return pantherId;
    }

    public void setPantherId(Double pantherId) {
        this.pantherId = pantherId;
    }

    @Override
    public String toString() {
        return "SensorValues{" +
                "requestResult='" + requestResult + '\'' +
                ", values=" + values +
                ", pantherId=" + pantherId +
                '}';
    }
}
