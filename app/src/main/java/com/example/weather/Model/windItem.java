package com.example.weather.Model;

import com.google.gson.annotations.SerializedName;

public class windItem {

    @SerializedName("speed")
    private String speed;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
