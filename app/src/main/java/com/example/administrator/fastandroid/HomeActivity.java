package com.example.administrator.fastandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.fastandroid.banner.BannerActivity;
import com.example.administrator.fastandroid.base.BaseActivity;
import com.example.administrator.fastandroid.base.BaseListActivity;
import com.example.administrator.fastandroid.base.BaseViewHolder;
import com.example.administrator.fastandroid.contansts.ConstantValues;
import com.example.administrator.fastandroid.model.Module;

import java.util.ArrayList;

/**
 * 主界面
 * Created by Administrator
 * on 2016/8/1 17:01
 */
public class HomeActivity extends BaseListActivity<Module> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.activity_home);
    }


    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.enablePullRefresh(false);
        recycler.setRefreshing();

    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_item, parent, false);
        return new ViewHolder(view);

    }

    private class ViewHolder extends BaseViewHolder {
        private final TextView label;

        public ViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.mHomeItemModuleLabel);
        }

        @Override
        public void onItemClick(View view, int position) {
            startActivity(new Intent(HomeActivity.this, mDataList.get(position).moduleTargetClass));
        }

        @Override
        public void onBindViewHolder(int position) {
            label.setText(mDataList.get(position).moduleName);
        }
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
    public void onRefresh(int action) {
        try {
            mDataList = new ArrayList<>();
            mDataList.add(new Module("RecyclerView基于BaseListActivity\n支持下拉刷新,加载更多", SampleListActivity.class));
            mDataList.add(new Module("RecyclerView基于BaseListFragment\n支持下拉刷新,加载更多", SampleListActivity1.class));
           // mDataList.add(new Module("banner", BannerActivity.class));
            recycler.onRefreshCompleted();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return super.onMenuItemClick(item);
    }
}
