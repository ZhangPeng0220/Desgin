package com.zhangpeng.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 上官若枫 on 2017/2/13.
 */

public class PictureMap {
   static Map<String,Integer> picture ;
    public static  void setPicture(){
        picture = new HashMap<String,Integer>();
        picture.put("暴雨",R.drawable.baoyu);
        picture.put("大雪",R.drawable.daxue);
        picture.put("大雨",R.drawable.dayu);
        picture.put("多云",R.drawable.duoyun);
        picture.put("雷阵雨",R.drawable.leizhenyu);
        picture.put("零散雷雨",R.drawable.lingsanleiyu);
        picture.put("零散阵雨",R.drawable.lingsanzhenyu);
        picture.put("霾",R.drawable.mai);
        picture.put("晴",R.drawable.qing);
        picture.put("少云",R.drawable.shaoyun);
        picture.put("小雪",R.drawable.xiaoxue);
        picture.put("小雨",R.drawable.xiaoyu);
        picture.put("阴",R.drawable.yin);
        picture.put("雨",R.drawable.yu);
        picture.put("雨夹雪",R.drawable.yujiaxue);
        picture.put("阵雪",R.drawable.zhenxue);
        picture.put("阵雨",R.drawable.zhenyu);
        picture.put("中雪",R.drawable.zhongxue);
        picture.put("中雨",R.drawable.zhongyu);
    }
}
