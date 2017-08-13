package com.zhangpeng.design;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TempActivity extends AppCompatActivity {
    Gson gson;
    MyObject jsonObject;
    OkHttpClient client;
    String stream;
    String temper = null;
    TextView tv;
    public static final int UPDATA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_temp);
        client = new OkHttpClient();
        tv = (TextView) findViewById(R.id.temText);
        //MyObject tempObject = new ConnectBerry().connect();

    }
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // 在这里进行 http request.网络请求相关操作
            try {
                stream = runNet("http://192.168.2.211/test.php");
                Log.d("你妹",stream);
                temper =  parseJson(stream);
                Message message = new Message();
                message.what = UPDATA;
                handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATA:
                    setdata();
                    break;
                default:
                    break;
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
    public void tempRefresh(View v){
        new Thread(networkTask).start();
        //MyObject tempObject = new ConnectBerry().connect();
        //Log.d("温度",tempObject.temp+"");
    }
    private String parseJson(String jsonData){
           String[] s1 = new String[2];
            String[] s2 = new String[2];
            s1 = jsonData.split("\\{");
            s2 = s1[1].split("\\}");
            String json = "{"+s2[0]+"}";
             String tem = null;
            //String json = "{\"responseData\": 1, \"responseDetails\": \"This API is no longer available.\", \"responseStatus\": 403}";
            try {
                JSONObject jsonObject = new JSONObject(json);
               tem = jsonObject.getString("temp");
                String red = jsonObject.getString("red");



            } catch (JSONException e) {
                e.printStackTrace();
            }
        return tem;
    }
    public void setdata(){
        tv.setText(temper+"℃");
    }
}
