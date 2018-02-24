package com.example.ssc.tools;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hubert.library.HighLight;
import com.app.hubert.library.NewbieGuide;
import com.example.ssc.tools.bean.ForecastAir;
import com.example.ssc.tools.bean.ForecastCurrent;
import com.example.ssc.tools.bean.ForecastLater;
import com.example.ssc.tools.bean.ForecastSuggestion;
import com.example.ssc.tools.listener.JsonDataListener;
import com.example.ssc.tools.listener.OnNetResultListener;
import com.example.ssc.tools.utils.CutJson;
import com.example.ssc.tools.utils.MyHttpUtil;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omooo on 2018/2/21.
 */

public class CoolWeatherForecast extends AppCompatActivity {

    private ImageView mTitleImage;
    private TextView mTitleCity, mTitleTime, mTvDegree, mTvWeather, mTvAqi, mTvPm, mTvSuggestion1,mTvSuggestion2, mTvSuggestion3,mTvSuggestion4;
    private LinearLayout mLayoutForecast;
    private static final String key = "5e9d91564360447ca6be642716bb8a28";
    private static final String TAG = "CoolWeatherForecast";

    CityPickerView mPickerView = new CityPickerView();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setStatusBarColor(Color.parseColor("#800000"));
        getWindow().setStatusBarColor(Color.parseColor("#0B73D4"));
        setContentView(R.layout.activity_cool_weather);

        //初始化控件
        init();

        //初始化城市选择数据
        CityConfig cityConfig = new CityConfig.Builder().build();
        mPickerView.setConfig(cityConfig);
        mPickerView.init(this);

        //选择城市
        chooseCity();

        //设置数据
//        setForecastData();

