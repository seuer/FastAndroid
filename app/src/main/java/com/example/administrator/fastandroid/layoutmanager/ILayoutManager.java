package com.example.administrator.fastandroid.layoutmanager;

import android.support.v7.widget.RecyclerView;

/**
 * Created by zt on 16/8/30.
 */
public interface ILayoutManager  {
    RecyclerView.LayoutManager getLayoutManager();
    int findLastVisablePosition();
}
