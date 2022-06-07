package com.example.weatherapp.viewmodel;

import com.example.weatherapp.model.CurrentData;
import com.example.weatherapp.model.Daylydata;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface WeatherApi {
    @GET("data/2.5/weather?q=HaNoi&appid=7b0df47e7b9398060bba4ba9fb314856&=")
    public Single<CurrentData> getData();
    @GET("data/2.5/onecall?lat=33.44&lon=-94.04&exclude=current,daily&appid=7b0df47e7b9398060bba4ba9fb314856")
    public  Single<Daylydata> getDataofday();
}
