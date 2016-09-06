package com.example.administrator.fastandroid.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 * 底部导航，类似于微信
 * Created by zhatong
 * on 2016/9/6 10:58
 */
public class TabBottomNavLayout extends LinearLayout implements View.OnClickListener {
    private ArrayList<TabView.Tab> tabsList = new ArrayList<>();
    private OnTabClickListener listener;
    private  int tabCount;
    private View selectView;


    public TabBottomNavLayout(Context context) {
        super(context);
        setUpView();
    }

    public TabBottomNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public TabBottomNavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    private void setUpView() {
        setOrientation(HORIZONTAL);
    }

    //把导航菜单加入
    public void setUpdata(ArrayList<TabView.Tab> tabs, OnTabClickListener listener) {
        this.tabsList = tabs;
        this.listener = listener;
        if (tabs != null && tabs.size() > 0) {
            TabView tabView = null;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            for (int i = 0; i < tabs.size(); i++) {
                tabView = new TabView(getContext());
                tabView.setTag(tabs.get(i));
                tabView.setUpdata(tabs.get(i));
                tabView.setOnClickListener(this);
                addView(tabView, params);
            }

        } else {
            throw new IllegalArgumentException("tabs can not be empty");
        }
    }

    public  void setCurrentTab( int i){
        if(i < tabCount && i>0){
            View view=getChildAt(i);
            onClick(view);
        }
    }


    @Override
    public void onClick(View v) {
        if (selectView != v) {
            listener.onTabClick((TabView.Tab) v.getTag());
            v.setSelected(true);
            if (selectView != null) {
                selectView.setSelected(false);
            }
            selectView = v;
        }

    }

    public  void onDataChanged(int i){
       if( i< tabCount && i>=0) {
           TabView view = (TabView) getChildAt(i);
       }
    }




    public interface OnTabClickListener {
        void onTabClick(TabView.Tab tab);

    }
}
