package com.example.administrator.fastandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.fastandroid.base.BaseActivity;
import com.example.administrator.fastandroid.contansts.ConstantValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 登录界面
 * Created by Administrator
 * on 2016/8/2 10:56
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setUpData() {
        setContentView(R.layout.activity_login, R.string.activity_login);
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
             AppStatusTracker.getInstance().setAppStatus(ConstantValues.STATUS_ONLINE);
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return super.onMenuItemClick(item);
    }
}
