package com.example.weatherapp.viewmodel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weatherapp.model.Daylydata;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface DaylydataDao {

    @Insert
    public  void inserAll(Daylydata...contact);

    @Insert
    public void Insert(Daylydata...daylydata);

    @Query("SELECT * FROM dayly_data")
    public List<Daylydata> getDogs();

    @Query("DELETE FROM dayly_data")
    public void nukeTable();

    @Query("SELECT * FROM dayly_data WHERE lat= :lat and lon= :lon")
    public Daylydata getDalyDataOfCity(String lat,String lon);

    @Query("DELETE FROM dayly_data WHERE lat= :lat and lon= :lon")
    public void deleteDaylyDataByLatLon(String lat,String lon);


}
