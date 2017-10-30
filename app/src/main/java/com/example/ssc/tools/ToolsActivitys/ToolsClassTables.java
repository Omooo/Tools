package com.example.ssc.tools.ToolsActivitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ssc.tools.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SSC on 2017/10/18.
 */

public class ToolsClassTables extends Activity {

    private EditText mEditTextUsername;
    private EditText mEditTextPwd;
    private Button mButtonLogin;
    String username;
    String password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_classtable);

        initView();
        username = mEditTextUsername.getText().toString();
        password = mEditTextPwd.getText().toString();
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        return null;
                    }
                });
                Request request = new Request.Builder()
                        .url("http://jx.sspu.edu.cn/eams/login.action?username=20154831135&password=150415&encodedPassword=")
                        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                        .build();
                OkHttpClient client = builder.build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("233", "onFailure()");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("233", "onResponse()");
                        String result = response.body().string();
                        Log.i("233--result", result);

                    }
                });

            }
        });
    }

    private void initView() {
        mEditTextUsername = findViewById(R.id.et_username);
        mEditTextPwd = findViewById(R.id.et_pwd);
        mButtonLogin = findViewById(R.id.bt_login);
    }
}
