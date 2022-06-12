package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;
import com.example.weatherapp.model.CurrentData.Coord;

public class LatLonData {

    @SerializedName("coord")
    private Coord coord;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}


