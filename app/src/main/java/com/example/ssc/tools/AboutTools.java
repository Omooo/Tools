package com.example.ssc.tools;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.sumimakito.awesomeqr.AwesomeQRCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SSC on 2017/9/22.
 */

public class AboutTools extends Activity {

    private ImageView mimageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);

        mimageView = findViewById(R.id.qrcode);
    }

    public void shareApp(View view) {
        /**
         * 待优化部分
         * 略显卡顿
         */
        //生成二维码
        Bitmap image_bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.qrcode_background);
        String url = "http://www.wooyun.site/";
        new AwesomeQRCode.Renderer()
                .contents(url)
                .background(image_bitmap)
                .size(500).margin(20)
                .renderAsync(new AwesomeQRCode.Callback() {
                    @Override
                    public void onRendered(AwesomeQRCode.Renderer renderer, final Bitmap bitmap) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Tip: here we use runOnUiThread(...) to avoid the problems caused by operating UI elements from a non-UI thread.
                                mimageView.setImageBitmap(bitmap);
                            }
                        });
                    }

                    @Override
                    public void onError(AwesomeQRCode.Renderer renderer, Exception e) {
                        e.printStackTrace();
                    }
                });
        //分享
        if (mimageView.getDrawable() == null) {
            return;
        }
        Bitmap qrCodeBitmap = ((BitmapDrawable)mimageView.getDrawable()).getBitmap();
        final File qrImage = new File(Environment.getExternalStorageDirectory(), "qrcode.jpg");
        if(qrImage.exists())
        {
            qrImage.delete();
        }
        try {
            qrImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(qrImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(qrCodeBitmap == null)
        {
            return;
        }
        qrCodeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Toast.makeText(this, "保存成功 " + qrImage.getPath().toString(), Toast.LENGTH_SHORT).show();
        String picPath = qrImage.getPath();
        Log.i("2333", picPath);
        Uri imageUri = Uri.fromFile(new File(picPath));
        Log.i("2333", imageUri.toString());
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent,"分享到 "));

    }

    public void goToGithub(View v) {
        Uri uri = Uri.parse("https://github.com/Omooo/Tools");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void goToMyBlog(View v) {
        Uri uri = Uri.parse("http://omooo.top/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
