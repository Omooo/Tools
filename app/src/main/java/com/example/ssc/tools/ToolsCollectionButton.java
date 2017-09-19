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

public class ToolsCollectionButton extends Activity{

    private Button mButton_led;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolscb);

        mButton_led = findViewById(R.id.button_led);

    }

    //LED屏幕显示
    public void tools_Led(View view) {
        startActivity(new Intent(this, Tools_Led.class));
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.button_led:
//                startActivity(new Intent(this, Tools_Led.class));
//                break;
//        }
//    }
}
