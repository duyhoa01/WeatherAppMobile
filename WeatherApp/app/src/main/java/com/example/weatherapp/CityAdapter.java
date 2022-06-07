package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private ArrayList<String> citys;

    public CityAdapter(ArrayList<String> citys) {
        this.citys = citys;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        holder.tvNameCity.setText(citys.get(position));
    }

    @Override
    public int getItemCount() {
        return citys.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNameCity;
        public ImageView ivDailyIcon;
        public TextView tvDailyDescription;
        public TextView tvTemperature;
        public ViewHolder(View view) {
            super(view);

            tvNameCity = view.findViewById(R.id.tvNameCity);
            ivDailyIcon = view.findViewById(R.id.ivDailyIcon);
            tvDailyDescription = view.findViewById(R.id.tvDailyDescription);
            tvTemperature = view.findViewById(R.id.tvTemperature);
        }
    }
}
