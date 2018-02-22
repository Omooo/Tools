package com.example.ssc.tools;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssc.tools.bean.ForecastCurrent;
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
    private TextView mTitleCity, mTitleTime, mTvDegree, mTvWeather, mTvAqi, mTvPm, mTvSuggestion1,mTvSuggestion2, mTvSuggestion3;
    private LinearLayout mLayoutForecast;
    private static final String key = "5e9d91564360447ca6be642716bb8a28";
    private static final String TAG = "CoolWeatherForecast";

    CityPickerView mPickerView = new CityPickerView();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#800000"));
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
                        String result = province.getName() + city.getName() + district.getName();
                        //实际上只需要 县级地区 即可
                        String cityData = district.getName().toString();
                        mTitleCity.setText(cityData);

                        setForecastData();
                    }
                });
                //显示
                mPickerView.showCityPicker();
            }
        });
    }

    public void setForecastData() {
        String city = mTitleCity.getText().toString();

//        //获取未来三天天气预报
//        String url1 = "https://free-api.heweather.com/v5/forecast?city=" + city + "&key=" + key;
//        MyHttpUtil.getInstance().startGet(url1, new OnNetResultListener() {
//            @Override
//            public void onSuccessListener(String result) {
//                Log.i(TAG, "onSuccessListener: " + result);
//
//            }
//
//            @Override
//            public void onFailureListener(String result) {
//                Log.i(TAG, "onFailureListener: " + result);
//            }
//        });

        //获取当天实时天气预报
        String url2 = "https://free-api.heweather.com/s6/weather/now?location=" + city + "&key=" + key;
        MyHttpUtil.getInstance().startGet(url2, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result) {
                CutJson cutJson = new CutJson();
                Log.i(TAG, "onSuccessListener: " + result);
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
                Log.i(TAG, "onFailureListener: " + result);
            }
        });


    }
}
