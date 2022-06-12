package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daylydata {
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("timezone_offset")
    private String timezone_offset;
    @SerializedName("current")
    private Current current;
    @SerializedName("hourly")
    private List<Hourly> hourly;
    @SerializedName("daily")
    private List<Daily> daily;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(String timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public List<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }
    class  Temp{
        @SerializedName("day")
        private String day;
        @SerializedName("min")
        private  String min;
        @SerializedName("max")
        private  String max;
        @SerializedName("night")
        private  String night;
        @SerializedName("eve")
        private  String eve;
        @SerializedName("morn")
        private  String morn;
    }

    class Feels_like{
        @SerializedName("day")
        private  String day;
        @SerializedName("night")
        private  String night;
        @SerializedName("eve")
        private  String eve;
        @SerializedName("morn")
        private  String morn;
    }

    class Weather{
        @SerializedName("id")
        private  String id;
        @SerializedName("main")
        private  String main;
        @SerializedName("description")
        private  String description;
        @SerializedName("icon")
        private  String icon;
        public String toString() {
            return "Weather{" +
                    "id='" + id + '\'' +
                    ", main='" + main + '\'' +
                    ", discription='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    class Hourly{
        @SerializedName("dt")
        private String dt;
        @SerializedName("temp")
        private String temp;
        @SerializedName("feels_like")
        private String feels_like;
        @SerializedName("pressure")
        private String pressure;
        @SerializedName("humidity")
        private String humidity;
        @SerializedName("dew_point")
        private String dew_point;
        @SerializedName("uvi")
        private String uvi;
        @SerializedName("clouds")
        private String clouds;
        @SerializedName("visibility")
        private String visibility;
        @SerializedName("wind_speed")
        private String wind_speed;
        @SerializedName("wind_deg")
        private String wind_deg;
        @SerializedName("wind_gust")
        private String wind_gust;
        @SerializedName("pop")
        private String pop;
    }
    class Current{
        @SerializedName("dt")
        private String dt;
        @SerializedName("sunrise")
        private String sunrise;
        @SerializedName("sunset")
        private String sunset;
        @SerializedName("temp")
        private String temp;
        @SerializedName("feels_like")
        private String feels_like;
        @SerializedName("pressure")
        private String pressure;
        @SerializedName("humidity")
        private String humidity;
        @SerializedName("dew_point")
        private String dew_point;
        @SerializedName("uvi")
        private String uvi;
        @SerializedName("clouds")
        private String clouds;
        @SerializedName("visibility")
        private String visibility;
        @SerializedName("wind_speed")
        private String wind_speed;
        @SerializedName("wind_deg")
        private String wind_deg;
        @SerializedName("weather")
        private List<Weather> weather;

        public String getDt() {
            return dt;
        }

        public void setDt(String dt) {
            this.dt = dt;
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

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(String feels_like) {
            this.feels_like = feels_like;
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

        public String getDew_point() {
            return dew_point;
        }

        public void setDew_point(String dew_point) {
            this.dew_point = dew_point;
        }

        public String getUvi() {
            return uvi;
        }

        public void setUvi(String uvi) {
            this.uvi = uvi;
        }

        public String getClouds() {
            return clouds;
        }

        public void setClouds(String clouds) {
            this.clouds = clouds;
        }

        public String getVisibility() {
            return visibility;
        }

        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }

        public String getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(String wind_speed) {
            this.wind_speed = wind_speed;
        }

        public String getWind_deg() {
            return wind_deg;
        }

        public void setWind_deg(String wind_deg) {
            this.wind_deg = wind_deg;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }
    }
    class  Daily{
        @SerializedName("dt")
        private String dt;
        @SerializedName("sunrise")
        private String sunrise;
        @SerializedName("sunset")
        private String sunset;
        @SerializedName("moonrise")
        private String moonrise;
        @SerializedName("moonset")
        private String moonset;
        @SerializedName("moon_phase")
        private String moon_phase;
        @SerializedName("temp")
        private Temp temp;
        @SerializedName("feels_like")
        private Feels_like feels_likemp;
        @SerializedName("pressure")
        private String pressure;
        @SerializedName("humidity")
        private String humidity;
        @SerializedName("dew_point")
        private String dew_point;
        @SerializedName("wind_speed")
        private String wind_speed;
        @SerializedName("wind_deg")
        private String wind_deg;
        @SerializedName("weather")
        private List<Weather> weather;
        @SerializedName("clouds")
        private String clouds;
        @SerializedName("pop")
        private String pop;
        @SerializedName("rain")
        private String rain;
        @SerializedName("uvi")
        private String uvi;
    }

}

