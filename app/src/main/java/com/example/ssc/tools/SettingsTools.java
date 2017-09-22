package com.example.ssc.tools;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by SSC on 2017/9/18.
 */

public class SettingsTools extends Activity {

    private Switch mSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        mSwitch = findViewById(R.id.switchButton);

    }

    public void switchMode(View view) {
        if (mSwitch.isChecked()) {
            Toast.makeText(this, "打开", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "关闭", Toast.LENGTH_SHORT).show();

//        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//        if (mode == Configuration.UI_MODE_NIGHT_YES)
//            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        else if (mode == Configuration.UI_MODE_NIGHT_NO) {
//            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        recreate();
    }
}
