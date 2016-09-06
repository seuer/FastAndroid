package com.example.administrator.fastandroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.fastandroid.AppStatusTracker;
import com.example.administrator.fastandroid.HomeActivity;
import com.example.administrator.fastandroid.R;
import com.example.administrator.fastandroid.WelcomeActivity;
import com.example.administrator.fastandroid.contansts.ConstantValues;

/**
 * Created by Administrator
 * on 2016/8/1 17:01
 */
public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    protected Toolbar toolbar;
    protected TextView toolbar_title;
    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MODE_NONE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (AppStatusTracker.getInstance().getAppStatus()) {
            case ConstantValues.STATUS_FORCE_KILLED:
                protectApp();
                break;
            case ConstantValues.ACTION_KICK_OUT:
                kickOut();
                break;
            case ConstantValues.ACTION_LOGOUT:
            case ConstantValues.STATUS_OFFLINE:
                setUpContentView();
                setUpView();
                setUpData();
                break;
        }

    }

    protected  void setUpContentView(){

    };


    protected  void setUpView(){

    };


    protected  void setUpData(){


    };

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(layoutResID, 0, MODE_BACK);
    }

    protected void setContentView(int layouResid, int titleId) {
        setContentView(layouResid, titleId, MODE_BACK);
    }

    protected void setContentView(int layoutResid, int titleId, int mode) {
        super.setContentView(layoutResid);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar.setTitle("");


        switch (mode) {
            case MODE_BACK:
                toolbar.setNavigationIcon(R.mipmap.ic_toolbar_back);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case MODE_DRAWER:
                break;
            case MODE_NONE:
                break;


        }
        setUpTitle(titleId);
    }


    protected void setupData(Bundle bundle) {
    }

    protected void setUpTitle(int titleResId) {
        if (titleResId > 0 && toolbar_title != null) {
            toolbar_title.setText(titleResId);
        }
    }

    protected void setUpMenu(int menuId) {
        if (toolbar != null) {
            toolbar.getMenu().clear();
            if (menuId > 0) {
                toolbar.inflateMenu(menuId);
                toolbar.setOnMenuItemClickListener(this);
            }
        }
    }




    protected void kickOut() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_KICK_OUT);
        startActivity(intent);
    }

    protected void protectApp() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_RESTART_APP);
        startActivity(intent);
    }


}
