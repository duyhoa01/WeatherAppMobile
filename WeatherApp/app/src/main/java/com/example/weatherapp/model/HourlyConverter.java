package com.example.weatherapp.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class HourlyConverter {
    @TypeConverter
    public static List<Daylydata.Hourly> toHourly(String string) {
        Gson gson=new Gson();
        TypeToken<List<Daylydata.Hourly>> listType = new TypeToken<List<Daylydata.Hourly>>(){};
        return gson.fromJson(string,listType.getType());
    }


    @TypeConverter
    public static String toTimestamp(List<Daylydata.Hourly> hourlies) {
        Gson gson=new Gson();
        return gson.toJson(hourlies);
    }
}
