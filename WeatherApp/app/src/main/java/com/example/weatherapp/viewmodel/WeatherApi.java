package com.example.weatherapp.viewmodel;

import com.example.weatherapp.model.CurrentData;
import com.example.weatherapp.model.Daylydata;
import com.example.weatherapp.model.LatLonData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
//    @GET("data/2.5/weather?q=HaNoi&appid=7b0df47e7b9398060bba4ba9fb314856&=")
//    public Single<CurrentData> getData();
    //lat=33.44&lon=-94.04&exclude=current,daily&appid=7b0df47e7b9398060bba4ba9fb314856
    @GET("data/2.5/onecall")
    public  Single<Daylydata> getDataofday(@Query("lat") String lat, @Query("lon") String lon, @Query("exclude") String exclude, @Query("appid") String key );

    @GET("data/2.5/weather")
    public Single<CurrentData> getData(@Query("q") String name, @Query("appid") String key);

    @GET("data/2.5/weather")
    public Single<LatLonData> getLatLon(@Query("q") String name, @Query("appid") String key);
}
