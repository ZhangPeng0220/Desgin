package com.zhangpeng.design;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 上官若枫 on 2017/4/12.
 */

public class ConnectBerry {
    Gson gson;
    MyObject jsonObject;
    OkHttpClient client;
    String stream;

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // 在这里进行 http request.网络请求相关操作
            try {
                stream = runNet("http://192.168.137.2/test.php");
                Log.d("你妹",stream);
                parseJson(stream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    public MyObject connect(){
        client = new OkHttpClient();
        new Thread(networkTask).start();
        return jsonObject;
    }
    private void parseJson(String jsonData){
        String[] s1 = new String[2];
        String[] s2 = new String[2];
        s1 = jsonData.split("\\{");
        s2 = s1[1].split("\\}");
        String json = "{"+s2[0]+"}";
        //String json = "{\"responseData\": 1, \"responseDetails\": \"This API is no longer available.\", \"responseStatus\": 403}";
        try {
            JSONObject jsonObject = new JSONObject(json);
            int id = jsonObject.getInt("id");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
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
