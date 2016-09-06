package com.example.administrator.fastandroid;

import android.view.MenuItem;

import com.example.administrator.fastandroid.base.BaseActivity;
import com.example.administrator.fastandroid.tab.TTabFragment;
import com.example.administrator.fastandroid.tab.TabBottomNavLayout;
import com.example.administrator.fastandroid.tab.TabView;

import java.util.ArrayList;

/**
 * APP常见首页
 * Created by zhatong
 * on 2016/9/6 11:33
 */
public class SampleTabActivity extends BaseActivity implements TabBottomNavLayout.OnTabClickListener {
    private TabBottomNavLayout tabLayout;
    private TTabFragment tTabFragment;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_tab, R.string.activity_home);
    }

    @Override
    protected void setUpView() {
        tabLayout = (TabBottomNavLayout) findViewById(R.id.tabLayout);
    }

    @Override
    protected void setUpData() {
        ArrayList<TabView.Tab> list = new ArrayList<>();
        list.add(new TabView.Tab(R.drawable.selector_tab_msg, R.string.msg, SampleListFragment.class));
        list.add(new TabView.Tab(R.drawable.selector_tab_contact, R.string.contact, SampleListFragment.class));
        list.add(new TabView.Tab(R.drawable.selector_tab_moments, R.string.discovery, SampleListFragment.class));
        list.add(new TabView.Tab(R.drawable.selector_tab_profile, R.string.mine, SampleListFragment.class));
        tabLayout.setUpdata(list, this);
        tabLayout.setCurrentTab(0);

    }

    @Override
    public void onTabClick(TabView.Tab tab) {

        try {
            setUpTitle(tab.labelResId);
            tTabFragment = tab.targetFragmentClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, tTabFragment.getFragment()).commitAllowingStateLoss();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
