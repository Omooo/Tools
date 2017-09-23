package com.example.ssc.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class WelcomeActivity extends Activity implements OnClickListener, OnTouchListener {

    private Button welcome_start;
    private ViewFlipper welcome_page;
    private float touchDownX; // 手指按下的X坐标
    private float touchUpX; // 手指松开的X坐标
    String isFirst = "First";

    private void findView() {
        welcome_start = findViewById(R.id.welcome_start);
        welcome_start.setOnClickListener(this);
        welcome_page = findViewById(R.id.welcome_page);
        welcome_page.setOnTouchListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        SharedPreferences setting = getSharedPreferences(isFirst, 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){
            //第一次启动
            setting.edit().putBoolean("FIRST", false).commit();
            findView();
        }else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.welcome_start:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 重写Back按键响应
     */
    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            v.performClick();
            touchUpX = event.getX();
            // 从左往右，看前一个View
            if (touchUpX - touchDownX > 100) {
                // 显示上一屏动画
                welcome_page.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
                welcome_page.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
                // 显示上一屏View
                View temp = findViewById(R.id.welcome_view_1);
                if (welcome_page.getCurrentView() != temp) {
                    welcome_page.showPrevious();
                }
                // 从左往右，看后一个View
            } else if (touchDownX - touchUpX > 100) {
                // 显示下一屏动画
                welcome_page.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
                welcome_page.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
                // 显示下一屏View
                View temp = findViewById(R.id.welcome_view_4);
                if (welcome_page.getCurrentView() != temp) {
                    welcome_page.showNext();
                }
            }
            return true;
        }
        return false;
    }

}
