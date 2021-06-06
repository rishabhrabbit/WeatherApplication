package com.example.weather.retrofit;

import com.example.weather.Model.WeatherData;
import com.example.weather.response.main;
import com.example.weather.response.weather;
import com.example.weather.response.wind;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    String currentLocationBaseUrl = "https://api.openweathermap.org/";

    @GET("weather?appid=e60ca6ecb804babbd7e4e97fb213110e&units=metric")
    Call<main> getWeatherData(@Query("q") String name);

    @GET("weather?appid=e60ca6ecb804babbd7e4e97fb213110e&units=metric")
    Call<List<weather>> getWeatherDataW(@Query("q") String name);

    @GET("weather?appid=e60ca6ecb804babbd7e4e97fb213110e&units=metric")
    Call<wind> getWeatherDataWi(@Query("q") String name);


    @GET("weather?appid=e60ca6ecb804babbd7e4e97fb213110e&units=metric")
    Call<main> getWeatherDatall(@Query("lat") double lat, @Query("lon") double lon);

    @GET("weather?appid=e60ca6ecb804babbd7e4e97fb213110e&units=metric")
    Call<wind> getWeatherDatalli(@Query("lat") double lat, @Query("lon") double lon);

    @GET("/data/2.5/onecall")
    Call<WeatherData> getCurrentLocationWeatherData(@Query("lat") String lat, @Query("lon") String lon,
                                                    @Query("units") String unit,
                                                    @Query("appid") String key);


}
