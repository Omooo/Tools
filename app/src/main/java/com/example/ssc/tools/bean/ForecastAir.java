package com.example.ssc.tools.bean;

/**
 * Created by Omooo on 2018/2/22.
 */

public class ForecastAir {
    private String aqi;
    private String pm;

    public ForecastAir(String aqi, String pm) {
        this.aqi = aqi;
        this.pm = pm;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }
}
