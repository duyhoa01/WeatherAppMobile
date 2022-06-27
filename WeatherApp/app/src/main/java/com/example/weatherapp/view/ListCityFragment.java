package com.example.weatherapp.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;

import com.example.weatherapp.CityAdapter;
import com.example.weatherapp.Database.CityDatabase;
import com.example.weatherapp.R;
//import com.example.weatherapp.databinding.ActivityMainBinding;
//import com.example.weatherapp.databinding.FragmentDetailsBinding;
//import com.example.weatherapp.databinding.FragmentListCityBinding;
import com.example.weatherapp.model.CityBreed;
import com.example.weatherapp.model.Daylydata;
import com.example.weatherapp.model.LatLonData;
import com.example.weatherapp.viewmodel.AppDatabase;
import com.example.weatherapp.viewmodel.DaylydataDao;
import com.example.weatherapp.viewmodel.WeatherApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListCityFragment extends Fragment {
    //private FloatingActionButton btnDelete;
    private ViewDataBinding binding;
    private RecyclerView rvCitys;
    private CityAdapter cityAdapter;
    private ArrayAdapter<String> arrayAdapter;
    private List<CityBreed> cityBreedList;
    private WeatherApiService dataApiService;
    private String key="7b0df47e7b9398060bba4ba9fb314856";
    public String lat;
    public String lon;
    //private Context context;

    @Override
    public void onCreateOptionsMenu(@androidx.annotation.NonNull Menu menu, @androidx.annotation.NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.acction_search);
        android.widget.SearchView searchView = (android.widget.SearchView) menuItem.getActionView();
        //androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.search_dog).getActionView();
        System.out.println("3");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                dataApiService.getLatLon(s,key)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<LatLonData>() {
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull LatLonData latLonData) {
                                System.out.println("lat: "+ latLonData.getCoord().getLat()+ " long: "+latLonData.getCoord().getLon());
                                lat=latLonData.getCoord().getLat();
                                lon=latLonData.getCoord().getLon();
                                dataApiService.getDataOfDay(lat,lon,"minutely",key)
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeWith(new DisposableSingleObserver<Daylydata>() {
                                            @Override
                                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Daylydata daylydata) {
                                                if (CityDatabase.getInstance(getContext()).cityDAO().getCity(s) == null){
                                                    float tC = Float.parseFloat(daylydata.getCurrent().getTemp()) - 273;
                                                    String status = daylydata.getCurrent().getWeather().get(0).getMain();
                                                    System.out.println("TP: "+ s);
                                                    System.out.println("Trạng thái :" + status);
                                                    int id = CityDatabase.getInstance(getContext()).cityDAO().getCount() + 1;
                                                    CityBreed newCB = new CityBreed(s, status, String.valueOf(Math.round(tC)) + "°C");
                                                    CityDatabase.getInstance(getContext()).cityDAO().insertCity(newCB);
                                                    System.out.println("Da them " + s);
                                                    Bundle bundle=new Bundle();
                                                    bundle.putSerializable("namecity",s);
                                                    Navigation.findNavController(getView()).navigate(R.id.detailsFragment,bundle);
                                                    cityBreedList.add(newCB);
                                                    cityAdapter.notifyDataSetChanged();
                                                }
                                                else{
                                                    System.out.println(s + " da ton tai !");
                                                }
                                            }

                                            @Override
                                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                                System.out.println("Error   "  +e.getMessage());
                                            }
                                        });
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                System.out.println("Không có thành phố : "+e.getMessage());
                            }
                        });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println("SEARCH : " + s);
                return false;
            }
        });
    }


    public ListCityFragment() {
        // Required empty public constructor
    }
    public static ListCityFragment newInstance() {
        ListCityFragment fragment = new ListCityFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("2");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_city, container, false);
//        binding = DataBindingUtil.inflate(getLayoutInflater(),
//                R.layout.fragment_list_city, null, false);
//        View viewRoot = binding.getRoot();
//        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cityBreedList = new ArrayList<CityBreed>();
        //btnDelete = view.findViewById(R.id.btn_delete);
        //cityBreedList.add(new CityBreed(1,"DaNang","Rain", "32"));

        cityBreedList = CityDatabase.getInstance(getContext()).cityDAO().getListCity();
        rvCitys = view.findViewById(R.id.rv_citys);
        cityAdapter = new CityAdapter(cityBreedList);
        System.out.println("1");
        dataApiService = new WeatherApiService();

        rvCitys.setAdapter(cityAdapter);
        rvCitys.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}