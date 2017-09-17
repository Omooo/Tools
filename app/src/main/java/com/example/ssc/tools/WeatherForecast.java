package com.example.ssc.tools;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SSC on 2017/9/17.
 */

public class WeatherForecast extends Activity {
    private EditText mEditText1;

    //很是痛心，阉割了这个功能，根据天气布置对应天气图片。。。
//    private ImageView mImageView1;
//    private ImageView mImageView2;
//    private ImageView mImageView3;

    private TextView mDate1;
    private TextView mWeather1;
    private TextView mTmp1;

    private TextView mDate2;
    private TextView mWeather2;
    private TextView mTmp2;

    private TextView mDate3;
    private TextView mWeather3;
    private TextView mTmp3;

    final String weatherAppKey = "5e9d91564360447ca6be642716bb8a28";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        mEditText1 = findViewById(R.id.editText1);

//        mImageView1 = findViewById(R.id.weather_image1);
//        mImageView2 = findViewById(R.id.weather_image2);
//        mImageView3 = findViewById(R.id.weather_image3);

        mDate1 = findViewById(R.id.weather_date1);
        mWeather1 = findViewById(R.id.weather_state1);
        mTmp1 = findViewById(R.id.weather_temp1);

        mDate2 = findViewById(R.id.weather_date2);
        mWeather2 = findViewById(R.id.weather_state2);
        mTmp2 = findViewById(R.id.weather_temp2);

