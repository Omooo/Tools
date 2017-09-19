package com.example.ssc.tools;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by SSC on 2017/9/18.
 */

public class SettingsTools extends Activity {

    private ImageButton mImageButton1;
    int num_click = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        mImageButton1 = findViewById(R.id.imageButton1);

    }

    public void imageClick(View view) {

        num_click++;
        if (num_click % 2 == 0) {
            mImageButton1.setImageResource(R.mipmap.close_icon);
            Toast.makeText(this, "司机模式已经关闭", Toast.LENGTH_SHORT).show();
        } else {
            mImageButton1.setImageResource(R.mipmap.open_icon);
            Toast.makeText(this, "司机模式开启成功", Toast.LENGTH_SHORT).show();
        }
    }
}
