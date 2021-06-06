package com.example.weather.response;

import com.example.weather.Model.weatherItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class weather {

    @SerializedName("weather")
    private List<weatherItem> weather;

    public weather(List<weatherItem> weather) {
        this.weather = weather;
    }

    public List<weatherItem> getWeather() {
        return weather;
    }

    public void setWeather(List<weatherItem> weather) {
        this.weather = weather;
    }
}
