package com.example.ssc.tools;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by SSC on 2017/9/18.
 */

public class BTSource extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btsource);

        String url = "http://gank.io/api/data/%e7%a6%8f%e5%88%a9/10/1";

    }
}
