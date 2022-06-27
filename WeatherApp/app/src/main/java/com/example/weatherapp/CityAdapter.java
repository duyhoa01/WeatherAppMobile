package com.example.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Database.CityDatabase;
import com.example.weatherapp.model.CityBreed;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private static List<CityBreed> cityBreeds;

    public CityAdapter(List<CityBreed> cityBreeds) {
        this.cityBreeds = cityBreeds;
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
        holder.tvNameCity.setText(cityBreeds.get(position).getName());
        holder.tvDailyDescription.setText(cityBreeds.get(position).getDescription());
        holder.tvTemperature.setText(cityBreeds.get(position).getTemperature());

//        if (cityBreeds.get(position).getDescription().equals("Rain")) {
//            holder.ivDailyIcon.setImageResource(R.drawable.);
//        }
//        else if (cityBreeds.get(position).getDescription().equals("Sunny")) {
//            holder.ivDailyIcon.setImageResource(R.drawable.clear_day_24);
//        }
//        else if (cityBreeds.get(position).getDescription().equals("Clouds")) {
//            holder.ivDailyIcon.setImageResource(R.drawable.cloud);
//        }


        switch (cityBreeds.get(position).getIdIcon().toString()){
            case "01d":
                holder.ivDailyIcon.setImageResource(R.drawable._01d);
                break;
            case "02d":
                holder.ivDailyIcon.setImageResource(R.drawable._02d);
                break;
            case "03d":
                holder.ivDailyIcon.setImageResource(R.drawable._03d);
                break;
            case "04d":
                holder.ivDailyIcon.setImageResource(R.drawable._04d);
                break;
            case "09d":
                holder.ivDailyIcon.setImageResource(R.drawable._09d);
                break;
            case "10d":
                holder.ivDailyIcon.setImageResource(R.drawable._10d);
                break;
            case "11d":
                holder.ivDailyIcon.setImageResource(R.drawable._11d);
                break;
            case "13d":
                holder.ivDailyIcon.setImageResource(R.drawable._13d);
                break;
            case "50d":
                holder.ivDailyIcon.setImageResource(R.drawable._50d);
                break;
        }

        CityBreed a = new CityBreed(cityBreeds.get(position).getName(),cityBreeds.get(position).getDescription(),cityBreeds.get(position).getTemperature()
        , cityBreeds.get(position).getLat(), cityBreeds.get(position).getLon(), cityBreeds.get(position).getIdIcon());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("test xoa " + holder.tvNameCity.getText());
                CityDatabase.getInstance(view.getContext()).cityDAO().deleteCityByName(holder.tvNameCity.getText().toString());
                cityBreeds.remove(position);
                //noti
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cityBreeds.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityBreeds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //FloatingActionButton
        public FloatingActionButton btnDelete;
        public TextView tvNameCity;
        public ImageView ivDailyIcon;
        public TextView tvDailyDescription;
        public TextView tvTemperature;
        public ViewHolder(View view) {
            super(view);

            btnDelete = view.findViewById(R.id.btn_delete);
            tvNameCity = view.findViewById(R.id.tvNameCity);
            ivDailyIcon = view.findViewById(R.id.ivDailyIcon);
            tvDailyDescription = view.findViewById(R.id.tvDailyDescription);
            tvTemperature = view.findViewById(R.id.tvTemperature);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CityBreed citys = cityBreeds.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("keyword",citys.getName()+"-"+citys.getLat()+"-"+citys.getLon());
                    Navigation.findNavController(view).navigate(R.id.detailsFragment,bundle);
                }
            });
        }
    }
}
