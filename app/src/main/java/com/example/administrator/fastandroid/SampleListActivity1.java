package com.example.administrator.fastandroid;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.administrator.fastandroid.base.BaseActivity;
import com.example.administrator.fastandroid.utils.L;

/**
 * Created by zhatong
 * on 2016/9/1 18:26
 */
public class SampleListActivity1 extends BaseActivity {

    private SampleListFragment mSampleListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_sample_list_1, R.string.activity_sample);
    }

    @Override
    protected void setUpView() {
        mSampleListFragment =new SampleListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mSampleListFragmentLayout,mSampleListFragment).commit();
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return super.onMenuItemClick(item);
    }
}
