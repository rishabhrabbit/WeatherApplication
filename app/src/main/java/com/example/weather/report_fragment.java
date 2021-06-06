package com.example.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Adapter.sevenDaysWeatherAdapter;
import com.example.weather.Model.Daily;
import com.example.weather.Model.WeatherData;
import com.example.weather.repository.WeatherRepository;
import com.example.weather.retrofit.WeatherInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class report_fragment extends Fragment {
    List<Daily> dailyWeatherList = new ArrayList<>();
    RecyclerView recyclerView;

    private TextView username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.report_7days, container, false);


        return inflater.inflate(R.layout.report_7days, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = this.getActivity().getSharedPreferences(sfName.sharedPreferencename, Context.MODE_PRIVATE);
        String myusername = preferences.getString("username", "NO USERNAME");
        username.setText(myusername);
        Initialization(view);
        getData("19.0144", "72.8479", "metric");
    }

    private void getData(String lat, String lon, String unit) {
        WeatherRepository.getCurrentLocationWeatherData(lat, lon, unit, new WeatherInterface() {
            @Override
            public void currentLocationWeatherInfo(Response<WeatherData> response) {
                WeatherData weatherData = response.body();
                setData(weatherData);
            }


        });

    }

    private void setData(WeatherData weatherData) {
        dailyWeatherList = weatherData.getDaily();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new sevenDaysWeatherAdapter(dailyWeatherList, getContext()));
    }

    private void Initialization(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);

    }

}
