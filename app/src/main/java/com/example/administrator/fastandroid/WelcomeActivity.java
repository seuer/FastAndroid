package com.example.administrator.fastandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;

import com.example.administrator.fastandroid.base.BaseActivity;
import com.example.administrator.fastandroid.contansts.ConstantValues;


/**
 * 欢迎界面
 * Created by Administrator
 * on 2016/8/1 17:01
 */
public class WelcomeActivity extends BaseActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        AppStatusTracker.getInstance().setAppStatus(ConstantValues.STATUS_OFFLINE);
        super.onCreate(savedInstanceState);

    }



    @Override
    protected void setUpData() {
        setContentView(R.layout.activity_welcome,R.string.activity_welcome,MODE_NONE);
        handler.sendEmptyMessageAtTime(0,5000);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
            finish();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
