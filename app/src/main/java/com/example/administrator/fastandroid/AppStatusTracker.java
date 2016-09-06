package com.example.administrator.fastandroid;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.administrator.fastandroid.contansts.ConstantValues;
import com.example.administrator.fastandroid.utils.L;

/**
 * APP状态记录
 * Created by zhatong
 * on 2016/8/1 17:01
 */
public class AppStatusTracker  {
    private static final long MAX_INTERVAL = 5 * 60 * 1000;
    private static AppStatusTracker tracker;
    private Application application;
    private int mAppStatus = ConstantValues.STATUS_ONLINE;
    private boolean isForground;
    private int activeCount;
    private long timestamp;
    private boolean isScreenOff;


   private  AppStatusTracker(){

   }


    public static AppStatusTracker getInstance() {
        if(tracker==null){
            tracker=new AppStatusTracker();
        }
        return tracker;
    }

    public void setAppStatus(int status) {
        this.mAppStatus = status;

    }

    public int getAppStatus() {
        return this.mAppStatus;
    }



}
