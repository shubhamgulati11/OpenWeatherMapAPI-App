package com.example.shubhamgulati.weather_lalit;

public class Wind {
    Float speed,deg;

    public Wind(Float speed, Float deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getDeg() {
        return deg;
    }

    public void setDeg(Float deg) {
        this.deg = deg;
    }
}
