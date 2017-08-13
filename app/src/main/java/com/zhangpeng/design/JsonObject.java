package com.zhangpeng.design;

import java.util.List;

/**
 * Created by 上官若枫 on 2017/2/9.
 */

public class JsonObject {
   /* {
        "msg": "success",
            "result": [
        {
            "airCondition": "良",
                "city": "北京",
                "coldIndex": "低发期",
                "updateTime": "20150908153820",
                "date": "2015-09-08",
                "distrct": "门头沟",
                "dressingIndex": "短袖类",
                "exerciseIndex": "适宜",
                "future": [
            {
                "date": "2015-09-09",
                    "dayTime": "阵雨",
                    "night": "阴",
                    "temperature": "24°C/18°C",
                    "week": "星期三",
                    "wind": "无持续风向小于3级"
            },
            {
                "date": "2015-09-10",
                    "dayTime": "阵雨",
                    "night": "阵雨",
                    "temperature": "22°C/15°C",
                    "week": "星期四",
                    "wind": "无持续风向小于3级"
            },
            {
                "date": "2015-09-11",
                    "dayTime": "阴",
                    "night": "晴",
                    "temperature": "23°C/15°C",
                    "week": "星期五",
                    "wind": "北风3～4级无持续风向小于3级"
            },
            {
                "date": "2015-09-12",
                    "dayTime": "晴",
                    "night": "晴",
                    "temperature": "26°C/13°C",
                    "week": "星期六",
                    "wind": "北风3～4级无持续风向小于3级"
            },
            {
                "date": "2015-09-13",
                    "dayTime": "晴",
                    "night": "晴",
                    "temperature": "27°C/16°C",
                    "week": "星期日",
                    "wind": "无持续风向小于3级"
            },
            {
                "date": "2015-09-14",
                    "dayTime": "晴",
                    "night": "多云",
                    "temperature": "27°C/16°C",
                    "week": "星期一",
                    "wind": "无持续风向小于3级"
            },
            {
                "date": "2015-09-15",
                    "dayTime": "少云",
                    "night": "晴",
                    "temperature": "26°C/14°C",
                    "week": "星期二",
                    "wind": "南风3级南风2级"
            },
            {
                "date": "2015-09-16",
                    "dayTime": "局部多云",
                    "night": "少云",
                    "temperature": "26°C/15°C",
                    "week": "星期三",
                    "wind": "南风3级南风2级"
            },
            {
                "date": "2015-09-17",
                    "dayTime": "阴天",
                    "night": "局部多云",
                    "temperature": "26°C/15°C",
                    "week": "星期四",
                    "wind": "东南风2级"
            }
            ],
            "humidity": "湿度：46%",
                "province": "北京",
                "sunset": "18:37",
                "sunrise": "05:49",
                "temperature": "25℃",
                "time": "14:35",
                "washIndex": "不适宜",
                "weather": "多云",
                "week": "周二",
                "wind": "南风2级"
        }
        ],
        "retCode": "200"
    }*/
    //result是一个数组里边就存了一个对象,这个对象里边有属性还有一个list就是那个future

    String msg;
    List<Result> result;
    String retCode;
    public static class Result{
        String airCondition;
        String city;
        String coldIndex;
        String updateTime;
        String date;
        String distrct;
        String dressingIndex;
        String exerciseIndex;
        List<Future> future;
        String humidity;
        String province;
        String sunset;
        String sunrise;
        String temperature;
        String time;
        String washIndex;
        String weather;
        String week;
        String wind;
    }

    public static class Future{

        /*"date": "2015-09-09",
          "dayTime": "阵雨",
          "night": "阴",
          "temperature": "24°C/18°C",
          "week": "星期三",
          "wind": "无持续风向小于3级"*/
        String data;
        String dayTime;
        String night;
        String temperature;
        String week;
        String wind;
    }

}
