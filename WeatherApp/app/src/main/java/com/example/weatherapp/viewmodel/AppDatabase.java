package com.example.weatherapp.viewmodel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.weatherapp.model.Daylydata;

@Database(entities = {Daylydata.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaylydataDao contactDao();
    private  static  AppDatabase instance;
    public   static  AppDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "WeatherApp").build();
        }
        return  instance;
    }

}
