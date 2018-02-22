package com.example.ssc.tools.utils;

import com.example.ssc.tools.listener.OnNetResultListener;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Omooo on 2018/2/22.
 */

public class MyHttpUtil {
    private static MyHttpUtil sMyHttpUtil = null;
    private android.os.Handler mHandler = new android.os.Handler();
    private OkHttpClient mClient;
    private Request mRequest;

    private MyHttpUtil() {

    }

    public static MyHttpUtil getInstance() {
        if (sMyHttpUtil == null) {
            synchronized (MyHttpUtil.class) {
                if (sMyHttpUtil == null) {
                    sMyHttpUtil = new MyHttpUtil();
                }
            }
        }
        return sMyHttpUtil;
    }

    public void startGet(String url, final OnNetResultListener listener) {
        mClient = new OkHttpClient();
        mRequest = new Request.Builder().url(url).build();
        mClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String errMsg = e.getMessage();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailureListener(errMsg);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccessListener(result);
                    }
                });
            }
        });
    }
}
