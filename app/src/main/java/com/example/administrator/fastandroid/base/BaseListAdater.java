package com.example.administrator.fastandroid.base;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.fastandroid.R;

/**
 * Created by zhatong
 * on 2016/8/31 16:55
 */
public abstract class BaseListAdater extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_MORE_FOOTER = 1024;
    private boolean isLoadMoreFooterShown;

    @Override

    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return  getViewHolder(parent,viewType);
        if (viewType == VIEW_TYPE_MORE_FOOTER) {
            return getLoadMoreFooter(parent);
        }
        return onCreateNormalViewHolder(parent, viewType);
    }


    protected int getDataViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (isLoadMoreFooterShown && position == getItemCount() - 1) {
            if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams params =
                        (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                params.setFullSpan(true);
            }
        }
        holder.onBindViewHolder(position);

    }

    @Override
    public int getItemCount() {
        return getDataCount() + (isLoadMoreFooterShown ? 1 : 0);
    }

    protected abstract int getDataCount();

    protected abstract BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);

    //显示【加载更多】布局
    private BaseViewHolder getLoadMoreFooter(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_footer, parent, false);
        return new LoadMoreFooterViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadMoreFooterShown && position == getItemCount() - 1) {
            return VIEW_TYPE_MORE_FOOTER;
        }
        return getItemType(position);
    }

    //是否显示加载更多布局
    public void onLoadMoreStateChanged(boolean isShown) {
        this.isLoadMoreFooterShown = isShown;
        if (isShown) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount());

        }

    }

    //是否显示底部布局
    public boolean isLoadMoreFooter (int position) {
        return isLoadMoreFooterShown && position == getItemCount() - 1;
    }

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

    protected int getItemType(int position) {
        return 0;
    }


}



