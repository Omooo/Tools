package com.example.ssc.tools;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

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

    private LineChart mLineChart;
    private LineDataSet mLineDataSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        mEditText1 = findViewById(R.id.editText1);

        mDate1 = findViewById(R.id.weather_date1);
        mWeather1 = findViewById(R.id.weather_state1);
        mTmp1 = findViewById(R.id.weather_temp1);

        mDate2 = findViewById(R.id.weather_date2);
        mWeather2 = findViewById(R.id.weather_state2);
        mTmp2 = findViewById(R.id.weather_temp2);

        mDate3 = findViewById(R.id.weather_date3);
        mWeather3 = findViewById(R.id.weather_state3);
        mTmp3 = findViewById(R.id.weather_temp3);

        mLineChart = findViewById(R.id.mLineChar);
        mLineChart.setDrawGridBackground(false);
        mLineChart.getDescription().setEnabled(false);

        drawLineChart();
    }

    private void drawLineChart() {
        //x轴
        LimitLine llXAxis = new LimitLine(10f, "标记");
        //设置线宽
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        //设置
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        //设置x轴的最大值
        xAxis.setAxisMaximum(30f);
        //设置x轴的最小值
        xAxis.setAxisMinimum(0f);
        //不显示X轴
        xAxis.setEnabled(false);
        YAxis leftAxis = mLineChart.getAxisLeft();
//        //重置所有限制线,以避免重叠线
//        leftAxis.removeAllLimitLines();
        //y轴最大
        leftAxis.setAxisMaximum(40f);
        //y轴最小
        leftAxis.setAxisMinimum(20f);
        leftAxis.setEnabled(false);
        leftAxis.enableGridDashedLine(5f, 5f, 0f);
        leftAxis.setDrawZeroLine(false);
        //限制数据
        leftAxis.setDrawLimitLinesBehindData(true);
        mLineChart.getAxisRight().setEnabled(false);

        //模拟数据
        ArrayList<Entry> values = new ArrayList<Entry>();
        values.add(new Entry(5, 25));
        values.add(new Entry(15, 20));
        values.add(new Entry(25, 30));
        //设置数据
        setData(values);
    }

    private void setData(ArrayList<Entry> values) {
        if (mLineChart.getData() != null && mLineChart.getData().getDataSetCount() > 0) {
            mLineDataSet = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            mLineDataSet.setValues(values);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            mLineDataSet = new LineDataSet(values,"");
            mLineDataSet.setValueTextSize(20f);
            // 在这里设置线
            mLineDataSet.enableDashedLine(10f, 5f, 0f);
            mLineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
            mLineDataSet.setColor(Color.WHITE);
            mLineDataSet.setCircleColor(Color.WHITE);
            mLineDataSet.setLineWidth(1f);
            mLineDataSet.setCircleRadius(3f);
            mLineDataSet.setDrawCircleHole(false);
            mLineDataSet.setValueTextSize(20f);
            mLineDataSet.setValueTextColor(Color.WHITE);
            mLineDataSet.setFormLineWidth(1f);
            mLineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            mLineDataSet.setFormSize(15.f);
            //不填充
            mLineDataSet.setDrawFilled(false);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            //添加数据集
            dataSets.add(mLineDataSet);

            //创建一个数据集的数据对象
            LineData data = new LineData(dataSets);

            //谁知数据
            mLineChart.setData(data);
        }
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
        double[] averageTmp = new double[3];
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
                String minTmp1 = todayTemp.getString("min");
                String maxTmp1 = todayTemp.getString("max");
                String tmp1 = minTmp1 + " - " + maxTmp1 + "℃";
                Log.i("233--tmp1", tmp1);
                tmpList[0] = tmp1;
                averageTmp[0] = (Integer.parseInt(minTmp1) + Integer.parseInt(maxTmp1))/2;

                //明天
                JSONObject  tomorrow = (JSONObject) jsonArray1.get(1);
                String date2 = tomorrow.getString("date");
                dateList[1] = date2;
                JSONObject tomorrowCond = tomorrow.getJSONObject("cond");
                String txt_d2 = tomorrowCond.getString("txt_d");
                Log.i("233-txt_d2", txt_d2);
                weatherList[1] = txt_d2;
                JSONObject tomorrowTemp = tomorrow.getJSONObject("tmp");
                String minTmp2 = tomorrowTemp.getString("min");
                String maxTmp2 = tomorrowTemp.getString("max");
                String tmp2 = minTmp2 + " - " + maxTmp2 + "℃";
                Log.i("233--tmp2", tmp2);
                tmpList[1] = tmp2;
                averageTmp[1] = (Integer.parseInt(minTmp2) + Integer.parseInt(maxTmp2)) / 2;

                //后天
                JSONObject aftertomorrow = (JSONObject) jsonArray1.get(2);
                String date3 = aftertomorrow.getString("date");
                dateList[2] = date3;
                JSONObject aftertomorrowCond = aftertomorrow.getJSONObject("cond");
                String txt_d3 = aftertomorrowCond.getString("txt_d");
                Log.i("233-txt_d3", txt_d3);
                weatherList[2] = txt_d3;
                JSONObject aftertomorrowTemp = aftertomorrow.getJSONObject("tmp");
                String minTmp3 = aftertomorrowTemp.getString("min");
                String maxTmp3 = aftertomorrowTemp.getString("max");
                String tmp3 = minTmp3 + " - " + maxTmp3+"℃";
                Log.i("233--tmp3", tmp3);
                tmpList[2] = tmp3;
                averageTmp[2] = (Integer.parseInt(minTmp3) + Integer.parseInt(maxTmp3)) / 2;

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

                }
            });

        }

    }

}
