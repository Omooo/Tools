package com.example.ssc.tools;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SSC on 2017/9/18.
 */

public class EnglishOneGet extends Activity {

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;

    String date = null;
    String content = null;
    String note = null;
    String translation = null;
    String translations = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.englishget);

        mTextView1 = findViewById(R.id.textView1);
        mTextView2 = findViewById(R.id.textView2);
        mTextView3 = findViewById(R.id.textView3);
        mTextView4 = findViewById(R.id.textView4);

        resourceProvider();
    }

    //获取资源
    private void resourceProvider() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://sentence.iciba.com/index.php?callback=jQuery19002058632025712177_1505711964243&c=dailysentence&m=getTodaySentence&_=1505711964245")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                int length = result.length();
                //改成标准的Json数据格式
                String result_json = result.substring(41, length - 1);
                Log.i("233--result_json", result_json);
                try {
                    JSONObject object = new JSONObject(result_json);
                    if (object.has("title")) {
                        date = object.getString("title");
                        Log.i("233--title", date);
                    }
                    content = object.getString("content");
                    Log.i("233--content", content);
                    note = object.getString("note");
                    Log.i("233--note", note);
                    translation = object.getString("translation");
                    translations = translation.substring(5, translation.length());
                    Log.i("233--translations", translations);
                    //更新UI
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView1.setText("     "+content);
                            mTextView2.setText("     "+note);
                            mTextView3.setText("     每日小编："+translations);
                            mTextView4.setText("更新至 "+date+"  ");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
