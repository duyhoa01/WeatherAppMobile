package com.example.weatherapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.weatherapp.model.CityBreed;

@Database(entities = {CityBreed.class}, version = 1)
public abstract class CityDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "city.db";
    private static CityDatabase instance;

    public static synchronized CityDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CityDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
//        if(instance==null){
//            instance = Room.databaseBuilder(context,
//                    CityDatabase.class, "WeatherApp").build();
//        }
//        return  instance;
    }

    public abstract CityDAO cityDAO();
}
