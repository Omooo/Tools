package com.example.ssc.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by SSC on 2017/9/19.
 */

public class ToolsCollectionButton extends Activity implements View.OnClickListener{

    private Button mButton_led;
    private Button mButton_classTable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolscb);

        initView();
        setOnClick();
    }

    private void setOnClick() {
        mButton_led.setOnClickListener(this);
        mButton_classTable.setOnClickListener(this);
    }

    private void initView() {
        mButton_led = findViewById(R.id.button_led);
        mButton_classTable = findViewById(R.id.button_classTable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //LED
            case R.id.button_led:
                startActivity(new Intent(this, Tools_Led.class));
                break;
        }
    }
}
