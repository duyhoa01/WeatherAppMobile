package com.example.weatherapp.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weatherapp.model.CityBreed;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CityDAO {

    @Query("SELECT * FROM CityBreed")
    public List<CityBreed> getListCity();

    @Insert
    void insertCity(CityBreed cityBreed);

    @Query("DELETE FROM CityBreed WHERE id = :id")
    public void deleteCity(int id);

    @Query("DELETE FROM CityBreed")
    public void Clear();

    @Query("SELECT count(*) FROM CityBreed")
    public int getCount();

    @Query("SELECT * FROM CityBreed WHERE name = :name")
    public CityBreed getCity(String name);

    @Query("DELETE FROM CityBreed WHERE name = :name")
    public void deleteCityByName(String name);
}
