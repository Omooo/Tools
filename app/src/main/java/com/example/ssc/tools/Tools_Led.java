package com.example.ssc.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by SSC on 2017/9/19.
 */

public class Tools_Led extends Activity {

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_led_button);

        mEditText = findViewById(R.id.text_led);
        mButton = findViewById(R.id.button_led);

    }

    public void startRollingLed(View view) {
//        //布局装载器
//        final LayoutInflater inflater = LayoutInflater.from(this);
//        //滚动的时候加载的布局
//        final LinearLayout rolling_xml = findViewById(R.id.rolling_xml);
//
//        mButton.setVisibility(View.GONE);
//        mEditText.setVisibility(View.GONE);
//        LinearLayout layout = inflater.inflate(R.layout.led_rolling, null).findViewById(R.id.led_layout);
//        rolling_xml.removeAllViews();
//        rolling_xml.addView(layout);
//        //切换为横屏
////        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        String text_led = mEditText.getText().toString();
        Intent intent = new Intent(this, Tools_Led_Marquee.class);
        intent.putExtra("text_led", text_led);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
    }
}
