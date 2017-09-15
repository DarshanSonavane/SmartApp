package com.smart_app.smartapp.fragmentsdemo.model;

/**
 * Created by lenovo on 2017-09-09.
 */

public class Cities {

    String cityName;
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Cities(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "cityName='" + cityName + '\'' +
                '}';
    }


}

