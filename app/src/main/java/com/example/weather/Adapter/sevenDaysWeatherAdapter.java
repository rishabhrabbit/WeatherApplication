package com.example.weather.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Model.Daily;
import com.example.weather.R;
import com.example.weather.sfName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class sevenDaysWeatherAdapter extends RecyclerView.Adapter<sevenDaysWeatherAdapter.ViewHolder> {
    List<Daily> sevenDayModelArrayList = new ArrayList<>();
    Context context;

    public sevenDaysWeatherAdapter(List<Daily> sevenDayModelArrayList, Context context) {
        this.sevenDayModelArrayList = sevenDayModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public sevenDaysWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.seven_day_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull sevenDaysWeatherAdapter.ViewHolder holder, int position) {

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        String temp = String.valueOf(sevenDayModelArrayList.get(position).getTemp().getDay());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(tz);

        /* print your timestamp and double check it's the date you expect */
        String localTime = sdf.format(new Date(sevenDayModelArrayList.get(position).getDt() * 1000)); // I assume your timestamp is in seconds and you're converting to milliseconds?
//        String dateString = sevenDayModelArrayList.get(position).getDt();
        holder.date.setText(localTime);
        SharedPreferences sharedPreferences = context.getSharedPreferences(sfName.sharedPreferencename, Context.MODE_PRIVATE);
        String unit = sharedPreferences.getString("unit", "metric");
        if (unit.equals("metric")) {
            holder.temperature.setText(sevenDayModelArrayList.get(position).getTemp().getDay() + " \u2103");
            holder.wind_speed.setText(sevenDayModelArrayList.get(position).getWindSpeed() + " m/s");
        } else {
            holder.temperature.setText(sevenDayModelArrayList.get(position).getTemp().getDay() + " \u2109");
            holder.wind_speed.setText(sevenDayModelArrayList.get(position).getWindSpeed() + " m/h");
        }

        holder.humidity.setText(sevenDayModelArrayList.get(position).getHumidity() + " %");
    }


    @Override
    public int getItemCount() {
        return sevenDayModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView temperature, humidity, date, wind_speed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temperature = itemView.findViewById(R.id.card_temp);
            humidity = itemView.findViewById(R.id.card_humidity);
            date = itemView.findViewById(R.id.card_date);
            wind_speed = itemView.findViewById(R.id.card_windspeed);
        }
    }
}