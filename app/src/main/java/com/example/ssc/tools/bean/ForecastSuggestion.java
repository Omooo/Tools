package com.example.ssc.tools.bean;

/**
 * Created by Omooo on 2018/2/22.
 */

public class ForecastSuggestion {
    private String suggestion;

    public ForecastSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
