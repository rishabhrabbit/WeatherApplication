package com.example.weather.response;

import com.example.weather.Model.windItem;
import com.google.gson.annotations.SerializedName;

public class wind {
    @SerializedName("wind")
    private windItem wind;

    public windItem getWind() {
        return wind;
    }

    public void setWind(windItem wind) {
        this.wind = wind;
    }
}