        //第一次进入界面引导提示用户选择城市
        firstShow();
    }

    private void firstShow() {
        NewbieGuide.with(this)//activity or fragment
                .setLabel("guide1")//Set guide layer labeling to distinguish different guide layers, must be passed! Otherwise throw an error
                .addHighLight(mTitleImage, HighLight.Type.RECTANGLE)//Add the view that needs to be highlighted
                .setLayoutRes(R.layout.view_guide)//Custom guide layer layout, do not add background color, the boot layer background color is set by setBackgroundColor()
                .show();
    }

    private void init() {
        mTitleImage = findViewById(R.id.title_image);

        mTitleCity = findViewById(R.id.title_city);
        mTitleTime = findViewById(R.id.title_time);
        mTvDegree = findViewById(R.id.tv_degree);
        mTvWeather = findViewById(R.id.tv_weather);
        mTvAqi = findViewById(R.id.tv_aqi);
        mTvPm = findViewById(R.id.tv_pm);

        mTvSuggestion1 = findViewById(R.id.tv_suggestion1);
        mTvSuggestion2 = findViewById(R.id.tv_suggestion2);
        mTvSuggestion3 = findViewById(R.id.tv_suggestion3);
        mTvSuggestion4 = findViewById(R.id.tv_suggestion4);

        mLayoutForecast = findViewById(R.id.layout_forecast);
    }

    private void chooseCity() {
        mTitleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //省市县
                        String proData = province.getName().toString();
                        String cityData = city.getName().toString();
                        String disData = district.getName().toString();

                        //实际上只需要 县级地区 即可
                        mTitleCity.setText(cityData);

                        setForecastData(cityData,disData);
                    }
                });
                //显示
                mPickerView.showCityPicker();
            }
        });
    }

    public void setForecastData(String cityData,String disData) {
        //县级
        String city = mTitleCity.getText().toString();
        //获取未来三天天气预报
        String url1 = "https://free-api.heweather.com/v5/forecast?city=" + city + "&key=" + key;
        MyHttpUtil.getInstance().startGet(url1, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result) {

                new CutJson().getValueFromJson(result, 1, new JsonDataListener() {
                    @Override
                    public void getData(List<?> mList) {

                        mLayoutForecast.removeAllViews();
                        TextView date, weather, maxDegree, minDegree;

                        List<ForecastLater> forecastLaterList = (List<ForecastLater>) mList;
                        for (int i = 0; i < forecastLaterList.size(); i++) {
                            View view = LayoutInflater.from(CoolWeatherForecast.this).inflate(R.layout.activity_forecast_item, mLayoutForecast, false);
                            date = view.findViewById(R.id.tv_item_date);
                            weather = view.findViewById(R.id.tv_item_weather);
                            maxDegree = view.findViewById(R.id.tv_item_max_degree);
                            minDegree = view.findViewById(R.id.tv_item_min_degree);

                            ForecastLater forecastLater = forecastLaterList.get(i);
                            date.setText(forecastLater.getItemDate());
                            weather.setText(forecastLater.getItemWeather());
                            maxDegree.setText(forecastLater.getItemMaxDegree());
                            minDegree.setText(forecastLater.getItemMinDegree());

                            ViewParent parent = view.getParent();
                            if (parent != null) {
                                ViewGroup group = (ViewGroup) parent;
                                group.removeView(view);
                            }
                            mLayoutForecast.addView(view);
                        }
                    }
                });
            }

            @Override
            public void onFailureListener(String result) {

            }
        });

        //获取当天实时天气预报
        String url2 = "https://free-api.heweather.com/s6/weather/now?location=" + city + "&key=" + key;
        MyHttpUtil.getInstance().startGet(url2, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result) {
                CutJson cutJson = new CutJson();
                cutJson.getValueFromJson(result, 2, new JsonDataListener() {
                    @Override
                    public void getData(List<?> mList) {
                        List<ForecastCurrent> currentList;
                        currentList = (List<ForecastCurrent>) mList;
                        ForecastCurrent current = currentList.get(0);
                        mTitleTime.setText(current.getTitleTime());
                        mTvDegree.setText(current.getTodayDegree());
                        mTvWeather.setText(current.getTodayWeather());

                    }
                });

            }

            @Override
            public void onFailureListener(String result) {

            }
        });

        //获取AQI和PM2.5数据
        String url3 = "https://free-api.heweather.com/s6/air/now?location=" + cityData + "&key=" + key;
        MyHttpUtil.getInstance().startGet(url3, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result) {
                new CutJson().getValueFromJson(result, 3, new JsonDataListener() {
                    @Override
                    public void getData(List<?> mList) {
                        List<ForecastAir> forecastAirList = (List<ForecastAir>) mList;
                        ForecastAir forecastAir = forecastAirList.get(0);
                        mTvAqi.setText(forecastAir.getAqi());
                        mTvPm.setText(forecastAir.getPm());
                    }
                });
            }

            @Override
            public void onFailureListener(String result) {

            }
        });

        //获取生活建议
        String url4 = "https://free-api.heweather.com/s6/weather/lifestyle?location=" + disData + "&key=" + key;
        MyHttpUtil.getInstance().startGet(url4, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result) {
                Log.i(TAG, "onSuccessListener: " + result);
                new CutJson().getValueFromJson(result, 4, new JsonDataListener() {
                    @Override
                    public void getData(List<?> mList) {
                        List<ForecastSuggestion> forecastSuggestionList = (List<ForecastSuggestion>) mList;
                        String suggestion1 = forecastSuggestionList.get(0).getSuggestion();
                        mTvSuggestion1.setText(suggestion1);
                        String suggestion2 = forecastSuggestionList.get(1).getSuggestion();
                        mTvSuggestion2.setText(suggestion2);
                        String suggestion3 = forecastSuggestionList.get(2).getSuggestion();
                        mTvSuggestion3.setText(suggestion3);
                        String suggestion4 = forecastSuggestionList.get(3).getSuggestion();
                        mTvSuggestion4.setText(suggestion4);
                    }
                });
            }

            @Override
            public void onFailureListener(String result) {

            }
        });
    }
}
