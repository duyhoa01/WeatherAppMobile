package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

//import com.example.weatherapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

import com.example.weatherapp.model.CurrentData;
import com.example.weatherapp.model.Daylydata;
import com.example.weatherapp.model.LatLonData;
import com.example.weatherapp.viewmodel.AppDatabase;
import com.example.weatherapp.viewmodel.DaylydataDao;
import com.example.weatherapp.viewmodel.WeatherApi;
import com.example.weatherapp.viewmodel.WeatherApiService;

import java.util.ArrayList;
import java.util.List;

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
    private String nameCity="HaNoi";
    private String key="7b0df47e7b9398060bba4ba9fb314856";
    public String lat;
    public String lon;
    private AppDatabase appDatabase;
    private DaylydataDao itemDAO;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.getInstance(this);
        itemDAO = appDatabase.contactDao();

        dataApiService = new WeatherApiService();

        dataApiService.getLatLon(nameCity,key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LatLonData>() {
                    @Override
                    public void onSuccess(@NonNull LatLonData latLonData) {
                        System.out.println("lat: "+ latLonData.getCoord().getLat()+ " long: "+latLonData.getCoord().getLon());
                        lat=latLonData.getCoord().getLat();
                        lon=latLonData.getCoord().getLon();
                        dataApiService.getDataOfDay(lat,lon,"minutely",key)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableSingleObserver<Daylydata>() {
                                    @Override
                                    public void onSuccess(@NonNull Daylydata daylydata) {
                                        AsyncTask.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                itemDAO.nukeTable();
                                                itemDAO.Insert(daylydata);
                                                List<Daylydata> daylydata1=itemDAO.getDogs();
                                                for(Daylydata d :daylydata1){
                                                    System.out.println("Main: "+d.getCurrent().getWeather().get(0).getMain());
                                                }
                                            }
                                        });


                                        //System.out.println("daily: "+ daylydata.getLat());
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        System.out.println("err   "  +e.getMessage());
                                    }
                                });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("ko co thanh pho: "+e.getMessage());
                    }
                });

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//
////              itemDAO.Insert(daylydata);
//                List<Daylydata> daylydata1=itemDAO.getDogs();
//                for(Daylydata d :daylydata1){
//                    System.out.println("Main: "+d.getDaily().get(0).getWeather().get(0).getMain());
//                }
//            }
//        });

    }

}