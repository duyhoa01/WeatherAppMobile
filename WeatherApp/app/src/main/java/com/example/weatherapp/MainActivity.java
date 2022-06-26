package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;

//import com.example.weatherapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.model.CityBreed;
import com.example.weatherapp.model.CurrentData;
import com.example.weatherapp.model.Daylydata;
import com.example.weatherapp.model.LatLonData;
import com.example.weatherapp.viewmodel.AppDatabase;
import com.example.weatherapp.viewmodel.DaylydataDao;
import com.example.weatherapp.viewmodel.WeatherApi;
import com.example.weatherapp.viewmodel.WeatherApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    Button btnDelete;
    private ActivityMainBinding binding;
    private CityAdapter cityAdapter;

    private ArrayAdapter<String> arrayAdapter;

    private List<CityBreed> cityBreedList;

    private ArrayList<String> TPlist = new ArrayList<String>();
    private ArrayList<String> listTP = new ArrayList<String>();
    private WeatherApiService dataApiService;
    //private String nameCity="HaNoi";
    private String key="7b0df47e7b9398060bba4ba9fb314856";
    public String lat;
    public String lon;
    private AppDatabase appDatabase;
    private DaylydataDao itemDAO;
    private Context context;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.acction_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    private void addControls() {
        TPlist.addAll(Arrays.asList(getResources().getStringArray(R.array.arrTP)));
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        addControls();

        context=this;

        dataApiService = new WeatherApiService();

        cityBreedList = new ArrayList<CityBreed>();
        cityAdapter = new CityAdapter(cityBreedList);
        binding.rvCitys.setAdapter(cityAdapter);
        listTP.add("DaNang");
        System.out.println("SL: "+ listTP.size());

        //Load database


        //Xoa
        btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Hien thi
        for(int i = 0; i < listTP.size(); i++){
            String nameCity = listTP.get(i);
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
                                            float tC = Float.parseFloat(daylydata.getCurrent().getTemp()) - 273;
                                            String status = daylydata.getCurrent().getWeather().get(0).getMain();
                                            System.out.println("TP: "+ nameCity);
                                            System.out.println("Trạng thái :" + status);
                                            cityBreedList.add(new CityBreed(1, nameCity, status, String.valueOf(tC)));
                                            binding.rvCitys.setAdapter(cityAdapter);
                                        }

                                        @Override
                                        public void onError(@NonNull Throwable e) {
                                            System.out.println("Error   "  +e.getMessage());
                                        }
                                    });
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            System.out.println("Không có thành phố : "+e.getMessage());
                        }
                    });
        }

        binding.rvCitys.setLayoutManager(new LinearLayoutManager(context));
    }
}