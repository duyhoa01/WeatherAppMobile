package com.example.weatherapp.viewmodel;

import com.example.weatherapp.model.CurrentData;
import com.example.weatherapp.model.Daylydata;
import com.example.weatherapp.model.LatLonData;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiService {
    private static final String BASE_URL="https://api.openweathermap.org";
    private WeatherApi api;

    public  WeatherApiService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(WeatherApi.class);
    }

//   public Single<CurrentData> getData(){
//        return  api.getData();
    public Single<CurrentData> getData(String namecity, String key){
        return  api.getData(namecity,key);
    }

    public Single<LatLonData> getLatLon(String namecity, String key){
        return  api.getLatLon(namecity,key);
    }
    public  Single <Daylydata> getDataofday(String lat, String lon, String exclude, String key){return  api.getDataofday(lat,lon,exclude,key);}
}
