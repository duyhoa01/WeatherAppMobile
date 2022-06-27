package com.example.weatherapp.viewmodel;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.Daylydata;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder>{

    private List<Daylydata.Daily> dailys;
    private static int state=0;

    public DailyAdapter(ArrayList<Daylydata.Daily> dailys) {
        this.dailys = dailys;
    }

    @NonNull
    @Override
    public DailyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_item, parent, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DailyAdapter.ViewHolder holder, int position) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final long unixTime = Long.parseLong(dailys.get(position).getDt());
        final String formattedDtm = Instant.ofEpochSecond(unixTime)
                .atZone(ZoneId.of("GMT+7"))
                .format(formatter);
        holder.tvDailyTemp.setText(dailys.get(position).getTemp().getDay());
        holder.tvDailyDescription.setText(dailys.get(position).getWeather().get(0).getDescription());
        holder.tvDailyWeekDay.setText(formattedDtm);
        String icon = dailys.get(position).getWeather().get(0).getIcon().substring(0,2);
        switch (icon){
            case "01":
                holder.ivDailyIcon.setImageResource(R.drawable._01d);
                break;
            case "02":
                holder.ivDailyIcon.setImageResource(R.drawable._02d);
                break;
            case "03":
                holder.ivDailyIcon.setImageResource(R.drawable._03d);
                break;
            case "04":
                holder.ivDailyIcon.setImageResource(R.drawable._04d);
                break;
            case "09":
                holder.ivDailyIcon.setImageResource(R.drawable._09d);
                break;
            case "10":
                holder.ivDailyIcon.setImageResource(R.drawable._10d);
                break;
            case "11":
                holder.ivDailyIcon.setImageResource(R.drawable._11d);
                break;
            case "13":
                holder.ivDailyIcon.setImageResource(R.drawable._13d);
                break;
            case "50":
                holder.ivDailyIcon.setImageResource(R.drawable._50d);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dailys.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDailyWeekDay;
        public TextView tvDailyDescription;
        public TextView tvDailyTemp;
        public ImageView ivDailyIcon;

        public ViewHolder(View view) {
            super(view);
            tvDailyTemp = view.findViewById(R.id.tvDailyTemp);
            tvDailyDescription = view.findViewById(R.id.tvDailyDescription);
            tvDailyWeekDay = view.findViewById(R.id.tvDailyWeekDay);
            ivDailyIcon = view.findViewById(R.id.ivDailyIcon);

        }

    }
}