        mDate3 = findViewById(R.id.weather_date3);
        mWeather3 = findViewById(R.id.weather_state3);
        mTmp3 = findViewById(R.id.weather_temp3);
    }

    public void SearchWeather(View view) {
        String cityValue = mEditText1.getText().toString();
        try {
            String cityValueURLEncode = URLEncoder.encode(cityValue, "utf-8");
            //构造好的URL
            String url = "https://free-api.heweather.com/v5/forecast?city=" + cityValueURLEncode + "&key=" + weatherAppKey;
            Log.i("233--url", url);
            JsonProvider(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void JsonProvider(String url){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                Log.i("233--content", content);
                AnaJson anaJson = new AnaJson();
                anaJson.GetJsonValue(content);
            }
        });
    }
    class AnaJson{
        String[] dateList=new String[3];
        String[] weatherList=new String[3];
        String[] tmpList=new String[3];
        //解析Json数据
        public void GetJsonValue(String json) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
                JSONArray jsonArray1 = jsonObject1.getJSONArray("daily_forecast");

                //今天
                JSONObject today = (JSONObject) jsonArray1.get(0);
                //今天日期
                String date1 = today.getString("date");
                dateList[0] = date1;
                //今天天气
                JSONObject todayCond = today.getJSONObject("cond");
                String txt_d1 = todayCond.getString("txt_d");
                Log.i("233-txt_d1", txt_d1);
                weatherList[0] = txt_d1;
                //今天温度
                JSONObject todayTemp = today.getJSONObject("tmp");
                String tmp1 = todayTemp.getString("min") + " - " + todayTemp.getString("max")+"℃";
                Log.i("233--tmp1", tmp1);
                tmpList[0] = tmp1;

                //明天
                JSONObject  tomorrow = (JSONObject) jsonArray1.get(1);
                String date2 = tomorrow.getString("date");
                dateList[1] = date2;
                JSONObject tomorrowCond = tomorrow.getJSONObject("cond");
                String txt_d2 = tomorrowCond.getString("txt_d");
                Log.i("233-txt_d2", txt_d2);
                weatherList[1] = txt_d2;
                JSONObject tomorrowTemp = tomorrow.getJSONObject("tmp");
                String tmp2 = tomorrowTemp.getString("min") + " - " + tomorrowTemp.getString("max")+"℃";
                Log.i("233--tmp2", tmp2);
                tmpList[1] = tmp2;

                //后天
                JSONObject aftertomorrow = (JSONObject) jsonArray1.get(2);
                String date3 = aftertomorrow.getString("date");
                dateList[2] = date3;
                JSONObject aftertomorrowCond = aftertomorrow.getJSONObject("cond");
                String txt_d3 = aftertomorrowCond.getString("txt_d");
                Log.i("233-txt_d3", txt_d3);
                weatherList[2] = txt_d3;
                JSONObject aftertomorrowTemp = aftertomorrow.getJSONObject("tmp");
                String tmp3 = aftertomorrowTemp.getString("min") + " - " + aftertomorrowTemp.getString("max")+"℃";
                Log.i("233--tmp3", tmp3);
                tmpList[2] = tmp3;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //赋值到UI
                    mDate1.setText(dateList[0]);
                    mWeather1.setText(weatherList[0]);
                    mTmp1.setText(tmpList[0]);

                    mDate2.setText(dateList[1]);
                    mWeather2.setText(weatherList[1]);
                    mTmp2.setText(tmpList[1]);

                    mDate3.setText(dateList[2]);
                    mWeather3.setText(weatherList[2]);
                    mTmp3.setText(tmpList[2]);

                    //辣鸡代码！
//                    Log.i("weatherList[0]", weatherList[0]);
//                    if (weatherList[0].equals("多云"))
//                        mImageView1.setImageResource(R.mipmap.p3);
//                    else if (weatherList[0].equals("晴"))
//                        mImageView1.setImageResource(R.mipmap.p2);
//                    else if (weatherList[0].equals("小雨"))
//                        mImageView1.setImageResource(R.mipmap.p4);
//                    else if (weatherList[0].equals("阴"))
//                        mImageView1.setImageResource(R.mipmap.p5);
//                    else if (weatherList[0].equals("阵雨"))
//                        mImageView1.setImageResource(R.mipmap.p6);
//                    else if (weatherList[0].equals("中雨"))
//                        mImageView1.setImageResource(R.mipmap.p7);
//                    else if (weatherList[0].equals("大雨"))
//                        mImageView1.setImageResource(R.mipmap.p8);
//                    else if (weatherList[0].equals("雾"))
//                        mImageView1.setImageResource(R.mipmap.p9);
//                    else if (weatherList[0].equals("霾"))
//                        mImageView1.setImageResource(R.mipmap.p10);
//                    else if (weatherList[0].equals("少云"))
//                        mImageView1.setImageResource(R.mipmap.p11);
//                    else if (weatherList[0].equals("晴间多云"))
//                        mImageView1.setImageResource(R.mipmap.p12);
//                    else if (weatherList[0].equals("有风"))
//                        mImageView1.setImageResource(R.mipmap.p13);
//
//                    if (weatherList[1].equals("晴"))
//                        mImageView1.setImageResource(R.mipmap.p2);
//                    else if (weatherList[1].equals("多云"))
//                        mImageView1.setImageResource(R.mipmap.p3);
//                    else if (weatherList[1].equals("小雨"))
//                        mImageView1.setImageResource(R.mipmap.p4);
//                    else if (weatherList[1].equals("阴"))
//                        mImageView1.setImageResource(R.mipmap.p5);
//                    else if (weatherList[1].equals("阵雨"))
//                        mImageView1.setImageResource(R.mipmap.p6);
//                    else if (weatherList[1].equals("中雨"))
//                        mImageView1.setImageResource(R.mipmap.p7);
//                    else if (weatherList[1].equals("大雨"))
//                        mImageView1.setImageResource(R.mipmap.p8);
//                    else if (weatherList[1].equals("雾"))
//                        mImageView1.setImageResource(R.mipmap.p9);
//                    else if (weatherList[1].equals("霾"))
//                        mImageView1.setImageResource(R.mipmap.p10);
//                    else if (weatherList[1].equals("少云"))
//                        mImageView1.setImageResource(R.mipmap.p11);
//                    else if (weatherList[1].equals("晴间多云"))
//                        mImageView1.setImageResource(R.mipmap.p12);
//                    else if (weatherList[1].equals("有风"))
//                        mImageView1.setImageResource(R.mipmap.p13);
//
//                    if (weatherList[2].equals("晴"))
//                        mImageView1.setImageResource(R.mipmap.p2);
//                    else if (weatherList[2].equals("多云"))
//                        mImageView1.setImageResource(R.mipmap.p3);
//                    else if (weatherList[2].equals("小雨"))
//                        mImageView1.setImageResource(R.mipmap.p4);
//                    else if (weatherList[2].equals("阴"))
//                        mImageView1.setImageResource(R.mipmap.p5);
//                    else if (weatherList[2].equals("阵雨"))
//                        mImageView1.setImageResource(R.mipmap.p6);
//                    else if (weatherList[2].equals("中雨"))
//                        mImageView1.setImageResource(R.mipmap.p7);
//                    else if (weatherList[2].equals("大雨"))
//                        mImageView1.setImageResource(R.mipmap.p8);
//                    else if (weatherList[2].equals("雾"))
//                        mImageView1.setImageResource(R.mipmap.p9);
//                    else if (weatherList[2].equals("霾"))
//                        mImageView1.setImageResource(R.mipmap.p10);
//                    else if (weatherList[2].equals("少云"))
//                        mImageView1.setImageResource(R.mipmap.p11);
//                    else if (weatherList[2].equals("晴间多云"))
//                        mImageView1.setImageResource(R.mipmap.p12);
//                    else if (weatherList[2].equals("有风"))
//                        mImageView1.setImageResource(R.mipmap.p13);
                }
            });

        }

    }
}
