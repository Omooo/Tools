package com.example.ssc.tools.ToolsActivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.ssc.tools.R;

/**
 * Created by SSC on 2017/9/19.
 */

public class ToolsCollectionButton extends Activity implements View.OnClickListener{

    private Button mButtonLed;
    private Button mButtonClassTable;
    private Button mButtonTodayStepCount, mButtonBirth, mButtonTrans, mButtonLiving;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolscb);

        initView();
        setOnClick();
    }

    private void setOnClick() {
        mButtonLed.setOnClickListener(this);
        mButtonClassTable.setOnClickListener(this);
        mButtonTodayStepCount.setOnClickListener(this);
        mButtonBirth.setOnClickListener(this);
        mButtonTrans.setOnClickListener(this);
        mButtonLiving.setOnClickListener(this);

    }

    private void initView() {
        mButtonLed = findViewById(R.id.button_led);
        mButtonClassTable = findViewById(R.id.button_classTable);
        mButtonTodayStepCount = findViewById(R.id.button_todayStepCount);
        mButtonBirth = findViewById(R.id.button_birth);
        mButtonTrans = findViewById(R.id.button_translate);
        mButtonLiving = findViewById(R.id.button_living);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_led:
                startActivity(new Intent(this, Tools_Led.class));
                break;
            case R.id.button_classTable:
                startActivity(new Intent(this, ToolsClassTables.class));
                break;
            case R.id.button_todayStepCount:
                startActivity(new Intent(this, ToolsTodayStep.class));
                break;
            case R.id.button_birth:
                startActivity(new Intent(this, ToolsBirth.class));
                break;
            case R.id.button_translate:
                startActivity(new Intent(this, ToolsTrans.class));
                break;
            case R.id.button_ai:
                startActivity(new Intent(this, Tools_AI.class));
                break;
            case R.id.button_living:
                startActivity(new Intent(this, ToolsVideoList.class));
                break;
        }
    }
}
/**
 * TODO : ↓
 * 自从加了 看会直播 功能，工具箱其他功能在模拟器上运行变得巨卡，但是其他非工具箱里面的功能都还好。
 * 又在真机（Mi5）测试一下，都还好，不知道问题出现在在哪。
 * 看会直播功能，如想去掉，可以删除以下实现类及布局文件：
 * ToolsPlayVideoActivity.class
 * ToolsVideoList.class
 * tools_video_item.xml
 * tools_video_list.xml
 * tools_video_play.xml
 * 以及 build.gradle 直播所需依赖库
 */
