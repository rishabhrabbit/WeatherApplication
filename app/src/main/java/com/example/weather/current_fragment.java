package com.example.weather;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.weather.response.main;
import com.example.weather.response.wind;
import com.example.weather.retrofit.ApiRequest;
import com.example.weather.retrofit.RetrofitRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class current_fragment extends Fragment implements View.OnClickListener {
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    public double latt, lonn;
    View view;
    private TextView lat, lon, temp1, windSpeed, humidity, username;
    private Button btn;
    private ProgressBar prb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



//        SharedPreferences preferences = this.getActivity().getSharedPreferences(sfName.sharedPreferencename, Context.MODE_PRIVATE);
//        String myusername = preferences.getString("name", "User");
//        username.setText(myusername);

        view = inflater.inflate(R.layout.current, container, false);
        //lat = (TextView)view.findViewById(R.id.locationlat);
        //lon = (TextView) view.findViewById(R.id.locationlon);
        windSpeed = (TextView) view.findViewById(R.id.wind_s_current);
        humidity = (TextView) view.findViewById(R.id.humidity_c);


        temp1 = (TextView) view.findViewById(R.id.temperature1);
        btn = (Button) view.findViewById(R.id.button);
        prb = (ProgressBar) view.findViewById(R.id.pb);
        btn.setOnClickListener(this);


        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("location_fragment", "OnRequestPermission");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else Toast.makeText(getActivity(), "Permission Denied!", Toast.LENGTH_SHORT);
        }
    }

    private void getCurrentLocation() {
        Log.d("location_fragment", "GetCurrentLocationFunction");
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(getActivity())
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(getActivity())
                                .removeLocationUpdates(this);
                        if (locationRequest != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            latt = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            lonn = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            //lat.setText(String.format("Latitude : %s\n",latt));
                            //lon.setText(String.format("Latitude : %s\n",lonn));
                        }
                    }
                }, Looper.getMainLooper());
    }

    @Override
    public void onClick(View v) {
        Log.d("location_fragment", "onClicked");

        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
            Log.d("location_fragment", "Click");
        } else {
            Log.d("location_fragment", "GetCurrentLocation");
            getCurrentLocation();
        }

        getWeatherDatall();
    }

    public void getWeatherDatall() {
        prb.setVisibility(View.VISIBLE);
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        Call<main> weatherResponseCall = apiRequest.getWeatherDatall(latt, lonn);
        Call<wind> windCall = apiRequest.getWeatherDatalli(latt, lonn);

        weatherResponseCall.enqueue(new Callback<main>() {
            @Override
            public void onResponse(Call<main> call, Response<main> response) {

                if (!response.isSuccessful()) {
                    //temp1.setText("Please enter correct city.");

                } else {
                    temp1.setText(response.body().getMain().getTemp() + "°C");
                    humidity.setText("Humidity : " + response.body().getMain().getHumidity());
                    prb.setVisibility(View.GONE);
                    temp1.setVisibility(View.VISIBLE);
                    humidity.setVisibility(View.VISIBLE);
                }
                //temp.setText("Hi");

            }

            @Override
            public void onFailure(Call<main> call, Throwable t) {
                //throw "";
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

/*
    public void getWeatherData(String name) {
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        Call<main> weatherResponseCall = apiRequest.getWeatherData(name);
        weatherResponseCall.enqueue(new Callback<main>() {
            @Override
            public void onResponse(Call<main> call, Response<main> response) {

                temp1.setText(response.body().getMain().getTemp()+"°C");
                //temp.setText("Hi");
            }

            @Override
            public void onFailure(Call<main> call, Throwable t) {
                temp1.setText("Bye");
            }
        });
    }*/
}
