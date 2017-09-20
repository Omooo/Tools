package com.example.ssc.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;

import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SSC on 2017/9/20.
 */

public class Tools_Led_Marquee extends Activity {

    SimpleMarqueeView marqueeView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.led_rolling);

        Display display=getWindowManager().getDefaultDisplay();
        int width=display.getWidth();
        int height=display.getHeight();
        Log.i("233--width+height", width + "   " + height);
        init();
    }

    private void init() {

        Intent intent = getIntent();
        String content = intent.getStringExtra("text_led");
        char[] calcChar = content.toCharArray();
        String fitContent = null;
        if (calcChar.length >= 6) {
            fitContent = content;
        } else if (calcChar.length <= 3) {
            fitContent = "           " + content;
        }else {
            fitContent = "     " + content;
        }
        marqueeView = findViewById(R.id.simpleMarqueeView);
        final List<String> datas = Arrays.asList(fitContent,fitContent,fitContent,fitContent);
        SimpleMF<String> marqueeFactory = new SimpleMF(this);
        marqueeFactory.setData(datas);
        marqueeView.setMarqueeFactory(marqueeFactory);
    }

    //处理重影
    @Override
    protected void onStart() {
        super.onStart();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
        marqueeView.startFlipping();
    }

    @Override
    protected void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
    }
}
