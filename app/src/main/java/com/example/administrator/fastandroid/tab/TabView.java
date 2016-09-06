package com.example.administrator.fastandroid.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.fastandroid.R;
import com.example.administrator.fastandroid.base.BaseFragment;

/**
 * Created by zhatong
 * on 2016/9/6 10:43
 */
public class TabView extends LinearLayout implements View.OnClickListener {

    private ImageView tabImg;
    private TextView tabLabel;

    public TabView(Context context) {
        super(context);
        setUpView(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context);
    }


    private void setUpView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_tab_view, this, true);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        tabImg = (ImageView) findViewById(R.id.mTabImg);
        tabLabel = (TextView) findViewById(R.id.mTabLabel);
        setOnClickListener(this);
    }

    public void setUpdata(Tab tab) {
        tabImg.setBackgroundResource(tab.imgId);
        tabLabel.setText(tab.labelResId);
    }

    @Override
    public void onClick(View v) {

    }

    public void onDataChanged(int badgeCount) {
    }

    public void setUpData(Tab tab) {
        tabImg.setBackgroundResource(tab.imgId);
        tabLabel.setText(tab.labelResId);
    }


    public static class Tab {

        public int imgId;
        public int labelResId;
        public int count;
        public int selectItemId;
        public Class<? extends TTabFragment> targetFragmentClazz;

        public Tab(int imgId, int labelResId) {
            this.imgId = imgId;
            this.labelResId = labelResId;
        }

        public Tab(int imgId, int labelResId, int count) {
            this.imgId = imgId;
            this.labelResId = labelResId;
            this.count = count;
        }

        public Tab(int imgId, int labelResId, Class<? extends TTabFragment> targetFragmentClazz) {
            this.imgId = imgId;
            this.targetFragmentClazz = targetFragmentClazz;
            this.labelResId = labelResId;
        }

        public Tab(int selectid, int imgId, int labelResId, Class<? extends TTabFragment> targetFragmentClazz) {
            this.selectItemId = selectid;
            this.imgId = imgId;
            this.targetFragmentClazz = targetFragmentClazz;
            this.labelResId = labelResId;
        }
    }

}
