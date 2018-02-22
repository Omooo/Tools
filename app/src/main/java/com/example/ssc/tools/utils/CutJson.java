package com.example.ssc.tools.utils;

import com.example.ssc.tools.bean.ForecastCurrent;
import com.example.ssc.tools.bean.ForecastLater;
import com.example.ssc.tools.listener.JsonDataListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omooo on 2018/2/22.
 */

public class CutJson {

    private JSONObject mJSONObject;
    private JSONArray mJSONArray;
    private List<ForecastLater> mForecastLaterList;
    private List<ForecastCurrent> mForecastCurrentList;

    /**
     * 解析Json数据
     * @param json
     * @param type  1：未来三天天气数据  2：当天实时数据
     * @return
     */
    public void getValueFromJson(String json, int type, JsonDataListener listener) {

        try {
            mJSONObject = new JSONObject(json);
            if (type == 1) {
                mJSONArray = mJSONObject.getJSONArray("HeWeather5");
                JSONObject jsonObject = (JSONObject) mJSONArray.get(0);
                JSONArray jsonArray = jsonObject.getJSONArray("daily_forecast");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    String date = object.getString("data");
                    JSONObject objectWeather = object.getJSONObject("cond");
                    String weather = objectWeather.getString("txt_d");
                    JSONObject objectTmp = object.getJSONObject("tmp");
                    String maxDegree = objectTmp.getString("max");
                    String minDegree = objectTmp.getString("min");
                    //填充数据
                    mForecastLaterList = new ArrayList<>();
                    mForecastLaterList.add(i, new ForecastLater(date, weather, maxDegree, minDegree));
                }

                listener.getData(mForecastLaterList);

            } else if (type == 2) {
                mJSONArray = mJSONObject.getJSONArray("HeWeather6");
                JSONObject jsonObject = (JSONObject) mJSONArray.get(0);
                JSONObject objectBasic = jsonObject.getJSONObject("basic");
                String titleCity = objectBasic.getString("location");
                JSONObject objectUpdate = jsonObject.getJSONObject("update");
                String titleTime = objectUpdate.getString("loc").substring(10, 16);
                JSONObject objectNow = jsonObject.getJSONObject("now");
                String currentTmp = objectNow.getString("tmp");
                String currentWeather = objectNow.getString("cond_txt");
                //填充数据
                mForecastCurrentList = new ArrayList<>();
                mForecastCurrentList.add(new ForecastCurrent(titleCity, titleTime, currentTmp, currentWeather));

                listener.getData(mForecastCurrentList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        if (mForecastLaterList != null && type == 1) {
//            return mForecastLaterList;
//        } else {
//            return mForecastCurrentList;
//        }
    }
}
