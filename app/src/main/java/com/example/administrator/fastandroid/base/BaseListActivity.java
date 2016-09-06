package com.example.administrator.fastandroid.base;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.fastandroid.PullRecycler;
import com.example.administrator.fastandroid.R;
import com.example.administrator.fastandroid.layoutmanager.ILayoutManager;
import com.example.administrator.fastandroid.layoutmanager.MyLinearLayoutManager;
import com.example.administrator.fastandroid.layoutmanager.MyStaggeredGridLayoutManager;
import com.example.administrator.fastandroid.widgets.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by zhatong
 * on 2016/8/10 18:05
 */
public abstract class BaseListActivity<T> extends BaseActivity implements PullRecycler.onRecyclerRefreshListener {

    protected BaseListAdater adapter;
    protected ArrayList<T> mDataList;
    protected PullRecycler recycler;


    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, -1);
    }

    @Override
    protected void setUpView() {
        recycler = (PullRecycler) findViewById(R.id.pullRecycler);
    }

    @Override
    protected void setUpData() {
        adapter = new ListAdapter();
        recycler.setOnrefreshListener(this);
        recycler.setLayoutManager(getLayoutManager());
        recycler.addItemDecoration(getItemDecoration());
        recycler.setAdapter(adapter);
    }

    /**
     * Item的样式
     *
     * @return
     */
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getApplicationContext(), R.drawable.list_divider);
    }


    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getApplicationContext());
    }

    public class ListAdapter extends BaseListAdater {
        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
        }

        @Override

        protected int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        protected int getDataCount() {
            return mDataList != null ? mDataList.size() : 0;

        }

        protected int getItemType(int position) {
            return 0;
        }


    }




    protected int getItemType(int position) {
        return 0;
    }


    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

    private class LoadMoreFooterViewHolder extends BaseViewHolder {
        public LoadMoreFooterViewHolder(View view) {
            super(view);
        }

        @Override
        public void onBindViewHolder(int position) {

        }

        @Override
        public void onItemClick(View view, int position) {

        }
    }
}
