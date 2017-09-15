package com.smart_app.smartapp.fragmentsdemo.model;

/**
 * Created by lenovo on 2017-09-06.
 */

public class Countries {

    String countryName;
    public Countries(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "countryName='" + countryName + '\'' +
                '}';
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
