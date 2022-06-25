package com.example.weatherapp.view;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.databinding.FragmentDetailsBinding;
import com.example.weatherapp.model.DailyConverter;
import com.example.weatherapp.model.Daylydata;
import com.example.weatherapp.model.LatLonData;
import com.example.weatherapp.viewmodel.AppDatabase;
import com.example.weatherapp.viewmodel.DaylydataDao;
import com.example.weatherapp.viewmodel.WeatherApiService;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class DetailsFragment extends Fragment {
    private Daylydata dailydata;
    private FragmentDetailsBinding binding;
//    private WeatherApiService dataApiService;
//    private String nameCity="HaNoi";
//    private String key="7b0df47e7b9398060bba4ba9fb314856";
//    public String lat;
//    public String lon;
    private AppDatabase appDatabase;
    private DaylydataDao itemDAO;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dailydata= (Daylydata) getArguments().getSerializable("dailydata");
        }

        appDatabase = AppDatabase.getInstance(MainActivity.context);
        itemDAO = appDatabase.contactDao();
//
//        dataApiService = new WeatherApiService();
//
//        dataApiService.getLatLon(nameCity, key)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableSingleObserver<LatLonData>() {
//                    @Override
//                    public void onSuccess(@NonNull LatLonData latLonData) {
//                        System.out.println("lat: " + latLonData.getCoord().getLat() + " long: " + latLonData.getCoord().getLon());
//                        lat = latLonData.getCoord().getLat();
//                        lon = latLonData.getCoord().getLon();
//                        dataApiService.getDataOfDay(lat, lon, "minutely", key)
//                                .subscribeOn(Schedulers.newThread())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribeWith(new DisposableSingleObserver<Daylydata>() {
//                                    @Override
//                                    public void onSuccess(@NonNull Daylydata daylydata) {
//                                        AsyncTask.execute(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                itemDAO.nukeTable();
//                                                itemDAO.Insert(daylydata);
//                                                List<Daylydata> daylydata1 = itemDAO.getDogs();
//                                                for (Daylydata d : daylydata1) {
//                                                    System.out.println("Main: " + d.getCurrent().getWeather().get(0).getMain());
//                                                    dailydata=d;
//                                                    onCreateView();
//                                                }
//                                            }
//                                        });
//                                    }
//
//                                    @Override
//                                    public void onError(@NonNull Throwable e) {
//                                        System.out.println("err   " + e.getMessage());
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        System.out.println("ko co thanh pho: " + e.getMessage());
//                    }
//                });
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                itemDAO.nukeTable();
//                itemDAO.getDogs();
                List<Daylydata> daylydata1 = itemDAO.getDogs();
                for (Daylydata d : daylydata1) {
                    System.out.println("Main: " + d.getCurrent().getWeather().get(0).getMain());
//                    dailydata=d;
                    System.out.println(d.getCurrent());
//                    System.out.println(dailydata);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_details, null, false);
        View viewRoot = binding.getRoot();
        System.out.println("daily"+dailydata);
        binding.setDaylydata(dailydata);
        return viewRoot;
    }
}