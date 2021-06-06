package com.example.weather.Model;

import com.google.gson.annotations.SerializedName;

public class weatherItem {


    @SerializedName("main")
    private String mainT;

    public weatherItem(String mainT) {
        this.mainT = mainT;
    }

    public String getMainT() {
        return mainT;
    }

    public void setMainT(String mainT) {
        this.mainT = mainT;
    }

    @Override
    public String toString() {
        return mainT;
    }
}

