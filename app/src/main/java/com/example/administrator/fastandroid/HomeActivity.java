package com.example.administrator.fastandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.administrator.fastandroid.base.BaseActivity;
import com.example.administrator.fastandroid.contansts.ConstantValues;

import java.util.ArrayList;

/**
 * 主界面
 * Created by Administrator
 * on 2016/8/1 17:01
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected void setUpData() {
        super.setUpData();
        setContentView(R.layout.activity_home, R.string.activity_home);
        Button button = (Button) findViewById(R.id.btn_index);
        button.setOnClickListener(this);
        CustomApplication.list = new ArrayList<>();
        CustomApplication.list.add("zhagege");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int action = intent.getIntExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_BACK_TO_HOME);
        switch (action) {
            case ConstantValues.ACTION_KICK_OUT:
                break;
            case ConstantValues.ACTION_LOGOUT:
                break;
            case ConstantValues.ACTION_RESTART_APP:
                protectApp();
                break;
            case ConstantValues.ACTION_BACK_TO_HOME:
                break;
        }

    }

    //本身就在homeActivity界面
    @Override
    protected void protectApp() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_index) {
            startActivity(new Intent(this, SampleListActivity.class));
            finish();
        }
    }
}
