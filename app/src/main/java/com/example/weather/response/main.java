package com.example.weather.response;

import com.example.weather.Model.mainItem;
import com.google.gson.annotations.SerializedName;

public class main {

    @SerializedName("main")
    private mainItem main;

    public mainItem getMain() {
        return main;
    }

    public void setMain(mainItem main) {
        this.main = main;
    }


}
