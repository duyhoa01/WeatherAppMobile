package com.example.weatherapp.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class WeatherConverter {
    @TypeConverter
    public static List<Daylydata.Weather> toWeather(String string) {
        Gson gson=new Gson();
        TypeToken<List<Daylydata.Weather>> listType = new TypeToken<List<Daylydata.Weather>>(){};
        return gson.fromJson(string,listType.getType());
    }


    @TypeConverter
    public static String toTimestamp(List<Daylydata.Weather> weathers) {
        Gson gson=new Gson();
        return gson.toJson(weathers);
    }
}
