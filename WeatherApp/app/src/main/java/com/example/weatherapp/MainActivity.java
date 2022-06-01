package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weatherapp.model.CurrentData;
import com.example.weatherapp.viewmodel.WeatherApi;
import com.example.weatherapp.viewmodel.WeatherApiService;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private WeatherApiService dataApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataApiService = new WeatherApiService();
        dataApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CurrentData>() {
                    @Override
                    public void onSuccess(@NonNull CurrentData currentData) {
                        System.out.println("visibility: "+currentData.getVisibility());
                        System.out.println("main: "+currentData.getMain());
                        System.out.println("weather: "+currentData.getWeather().get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("Visibility"+e.getMessage());
                    }
                });
    }
}