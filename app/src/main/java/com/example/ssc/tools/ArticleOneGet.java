package com.example.ssc.tools;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SSC on 2017/9/20.
 */

public class ArticleOneGet extends Activity {

    private TextView mTextTitle;
    private TextView mTextAuthor;
    private TextView mTextContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

        mTextTitle = findViewById(R.id.articleTitle);
        mTextAuthor = findViewById(R.id.articleAuthor);
        mTextContent = findViewById(R.id.articleContent);

        new GetArtical().AccessNetwork();
    }

    //搞事情
    class GetArtical {
        String url = "https://meiriyiwen.com/";
        String title = null;
        String author = null;
        String content = null;

        public void AccessNetwork() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    Document document = Jsoup.parse(result);
                    Elements elementsTitle = document.getElementsByTag("h1");
                    title = elementsTitle.text().toString();
                    Log.i("233--title", title);
                    Elements elementsAuthor = document.select("p.article_author");
                    author = elementsAuthor.text().toString();
                    Log.i("233--author", author);
                    Elements elementsContent = document.select("div.article_text");
                    content = elementsContent.html().toString();
                    Log.i("233--content", content);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextTitle.setText(title);
                            mTextAuthor.setText(author);
                            mTextContent.setText(Html.fromHtml(content, 1));
                        }
                    });
                }
            });
        }
    }

}
