package com.example.weatherapp.viewmodel;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.Daylydata;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder>{

    private List<Daylydata.Hourly> hourlies;
    private static int state=0;

    public HourlyAdapter(ArrayList<Daylydata.Hourly> hourlies) {
        this.hourlies = hourlies;
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_item, parent, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.ViewHolder holder, int position) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:00");
        final long unixTime = Long.parseLong(hourlies.get(position).getDt());
        final String formattedDtm = Instant.ofEpochSecond(unixTime)
                .atZone(ZoneId.of("GMT+7"))
                .format(formatter);
        holder.tvHourlyTime.setText(formattedDtm);
        holder.tvHourlyTemp.setText(hourlies.get(position).getTemp());
        String icon = hourlies.get(position).getWeather().get(0).getIcon().substring(0,2);
        switch (icon){
            case "01":
                holder.ivHourlyIcon.setImageResource(R.drawable._01d);
                break;
            case "02":
                holder.ivHourlyIcon.setImageResource(R.drawable._02d);
                break;
            case "03":
                holder.ivHourlyIcon.setImageResource(R.drawable._03d);
                break;
            case "04":
                holder.ivHourlyIcon.setImageResource(R.drawable._04d);
                break;
            case "09":
                holder.ivHourlyIcon.setImageResource(R.drawable._09d);
                break;
            case "10":
                holder.ivHourlyIcon.setImageResource(R.drawable._10d);
                break;
            case "11":
                holder.ivHourlyIcon.setImageResource(R.drawable._11d);
                break;
            case "13":
                holder.ivHourlyIcon.setImageResource(R.drawable._13d);
                break;
            case "50":
                holder.ivHourlyIcon.setImageResource(R.drawable._50d);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return hourlies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHourlyTime;
        public TextView tvDailyDescription;
        public TextView tvHourlyTemp;
        public ImageView ivHourlyIcon;

        public ViewHolder(View view) {
            super(view);
            tvHourlyTemp = view.findViewById(R.id.tvHourlyTemp);
            tvHourlyTime = view.findViewById(R.id.tvHourlyTime);
            ivHourlyIcon = view.findViewById(R.id.ivHourlyIcon);

        }

    }
}

