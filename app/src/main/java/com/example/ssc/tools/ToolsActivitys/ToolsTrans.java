package com.example.ssc.tools.ToolsActivitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ssc.tools.R;
import com.example.ssc.tools.utils.FastJsonGroup;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SSC on 2017/11/10.
 */

public class ToolsTrans extends Activity {

    private EditText mEditText;
    private Button mButton;
    private TextView mTextView;
    private static final String url = "http://fanyi.youdao.com/openapi.do?keyfrom=Skykai521&key=977124034&type=data&doctype=json&version=1.1&q=";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_translate);

        initView();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJson();
            }
        });
    }

    public void getJson() {
        final String text = mEditText.getText().toString();
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url + text).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().string();
                Log.i("233--jsonString", jsonString);
                JSONObject object = JSON.parseObject(jsonString);
                JSONObject object1 = (JSONObject) object.get("basic");
                JSONArray jsonArray = object1.getJSONArray("explains");
                JSONArray array = object.getJSONArray("translation");
                final String result = array.toJSONString() + jsonArray.toJSONString();
                Log.i("233", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(result);
                    }
                });
                String test = "{\"query\":\"ill\",\"errorCode\":0}";
                FastJsonGroup group = JSON.parseObject(test, FastJsonGroup.class);
                System.out.println("ErrorCode: "+group.getErrorCodeQuery() +"  Query: "+ group.getQuery());

            }
        });
    }
    private void initView() {
        mEditText = findViewById(R.id.et_trans);
        mButton = findViewById(R.id.btn_trans);
        mTextView = findViewById(R.id.tv_translateResult);

    }
}
