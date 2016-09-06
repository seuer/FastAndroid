package com.example.administrator.fastandroid;

import android.support.v7.widget.GridLayoutManager;

import com.example.administrator.fastandroid.base.BaseListActivity;
import com.example.administrator.fastandroid.base.BaseListAdater;

/**
 * 获取底部footer的宽度
 * Created by zhatong
 * on 2016/8/30 17:36
 */
public class FooterSpanSizeLookup extends GridLayoutManager.SpanSizeLookup{
    private BaseListAdater adapter;
    private int spanCount;
    public FooterSpanSizeLookup(BaseListAdater adapter, int spanCount) {
     this.adapter=adapter;
     this.spanCount=spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if(adapter.isLoadMoreFooter(position)){
            return  spanCount;
        }
        return 1;
    }
}
