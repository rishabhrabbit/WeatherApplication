package com.example.weather.repository;

import android.util.Log;

import com.example.weather.Model.WeatherData;
import com.example.weather.retrofit.ApiRequest;
import com.example.weather.retrofit.RetrofitRequest;
import com.example.weather.retrofit.WeatherInterface;
import com.example.weather.sfName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    public static void getCurrentLocationWeatherData(String lat, String lon,
                                                     String unit, WeatherInterface weatherInterface) {
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance1(ApiRequest.currentLocationBaseUrl)
                .create(ApiRequest.class);
        Call<WeatherData> call = apiRequest.getCurrentLocationWeatherData(lat, lon, unit, sfName.apiKey);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                weatherInterface.currentLocationWeatherInfo(response);
                Log.v("Res", response.body().getDaily().toString());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.v("FAILURE", t.getMessage());
            }
        });
    }

}
