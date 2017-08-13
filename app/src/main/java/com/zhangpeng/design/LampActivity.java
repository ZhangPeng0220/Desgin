package com.zhangpeng.design;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LampActivity extends AppCompatActivity {
    View view;
    int flag = 0;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lamp);
        view = findViewById(R.id.content_lamp);
        Bitmap bitmap = readBitMap(this,R.drawable.open_lamp);
        BitmapDrawable background = new BitmapDrawable(bitmap);
        view.setBackgroundDrawable(background);
    }
    public void changeState(View v){
        if(flag == 0){
            //关闭
            new Thread(shutTask).start();
            Bitmap bitmap = readBitMap(this,R.drawable.shut_lamp);
            BitmapDrawable background = new BitmapDrawable(bitmap);
            view.setBackgroundDrawable(background);
            flag = 1;
        }else{
            //打开
            new Thread(openTask).start();
            Bitmap bitmap = readBitMap(this,R.drawable.open_lamp);
            BitmapDrawable background = new BitmapDrawable(bitmap);
            view.setBackgroundDrawable(background);
            flag = 0;
        }
    }
    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }
    Runnable openTask = new Runnable() {

        @Override
        public void run() {
            // 在这里进行 http request.网络请求相关操作
            try {
                runNet("http://192.168.2.211/swith1.php?sw=0");
                //Log.d("你妹",stream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    Runnable shutTask = new Runnable() {

        @Override
        public void run() {
            // 在这里进行 http request.网络请求相关操作
            try {
                runNet("http://192.168.2.211/swith1.php?sw=1");
                //Log.d("你妹",stream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    public String runNet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
