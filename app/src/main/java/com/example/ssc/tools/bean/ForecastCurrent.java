package com.example.ssc.tools.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omooo on 2018/2/22.
 */

/**
 * 实时天气
 */
public class ForecastCurrent {

    private String titleCity;
    private String titleTime;
    private String todayDegree;
    private String todayWeather;

    public ForecastCurrent(String titleCity, String titleTime, String todayDegree, String todayWeather) {
        this.titleCity = titleCity;
        this.titleTime = titleTime;
        this.todayDegree = todayDegree;
        this.todayWeather = todayWeather;
    }

    public ForecastCurrent() {

    }

    public String getTitleCity() {
        return titleCity;
    }

    public void setTitleCity(String titleCity) {
        this.titleCity = titleCity;
    }

    public String getTitleTime() {
        return titleTime;
    }

    public void setTitleTime(String titleTime) {
        this.titleTime = titleTime;
    }

    public String getTodayDegree() {
        return todayDegree;
    }

    public void setTodayDegree(String todayDegree) {
        this.todayDegree = todayDegree;
    }

    public String getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(String todayWeather) {
        this.todayWeather = todayWeather;
    }
}
