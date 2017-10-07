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
}
