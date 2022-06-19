package com.example.weatherapp.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DailyConverter {
    @TypeConverter
    public static List<Daylydata.Daily> toDaily(String string) {
        Gson gson=new Gson();
        TypeToken<List<Daylydata.Daily>> listType = new TypeToken<List<Daylydata.Daily>>(){};
        return gson.fromJson(string,listType.getType());
    }


    @TypeConverter
    public static String toTimestamp(List<Daylydata.Daily> dailies) {
        Gson gson=new Gson();
        return gson.toJson(dailies);
    }
}
