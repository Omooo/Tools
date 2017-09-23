package com.example.ssc.tools;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ssc.tools.service.ServiceSound;

/**
 * Created by SSC on 2017/9/18.
 */

public class SettingsTools extends Activity {

    private Switch mSwitchMode;
    private Switch mSwitchSound;
    private AudioManager mAudioManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        mSwitchMode = findViewById(R.id.switchButton);
        mSwitchSound = findViewById(R.id.switchSound);
    }

    public void switchMode(View view) {
        if (mSwitchMode.isChecked()) {
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

    public void switchSound(View view) {
//        if (mSwitchSound.isChecked()) {
////            int mode = AudioManager.RINGER_MODE_NORMAL;
//            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//            mAudioManager.adjustVolume(100, 0);
////            MediaPlayer player = MediaPlayer.create(this, R.raw.sound);
////            player.start();
//        }
        startService(new Intent(this, ServiceSound.class));

    }
}
