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
    private Button mButtonTodayStepCount, mButtonBirth;

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
    }

    private void initView() {
        mButtonLed = findViewById(R.id.button_led);
        mButtonClassTable = findViewById(R.id.button_classTable);
        mButtonTodayStepCount = findViewById(R.id.button_todayStepCount);
        mButtonBirth = findViewById(R.id.button_birth);
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
        }
    }
}
