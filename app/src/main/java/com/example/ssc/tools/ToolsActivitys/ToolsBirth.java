package com.example.ssc.tools.ToolsActivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.ssc.tools.R;

/**
 * Created by SSC on 2017/10/30.
 */

public class ToolsBirth extends Activity {

    private Button mButtonSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_birth);

        initView();
        mButtonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ToolsBirth.this, ToolsDie.class));
                overridePendingTransition(R.anim.birth_to_die, R.anim.die_to_birth);
            }
        });
    }

    private void initView() {
        mButtonSwitch = findViewById(R.id.btn_switchToDie);
    }
}
