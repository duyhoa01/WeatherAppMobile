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
        holder.tvHourlyTemp.setText(hourlies.get(position).getTemp());
//        holder.tvDailyDescription.setText(hourlies.get(position).getWeather().get(0).getDescription());
        holder.tvHourlyTime.setText(formattedDtm);
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

