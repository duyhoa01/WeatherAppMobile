package com.example.weatherapp.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.weatherapp.viewmodel.DailyAdapter;
import com.example.weatherapp.viewmodel.DaylydataDao;
import com.example.weatherapp.viewmodel.HourlyAdapter;
import com.example.weatherapp.viewmodel.WeatherApiService;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class DetailsFragment extends Fragment {
    private Daylydata dailydata;
    private FragmentDetailsBinding binding;
    private WeatherApiService dataApiService;
    private String nameCity="HaNoi";
    private String key="7b0df47e7b9398060bba4ba9fb314856";
    public String lat;
    public String lon;
    private DailyAdapter dailyAdapter;
    private HourlyAdapter hourlyAdapter;
    private RecyclerView rvDailys;
    private RecyclerView rvHourlys;
    private AppDatabase appDatabase;
    private DaylydataDao itemDAO;
    private ArrayList<Daylydata.Daily> dailys;
    private ArrayList<Daylydata.Hourly> hourlys;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameCity= (String) getArguments().getSerializable("namecity");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvDailys = view.findViewById(R.id.rvDaily);
        rvHourlys = view.findViewById(R.id.rvHourly);
        dailys = new ArrayList<Daylydata.Daily>();
        hourlys = new ArrayList<Daylydata.Hourly>();
        dailyAdapter =new DailyAdapter(dailys);
        hourlyAdapter =new HourlyAdapter(hourlys);
        rvDailys.setAdapter(dailyAdapter);
        rvHourlys.setAdapter(hourlyAdapter);
        rvDailys.setLayoutManager(new GridLayoutManager(getContext(),1));
        LinearLayoutManager ln = new LinearLayoutManager(getActivity());
        ln.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHourlys.setLayoutManager(ln);
        appDatabase = AppDatabase.getInstance(getContext());
        itemDAO = appDatabase.contactDao();

        dataApiService = new WeatherApiService();

        dataApiService.getLatLon(nameCity, key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LatLonData>() {
                    @Override
                    public void onSuccess(@NonNull LatLonData latLonData) {
                        System.out.println("lat: " + latLonData.getCoord().getLat() + " long: " + latLonData.getCoord().getLon());
                        lat = latLonData.getCoord().getLat();
                        lon = latLonData.getCoord().getLon();
                        dataApiService.getDataOfDay(lat, lon, "minutely", key)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableSingleObserver<Daylydata>() {
                                    @Override
                                    public void onSuccess(@NonNull Daylydata daylydata) {
                                        AsyncTask.execute(new Runnable() {
                                            @RequiresApi(api = Build.VERSION_CODES.O)
                                            @Override
                                            public void run() {
                                                itemDAO.nukeTable();
                                                Daylydata.Current c = daylydata.getCurrent();
                                                c.setTemp(Math.round(Float.parseFloat(daylydata.getCurrent().getTemp())-273)+"");

                                                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                                final long unixTime = Long.parseLong(daylydata.getCurrent().getDt());
                                                final String formattedDtm = Instant.ofEpochSecond(unixTime)
                                                        .atZone(ZoneId.of("GMT+7"))
                                                        .format(formatter);
                                                c.setDt(formattedDtm);

                                                daylydata.setCurrent(c);
                                                daylydata.setCityName(nameCity);
                                                itemDAO.Insert(daylydata);
                                                List<Daylydata> daylydata1 = itemDAO.getDogs();
                                                for (Daylydata d : daylydata1) {
                                                    System.out.println("Main: " + d.getCurrent().getWeather().get(0).getMain());
                                                    dailydata=d;
                                                }

                                                binding.setDaylydata(dailydata);
                                                dailys.clear();
                                                getActivity().runOnUiThread(new Runnable() {

                                                    @Override
                                                    public void run() {

                                                        for(Daylydata.Daily d : dailydata.getDaily()){
                                                            d.getTemp().setDay(Math.round(Float.parseFloat(d.getTemp().getDay())-273)+"°C");
                                                            dailys.add(d);
                                                            dailyAdapter.notifyDataSetChanged();
                                                        }
                                                        hourlys.clear();
                                                        for(Daylydata.Hourly d : dailydata.getHourly()){
                                                            d.setTemp(Math.round(Float.parseFloat(d.getTemp())-273)+"");
                                                            hourlys.add(d);
                                                            hourlyAdapter.notifyDataSetChanged();
                                                        }

                                                    }
                                                });

                                            }
                                        });
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        System.out.println("err   " + e.getMessage());
                                    }
                                });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("ko co thanh pho: " + e.getMessage());
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