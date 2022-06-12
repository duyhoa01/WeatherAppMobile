package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.weatherapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

import com.example.weatherapp.model.CurrentData;
import com.example.weatherapp.model.Daylydata;
import com.example.weatherapp.model.LatLonData;
import com.example.weatherapp.viewmodel.WeatherApi;
import com.example.weatherapp.viewmodel.WeatherApiService;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

//<<<<<<< HEAD
//    private ActivityMainBinding binding;
//    private ArrayList<String> citylist;
//    private CityAdapter cityAdapter;
//=======
    private WeatherApiService dataApiService;
    private String nameCity="Ha Noi";
    private String key="7b0df47e7b9398060bba4ba9fb314856";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataApiService = new WeatherApiService();
        dataApiService.getDataofday("33","-94","current,daily","7b0df47e7b9398060bba4ba9fb314856")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Daylydata>() {
                    @Override
                    public void onSuccess(@NonNull Daylydata daylydata) {


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("err   "  +e.getMessage());
                    }
                });

//        dataApiService.getData(nameCity,key)
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeWith(new DisposableSingleObserver<CurrentData>() {
//                        @Override
//                        public void onSuccess(@NonNull CurrentData currentData) {
//                            System.out.println("visibility: "+currentData.getVisibility());
//                            System.out.println("main: "+currentData.getMain());
//                            System.out.println("weather: "+currentData.getWeather().get(0));
//                        }
//
//                        @Override
//                        public void onError(@NonNull Throwable e) {
//                            System.out.println("ko co thanh pho:"+e.getMessage());
//                        }
//                    });

            dataApiService.getLatLon(nameCity,key)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<LatLonData>() {
                        @Override
                        public void onSuccess(@NonNull LatLonData latLonData) {
                            System.out.println("lat: "+ latLonData.getCoord().getLat()+ " long: "+latLonData.getCoord().getLon());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            System.out.println("ko co thanh pho: "+e.getMessage());
                        }
                    });
        }
}