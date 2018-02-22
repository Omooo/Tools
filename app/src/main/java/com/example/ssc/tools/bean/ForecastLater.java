package com.example.ssc.tools.bean;

/**
 * Created by Omooo on 2018/2/22.
 */

/**
 * 天气预报
 */
public class ForecastLater {
    private String itemDate;
    private String itemWeather;
    private String itemMaxDegree;
    private String itemMinDegree;

    public ForecastLater(String itemDate, String itemWeather, String itemMaxDegree, String itemMinDegree) {
        this.itemDate = itemDate;
        this.itemWeather = itemWeather;
        this.itemMaxDegree = itemMaxDegree;
        this.itemMinDegree = itemMinDegree;
    }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemWeather() {
        return itemWeather;
    }

    public void setItemWeather(String itemWeather) {
        this.itemWeather = itemWeather;
    }

    public String getItemMaxDegree() {
        return itemMaxDegree;
    }

    public void setItemMaxDegree(String itemMaxDegree) {
        this.itemMaxDegree = itemMaxDegree;
    }

    public String getItemMinDegree() {
        return itemMinDegree;
    }

    public void setItemMinDegree(String itemMinDegree) {
        this.itemMinDegree = itemMinDegree;
    }
}
