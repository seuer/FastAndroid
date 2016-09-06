package com.example.administrator.fastandroid;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.administrator.fastandroid.base.BaseListAdater;
import com.example.administrator.fastandroid.layoutmanager.ILayoutManager;

/**
 * 封装下拉刷新和加载更多
 * Created by zt on 16/8/24.
 */
public class PullRecycler extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
    private  SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private BaseListAdater adapter;
    /**
     * 下拉刷新
     */
    public static final int ACTION_PULL_TO_REFRESH = 1;
    /**
     * 加载更多
     */
    public static final int ACTION_LOAD_MORE_REFRESH = 2;
    /**
     * 正常状态
     */
    public static final int ACTION_IDLE=0;
    private onRecyclerRefreshListener listener;
    private int mCurrentState=ACTION_IDLE;
    /**
     * 是否可以加载更多
     */
    public  boolean isLoadMoreEnable=false;
    /**
     * 是否可以下拉刷新
     */
    public  boolean isPullToRefresh =true;

    public ILayoutManager layouManager;

    public PullRecycler(Context context) {
        super(context);
        setUpView();
    }

    public PullRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public PullRecycler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    public void setLayoutManager(ILayoutManager manager){
        this.layouManager=manager;
        recyclerView.setLayoutManager(manager.getLayoutManager());
    }

    //分割线
    public void addItemDecoration(RecyclerView.ItemDecoration decoration){
        if(decoration!=null){
            recyclerView.addItemDecoration(decoration);
        }
    }

    public void setAdapter(BaseListAdater adapter){
        this.adapter=adapter;
        recyclerView.setAdapter(adapter);
    }

    public void setRefreshing(){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public  void setOnrefreshListener(onRecyclerRefreshListener listener){
        this.listener=listener;
    }



    private  void setUpView(){
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_refresh,this,true);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mCurrentState==ACTION_IDLE && isLoadMoreEnable && checkLoadMore() ){
                    mCurrentState=ACTION_PULL_TO_REFRESH;
                    adapter.onLoadMoreStateChanged(true);
                    swipeRefreshLayout.setEnabled(false);
                    listener.onRefresh(ACTION_LOAD_MORE_REFRESH);

                }
            }
        });

    }

    /**
     * 什么时候该加载更多数据
     * @return
     */
    private boolean checkLoadMore() {
        int position=layouManager.findLastVisablePosition();
        int totalCount=layouManager.getLayoutManager().getItemCount();//recycleview整个的数据
       return  totalCount - position < 5;
    }

    /**
     * 加载更多开关
     * @param enable
     */
    public  void enableLoadMore(boolean enable){
        isLoadMoreEnable=enable;
    }

    /**
     * 下拉刷新开关
     * @param enbale
     */
    public void enablePullRefresh(boolean enbale){
        isPullToRefresh=enbale;

    }

    @Override
    public void onRefresh() {
         mCurrentState = ACTION_PULL_TO_REFRESH;
        listener.onRefresh(ACTION_PULL_TO_REFRESH);
    }

    public void onRefreshCompleted(){
        switch (mCurrentState){
            case ACTION_PULL_TO_REFRESH:
                swipeRefreshLayout.setRefreshing(false);
                break;
            case ACTION_LOAD_MORE_REFRESH:
                //禁止加载更多
                adapter.onLoadMoreStateChanged(false);
                 if(isPullToRefresh){
                     swipeRefreshLayout.setEnabled(true);
                 }
                break;
        }
        //恢复正常状态
        mCurrentState=ACTION_IDLE;
    }




    public interface  onRecyclerRefreshListener{
        void onRefresh(int action);
    }
}
