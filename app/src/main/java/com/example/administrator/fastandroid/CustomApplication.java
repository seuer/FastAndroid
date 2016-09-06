package com.example.administrator.fastandroid;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Administrator
 * on 2016/8/1 17:57
 */
public class CustomApplication extends Application {
    /**
     * APP异常状况
     */
    public static ArrayList<String> list;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
