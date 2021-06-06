package com.example.weather.retrofit;

import com.example.weather.Model.WeatherData;

import retrofit2.Response;

public interface WeatherInterface {

    void currentLocationWeatherInfo(Response<WeatherData> response);
}
