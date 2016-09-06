package com.example.administrator.fastandroid;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.fastandroid.base.BaseListActivity;
import com.example.administrator.fastandroid.base.BaseViewHolder;
import com.example.administrator.fastandroid.layoutmanager.ILayoutManager;
import com.example.administrator.fastandroid.layoutmanager.MyStaggeredGridLayoutManager;

import java.util.ArrayList;


/**
 * Created by zhatong
 * on 2016/8/11 11:32
 */
public class SampleListActivity extends BaseListActivity<String> {


    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.activity_sample);

    }


    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.setRefreshing();

    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    protected ILayoutManager getLayoutManager() {
        return new MyStaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void onRefresh(final int action) {
        //模拟网络请求数据，等待3s
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mDataList==null) {
                    mDataList = new ArrayList<>();
                }
                //状态如果处于下拉刷新，要清理之前的数据，防止数据重复
                if(action == PullRecycler.ACTION_PULL_TO_REFRESH) {
                    mDataList.clear();
                }
                //每一次加载更多的数据的数量
                int loadSizeOnce=mDataList.size();
                for (int i = loadSizeOnce; i < loadSizeOnce + 20; i++) {
                    mDataList.add("sample list item" + i);

                }
                adapter.notifyDataSetChanged();
                recycler.onRefreshCompleted();
                if(mDataList.size()< 100 ){
                    recycler.enableLoadMore(true);
                }else{
                    recycler.enableLoadMore(false);
                }
            }
        },3000);


    }


    class SampleViewHolder extends BaseViewHolder {
        TextView textView;

        public SampleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
        }

        @Override
        public void onBindViewHolder(int position) {
            String data = mDataList.get(position);
            textView.setText(data);
        }


    }


}


