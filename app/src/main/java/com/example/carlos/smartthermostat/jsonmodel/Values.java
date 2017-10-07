package com.example.carlos.smartthermostat.jsonmodel;

import com.example.carlos.smartthermostat.jsonmodel.value.Value;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos Flores on 10/7/2017.
 *
 */

public class Values {

    @SerializedName("variableName")
    @Expose
    private String variableName;

    @SerializedName("value")
    @Expose
    private Value value;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Values{" +
                "variableName='" + variableName + '\'' +
                ", value=" + value +
                '}';
    }
}
