package com.example.shubhamgulati.weather_lalit;

import java.util.ArrayList;

public class APIResponse {
    ArrayList<Weather> weather;
    Main main;
    Wind wind;
    Sys sys;
    String name;

    public APIResponse(ArrayList<Weather> weather, Main main, Wind wind, Sys sys, String name) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.sys = sys;
        this.name = name;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
