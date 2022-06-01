package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentData {

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("base")
    private String base;

    @SerializedName("main")
    private Wmain main;

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("clouds")
    private Clouds clouds;

    @SerializedName("dt")
    private String dt;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("timezone")
    private String timeZone;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private String cod;




    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Wmain getMain() {
        return main;
    }

    public void setMain(Wmain main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }


    public class Weather{
        @SerializedName("id")
        private String id;

        @SerializedName("main")
        private String main;

        @SerializedName("description")
        private String discription;

        @SerializedName("icon")
        private String icon;

        @Override
        public String toString() {
            return "Weather{" +
                    "id='" + id + '\'' +
                    ", main='" + main + '\'' +
                    ", discription='" + discription + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public class Coord{
        @SerializedName("lon")
        private String lon;

        @SerializedName("lat")
        private String lat;

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }

    public class Wmain{

        @SerializedName("temp")
        private String temp;

        @SerializedName("feels_like")
        private String feelsLike;

        @SerializedName("temp_min")
        private String tempMin;

        @SerializedName("temp_max")
        private String tempMax;

        @SerializedName("pressure")
        private String pressure;

        @SerializedName("humidity")
        private String humidity;

        @SerializedName("sea_level")
        private String seaLevel;

        @SerializedName("grnd_level")
        private String grndLevel;


        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(String feelsLike) {
            this.feelsLike = feelsLike;
        }

        public String getTempMin() {
            return tempMin;
        }

        public void setTempMin(String tempMin) {
            this.tempMin = tempMin;
        }

        public String getTempMax() {
            return tempMax;
        }

        public void setTempMax(String tempMax) {
            this.tempMax = tempMax;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getSeaLevel() {
            return seaLevel;
        }

        public void setSeaLevel(String seaLevel) {
            this.seaLevel = seaLevel;
        }

        public String getGrndLevel() {
            return grndLevel;
        }

        public void setGrndLevel(String grndLevel) {
            this.grndLevel = grndLevel;
        }

        @Override
        public String toString() {
            return "Wmain{" +
                    "temp='" + temp + '\'' +
                    ", feelsLike='" + feelsLike + '\'' +
                    ", tempMin='" + tempMin + '\'' +
                    ", tempMax='" + tempMax + '\'' +
                    ", pressure='" + pressure + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", seaLevel='" + seaLevel + '\'' +
                    ", grndLevel='" + grndLevel + '\'' +
                    '}';
        }
    }

    public class Wind{
        @SerializedName("speed")
        private String speed;

        @SerializedName("deg")
        private String deg;

        @SerializedName("gust")
        private String gust;

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getGust() {
            return gust;
        }

        public void setGust(String gust) {
            this.gust = gust;
        }
    }

    public class Clouds{
        @SerializedName("all")
        private String all;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }

    public class Sys{
        @SerializedName("type")
        private String type;

        @SerializedName("id")
        private String id;

        @SerializedName("country")
        private String country;

        @SerializedName("sunrise")
        private String sunrise;

        @SerializedName("sunset")
        private String sunset;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }
    }

}



