package com.example.administrator.fastandroid.base;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.fastandroid.PullRecycler;
import com.example.administrator.fastandroid.R;
import com.example.administrator.fastandroid.layoutmanager.ILayoutManager;
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
        adapter = new BaseListAdater();
        recycler.setOnrefreshListener(this);
        recycler.setLayoutManager(getLayoutManager());
        adapter = new BaseListAdater();
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
        return new MyStaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    }


    public class BaseListAdater extends RecyclerView.Adapter<BaseViewHolder> {

        private static final int VIEW_TYPE_MORE_FOOTER = 1024;
        private boolean isShowLoadMoreFooter;

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //return  getViewHolder(parent,viewType);
            if (viewType == VIEW_TYPE_MORE_FOOTER) {
                return getLoadMoreFooter(parent);
            }
            return getViewHolder(parent, viewType);
        }

        //显示【加载更多】布局
        private BaseViewHolder getLoadMoreFooter(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_footer, parent, false);
            return new LoadMoreFooterViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            // holder.onBindViewHolder(position);
            if (isShowLoadMoreFooter && position == getItemCount() - 1) {
                if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                    params.setFullSpan(true);
                }
            }
            holder.onBindViewHolder(position);

        }

        @Override
        public int getItemCount() {

            // return mDataList!=null ? mDataList.size(): 0;
            return mDataList != null ? mDataList.size() + (isShowLoadMoreFooter ? 1 : 0) : 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (isShowLoadMoreFooter && position == getItemCount() - 1) {
                return VIEW_TYPE_MORE_FOOTER;
            }
            return getItemType(position);
        }

       //是否显示加载更多布局
        public void showLoadMoreFooter(boolean isShow) {
            isShowLoadMoreFooter = isShow;
            if (isShow) {
                notifyItemInserted(getItemCount());
            } else {
                notifyItemRemoved(getItemCount());
            }
        }

        //是否显示底部布局
        public boolean isFooterView(int position) {
            return isShowLoadMoreFooter && position == getItemCount() - 1;
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
    }
}
