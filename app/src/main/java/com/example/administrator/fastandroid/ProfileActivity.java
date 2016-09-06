package com.example.administrator.fastandroid;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.fastandroid.base.BaseActivity;

/**
 * 简洁界面
 * Created by zhatong
 * on 2016/8/2 11:05
 */
public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setUpData() {
        setContentView(R.layout.activity_profile,R.string.activity_profile);
        TextView textView=(TextView)findViewById(R.id.profile);
        textView.setText(CustomApplication.list.toString());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
