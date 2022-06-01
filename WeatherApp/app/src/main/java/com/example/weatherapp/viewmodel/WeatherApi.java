package com.example.weatherapp.viewmodel;

import com.example.weatherapp.model.CurrentData;
import com.example.weatherapp.model.LatLonData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/weather")
    public Single<CurrentData> getData(@Query("q") String name, @Query("appid") String key);

    @GET("data/2.5/weather")
    public Single<LatLonData> getLatLon(@Query("q") String name, @Query("appid") String key);
}
