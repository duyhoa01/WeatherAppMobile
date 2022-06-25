package com.example.weatherapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "dayly_data")
public class Daylydata {

    @PrimaryKey(autoGenerate = true)
    private int key;

    public int getKey() {
        return key;
    }


    public void setKey(int key) {
        this.key = key;
    }
    @ColumnInfo(defaultValue = "")
    private String cityName;
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    @NonNull
    @SerializedName("lat")
    private String lat;

    @SerializedName("lon")
    private String lon;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("timezone_offset")
    private String timezone_offset;

    @Embedded
    @SerializedName("current")
    private Current current;

    @TypeConverters(HourlyConverter.class)
    @SerializedName("hourly")
    private List<Hourly> hourly;

    @TypeConverters(DailyConverter.class)
    @SerializedName("daily")
    private List<Daily> daily;

    @Override
    public String toString() {
        return "Daylydata{" +
                "key=" + key +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", timezone='" + timezone + '\'' +
                ", timezone_offset='" + timezone_offset + '\'' +
                ", current=" + current +
                ", hourly=" + hourly +
                ", daily=" + daily +
                '}';
    }

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

    public class Temp {
        @SerializedName("day")
        private String day;
        @SerializedName("min")
        private String min;
        @SerializedName("max")
        private String max;
        @SerializedName("night")
        private String night;
        @SerializedName("eve")
        private String eve;
        @SerializedName("morn")
        private String morn;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getNight() {
            return night;
        }

        public void setNight(String night) {
            this.night = night;
        }

        public String getEve() {
            return eve;
        }

        public void setEve(String eve) {
            this.eve = eve;
        }

        public String getMorn() {
            return morn;
        }

        public void setMorn(String morn) {
            this.morn = morn;
        }
    }

    public class Feels_like {
        @SerializedName("day")
        private String day;
        @SerializedName("night")
        private String night;
        @SerializedName("eve")
        private String eve;
        @SerializedName("morn")
        private String morn;
    }

    public class Weather {
        @SerializedName("id")
        private String id;
        @SerializedName("main")
        private String main;
        @SerializedName("description")
        private String description;
        @SerializedName("icon")
        private String icon;

        public String toString() {
            return "Weather{" +
                    "id='" + id + '\'' +
                    ", main='" + main + '\'' +
                    ", discription='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public class Hourly {
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

        @TypeConverters(WeatherConverter.class)
        @SerializedName("weather")
        private List<Weather> weather;

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        @Override
        public String toString() {
            return "Hourly{" +
                    "dt='" + dt + '\'' +
                    ", temp='" + temp + '\'' +
                    ", feels_like='" + feels_like + '\'' +
                    ", pressure='" + pressure + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", dew_point='" + dew_point + '\'' +
                    ", uvi='" + uvi + '\'' +
                    ", clouds='" + clouds + '\'' +
                    ", visibility='" + visibility + '\'' +
                    ", wind_speed='" + wind_speed + '\'' +
                    ", wind_deg='" + wind_deg + '\'' +
                    ", wind_gust='" + wind_gust + '\'' +
                    ", pop='" + pop + '\'' +
                    '}';
        }

        public String getDt() {
            return dt;
        }

        public void setDt(String dt) {
            this.dt = dt;
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

        public String getWind_gust() {
            return wind_gust;
        }

        public void setWind_gust(String wind_gust) {
            this.wind_gust = wind_gust;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }
    }

    public static class Current {
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

        @TypeConverters(WeatherConverter.class)
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

        @Override
        public String toString() {
            return "Current{" +
                    "dt='" + dt + '\'' +
                    ", sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    ", temp='" + temp + '\'' +
                    ", feels_like='" + feels_like + '\'' +
                    ", pressure='" + pressure + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", dew_point='" + dew_point + '\'' +
                    ", uvi='" + uvi + '\'' +
                    ", clouds='" + clouds + '\'' +
                    ", visibility='" + visibility + '\'' +
                    ", wind_speed='" + wind_speed + '\'' +
                    ", wind_deg='" + wind_deg + '\'' +
                    ", weather=" +
                    '}';
        }
    }

    public class Daily {
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

        @TypeConverters(WeatherConverter.class)
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

        public String getMoonrise() {
            return moonrise;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public String getMoon_phase() {
            return moon_phase;
        }

        public void setMoon_phase(String moon_phase) {
            this.moon_phase = moon_phase;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public Feels_like getFeels_likemp() {
            return feels_likemp;
        }

        public void setFeels_likemp(Feels_like feels_likemp) {
            this.feels_likemp = feels_likemp;
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

        public String getClouds() {
            return clouds;
        }

        public void setClouds(String clouds) {
            this.clouds = clouds;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getRain() {
            return rain;
        }

        public void setRain(String rain) {
            this.rain = rain;
        }

        public String getUvi() {
            return uvi;
        }

        public void setUvi(String uvi) {
            this.uvi = uvi;
        }

        @Override
        public String toString() {
            return "Daily{" +
                    "dt='" + dt + '\'' +
                    ", sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    ", moonrise='" + moonrise + '\'' +
                    ", moonset='" + moonset + '\'' +
                    ", moon_phase='" + moon_phase + '\'' +
                    ", temp=" + temp +
                    ", feels_likemp=" + feels_likemp +
                    ", pressure='" + pressure + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", dew_point='" + dew_point + '\'' +
                    ", wind_speed='" + wind_speed + '\'' +
                    ", wind_deg='" + wind_deg + '\'' +
                    ", weather=" + weather +
                    ", clouds='" + clouds + '\'' +
                    ", pop='" + pop + '\'' +
                    ", rain='" + rain + '\'' +
                    ", uvi='" + uvi + '\'' +
                    '}';
        }
    }
}
