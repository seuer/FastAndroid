package com.example.administrator.fastandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.fastandroid.PullRecycler;
import com.example.administrator.fastandroid.R;
import com.example.administrator.fastandroid.layoutmanager.ILayoutManager;
import com.example.administrator.fastandroid.layoutmanager.MyLinearLayoutManager;
import com.example.administrator.fastandroid.widgets.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by zhatong
 * on 2016/8/31 16:47
 */
public abstract class BaseListFragment<T> extends BaseFragment implements PullRecycler.onRecyclerRefreshListener {

    protected ArrayList<T> mDataList;
    protected PullRecycler recycler;
    protected ListFragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = (PullRecycler) view.findViewById(R.id.pullRecycler);
        setUpAdaper();
        recycler.setOnrefreshListener(this);
        recycler.setLayoutManager(getLayoutManager());
        recycler.addItemDecoration(getItemDecoration());
        recycler.setAdapter(adapter);

    }

    protected void setUpAdaper() {
        adapter = new ListFragmentAdapter();
    }

    @Override
    public void onRefresh(int action) {

    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getContext());
    }

    protected RecyclerView.ItemDecoration getItemDecoration(){
        return  new DividerItemDecoration(getContext(),R.drawable.list_divider);
    }

    public class ListFragmentAdapter extends BaseListAdater {
        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
        }

        @Override
        protected int getDataCount() {
            return mDataList != null ? mDataList.size() : 0;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }
    }


    public int getItemType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);


}
