package com.example.ssc.tools.ToolsActivitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.ssc.tools.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SSC on 2017/10/19.
 */

public class ToolsTodayStep extends Activity implements SensorEventListener {

    private TextView mTextViewStepNumber;
    private SensorManager mSensorManager;
    private Sensor mSensorCounter;
    private Sensor mSensorDetector;
    private int counterStep = 0;
    private int counterDetector = 0;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_today_step);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mSensorDetector = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        initView();
        //第一次启动将当前日期存入
        firstSaveDate();
    }

    private void firstSaveDate() {
        SharedPreferences setting = getSharedPreferences("FirstIn", 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){
            SharedPreferences sp = getSharedPreferences("FirstDate", MODE_PRIVATE);
            SharedPreferences.Editor editer = sp.edit();
            editer.putString("Date", getCurrentDate());
            editer.commit();
            setting.edit().putBoolean("FIRST", false).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 注册传感器的三个参数说明
         * 1. 接收信号的Listener实例
         * 2. 传感器类型
         * 3. 接收频度
         */
        mSensorManager.registerListener(this, mSensorCounter, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mSensorDetector, SensorManager.SENSOR_DELAY_UI);
    }

    private void initView() {
        mTextViewStepNumber = findViewById(R.id.tv_stepNumber);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //当传感器值发生变化时
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            //启动可能需要十步
            counterStep = (int) sensorEvent.values[0] + 10;
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            counterDetector++;
        }
        String date = getCurrentDate();
        mTextViewStepNumber.setText("    当前走了 " + counterDetector + " 步" + " ,总共走了 " + counterStep + " 步" + "       " + date);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //当传感器精度发生变化时
    }

    public String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date mdate = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(mdate);
    }
}

