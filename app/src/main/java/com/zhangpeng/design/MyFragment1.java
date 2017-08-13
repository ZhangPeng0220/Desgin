package com.zhangpeng.design;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/8/9.
 */

    public class MyFragment1 extends Fragment {
        Gson gson;
        MyAdapter myAdapter = new MyAdapter();
        private FragmentManager fm;
        private FragmentTransaction ft;
        private RecyclerView recyclerView;
            OkHttpClient client;
            String stream;
            JsonObject jsonObject;
            TextView temperature,weather,distrct,wind,airCondition,tomorrow,tomotem,aftertomo,aftertem,future,fututem;
            ImageView imageView,tomoimage,afterimage,futuimage;
            public static final int UPDATA = 1;
            @Override
            public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.tab01, container, false);
                initView(view,container);
                fm = getFragmentManager();
                client = new OkHttpClient();
                new Thread(networkTask).start();
                myAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener(){
                    @Override
                    public void onItemClick(View view , String data){
                        if(data == "台灯"){
                           Intent intent=new Intent(getActivity(), LampActivity.class);
                            startActivity(intent);
                        }if(data == "空调"){
                            Intent intent=new Intent(getActivity(), TempActivity.class);
                            startActivity(intent);
                        }if(data == "监控"){
                            Intent intent=new Intent(getActivity(), WarningActivity.class);
                            startActivity(intent);
                        }

                        else
                            Toast.makeText(container.getContext(), data, Toast.LENGTH_SHORT).show();
                    }
                });
                return view;
            }
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
    //请求网络
        public String runNet(String url) throws IOException {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
        Runnable networkTask = new Runnable() {

            @Override
            public void run() {
                // 在这里进行 http request.网络请求相关操作
                try {
                    stream = runNet("http://apicloud.mob.com/v1/weather/query?key=1b2fadfe8d9a8"+comBine("沈阳","辽宁"));
                    Log.d("abc",stream);
                    prase(stream);
                    //setdata();不能在非UI线程更新UI,不安全，需要用handler
                    Message message = new Message();
                    message.what = UPDATA;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
     //将链接补全
        public String comBine(String city,String province){
            try {
                city = URLEncoder.encode(city,"UTF-8");
                province = URLEncoder.encode(province,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "&city="+ city + "&province=" + province;
        }
    //解析字符串
        public void prase(String stream){
            gson = new Gson();
            jsonObject = gson.fromJson(stream, JsonObject.class);
        }
    //将数据显示在UI上
        public void setdata(){
            JsonObject.Result rs = jsonObject.result.get(0);
            String tem = rs.temperature;
            temperature.setText(tem);
            String weatherInfo = rs.weather;
            weather.setText(weatherInfo);
            distrct.setText(rs.distrct);
            wind.setText(rs.wind);
            airCondition.setText(rs.airCondition);
            PictureMap.setPicture();//将数据绑定给MAP
            if(PictureMap.picture.containsKey(weatherInfo)){
                imageView.setImageResource(PictureMap.picture.get(weatherInfo));
            }else {
                imageView.setImageResource(R.drawable.undefined);
            }
           setfuture(rs);
        }

    private void setfuture(JsonObject.Result rs) {
        tomorrow.setText(rs.future.get(0).week);
        tomotem.setText(rs.future.get(0).temperature);
        PictureMap.setPicture();
        if(PictureMap.picture.containsKey(rs.future.get(0).dayTime)){
            tomoimage.setImageResource(PictureMap.picture.get(rs.future.get(0).dayTime));
        }else {
            tomoimage.setImageResource(R.drawable.undefined);
        }
        //aftertomo.setText(rs.future.get(1).week);
        aftertem.setText(rs.future.get(1).temperature);
        if(PictureMap.picture.containsKey(rs.future.get(1).dayTime)){
            afterimage.setImageResource(PictureMap.picture.get(rs.future.get(1).dayTime));
        }else {
            afterimage.setImageResource(R.drawable.undefined);
        }
        //future.setText(rs.future.get(2).week);
        fututem.setText(rs.future.get(2).temperature);
        if(PictureMap.picture.containsKey(rs.future.get(2).dayTime)){
            futuimage.setImageResource(PictureMap.picture.get(rs.future.get(2).dayTime));
        }else {
            futuimage.setImageResource(R.drawable.undefined);
        }
    }
    //初始化布局
    public void initView(View view,ViewGroup container){
        temperature = (TextView) view.findViewById(R.id.temperature);
        imageView = (ImageView) view.findViewById(R.id.picture);
        imageView.setImageResource(R.drawable.undefined);
        weather = (TextView) view.findViewById(R.id.weather);
        distrct = (TextView) view.findViewById(R.id.distrct);
        wind = (TextView) view.findViewById(R.id.wind);
        airCondition = (TextView) view.findViewById(R.id.airCondition);

        //将来天气
        tomorrow = (TextView)view.findViewById(R.id.tomorrow);
        tomotem = (TextView)view.findViewById(R.id.tomotem);
        aftertomo = (TextView)view.findViewById(R.id.aftertomo);
        aftertem = (TextView)view.findViewById(R.id.aftertem);
        future = (TextView)view.findViewById(R.id.future);
        fututem = (TextView)view.findViewById(R.id.fututem);
        tomoimage = (ImageView) view.findViewById(R.id.tomoimage);
        tomoimage.setImageResource(R.drawable.undefined);
        afterimage = (ImageView) view.findViewById(R.id.afterimage);
        afterimage.setImageResource(R.drawable.undefined);
        futuimage = (ImageView) view.findViewById(R.id.futuimage);
        futuimage.setImageResource(R.drawable.undefined);

        //recyclerview
        recyclerView =(RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));
        recyclerView .addItemDecoration(new DividerGridItemDecoration(container.getContext() ));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(myAdapter);
    }
}

