package com.example.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weather.response.main;
import com.example.weather.response.weather;
import com.example.weather.response.wind;
import com.example.weather.retrofit.ApiRequest;
import com.example.weather.retrofit.RetrofitRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class city_fragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    View view;
    private TextView temp, windSpeed, humidity, username, feel, min, max;
    private EditText txt;
    private Button chk;
    private final ArrayList<weather> weathers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.city, container, false);
        username = (TextView) view.findViewById(R.id.user);

        temp = (TextView) view.findViewById(R.id.temperature);
        windSpeed = (TextView) view.findViewById(R.id.wind_s_city);
        humidity = (TextView) view.findViewById(R.id.humidity);
        feel = (TextView) view.findViewById(R.id.feels_like_city);
        min = (TextView) view.findViewById(R.id.min_temp_city);
        max = (TextView) view.findViewById(R.id.max_temp_city);
        txt = (EditText) view.findViewById(R.id.eT);
        chk = (Button) view.findViewById(R.id.chk);

        SharedPreferences preferences = this.getActivity().
                getSharedPreferences(sfName.sharedPreferencename, Context.MODE_PRIVATE);
        String myUsername = preferences.getString("name", "User");
        username.setText(myUsername);


        chk.setOnClickListener(this);
        Spinner spinner = (Spinner) view.findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        Log.d("date_fragment", "onCreateView");
        return view;
    }

    public void getWeatherData(String name) {
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        Call<main> mainCall = apiRequest.getWeatherData(name);
        Call<wind> windCall = apiRequest.getWeatherDataWi(name);
        mainCall.enqueue(new Callback<main>() {
            @Override
            public void onResponse(Call<main> call, Response<main> response) {

                temp.setText(response.body().getMain().getTemp() + "°C");
                humidity.setText("Humidity : " + response.body().getMain().getHumidity());
                feel.setText("Humidity : " + response.body().getMain().getFeels_like());
                min.setText("Humidity : " + response.body().getMain().getTemp_min());
                max.setText("Humidity : " + response.body().getMain().getTemp_max());

            }

            @Override
            public void onFailure(Call<main> call, Throwable t) {
                temp.setText("Error 404");
            }
        });
        windCall.enqueue(new Callback<wind>() {
                             @Override
                             public void onResponse(Call<wind> call, Response<wind> response) {
                                 windSpeed.setText("Wind Speed : " + response.body().getWind().getSpeed());
                             }

                             @Override
                             public void onFailure(Call<wind> call, Throwable t) {

                             }
                         }
        );

    }

    public void getWeatherDataWw(String name) {
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        Call<List<weather>> weatherCall = apiRequest.getWeatherDataW(name);
        weatherCall.enqueue(new Callback<List<weather>>() {
            @Override
            public void onResponse(Call<List<weather>> call, Response<List<weather>> response) {
                List<weather> weathers = (List<weather>) response.body();

            }

            @Override
            public void onFailure(Call<List<weather>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        Log.d("date_fragment", "onClick");
        getWeatherData(txt.getText().toString());
        //getWeatherDataW(txt.getText().toString());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        String spinnerLabel = parent.getItemAtPosition(i).toString();
        getWeatherData(spinnerLabel);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
