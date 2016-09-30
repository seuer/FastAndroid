package com.example.administrator.fastandroid.banner;

import android.view.MenuItem;

import com.example.administrator.fastandroid.R;
import com.example.administrator.fastandroid.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by zhatong
 * on 2016/9/18 16:13
 */
public class BannerActivity extends BaseActivity implements  BannerView.OnBannerItemClickListener {

    private  BannerView mBannerView;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_banner);
    }

    @Override
    protected void setUpView() {
     mBannerView= (BannerView) findViewById(R.id.banner_view);

    }

    @Override
    protected void setUpData() {
        ArrayList<BannerView.Banner> list= new ArrayList<>();
        list.add(new BannerView.Banner(R.mipmap.ic_tab_contact));
        list.add(new BannerView.Banner(R.mipmap.ic_tab_moments));
        list.add(new BannerView.Banner(R.mipmap.ic_tab_msg));
        list.add(new BannerView.Banner(R.mipmap.ic_tab_profile));
        mBannerView.setUpData(list,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBannerView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBannerView.onStop();
    }



    @Override
    public void setOnBannerClick(int index, BannerView.Banner banner) {

    }
}
