package com.example.administrator.fastandroid;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.fastandroid.api.Api;
import com.example.administrator.fastandroid.base.BaseListActivity;
import com.example.administrator.fastandroid.base.BaseViewHolder;
import com.example.administrator.fastandroid.layoutmanager.ILayoutManager;
import com.example.administrator.fastandroid.layoutmanager.MyStaggeredGridLayoutManager;
import com.example.administrator.fastandroid.model.BaseModel;
import com.example.administrator.fastandroid.model.Benefit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by zhatong
 * on 2016/8/11 11:32
 */
public class SampleListActivity extends BaseListActivity<Benefit> {

    private int page = 1;

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
        if(mDataList == null){
            mDataList=new ArrayList<>();
        }
        if(action == PullRecycler.ACTION_LOAD_MORE_REFRESH){
            page=1;
        }
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);
        Call<BaseModel<ArrayList<Benefit>>> call = api.defaultDenefits(20,page++);
        call.enqueue(new Callback<BaseModel<ArrayList<Benefit>>>() {
                         @Override
                         public void onResponse(Call<BaseModel<ArrayList<Benefit>>> call, Response<BaseModel<ArrayList<Benefit>>> response) {
                             if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
                                 mDataList.clear();
                             }
                             if (response.body().results == null || response.body().results.size() == 0) {
                                 recycler.enableLoadMore(false);
                             } else {
                                 recycler.enableLoadMore(true);
                                 mDataList.addAll(response.body().results);
                                 adapter.notifyDataSetChanged();
                             }
                             recycler.onRefreshCompleted();
                         }

                         @Override
                         public void onFailure(Call<BaseModel<ArrayList<Benefit>>> call, Throwable t) {
                             recycler.onRefreshCompleted();
                         }
                     }
        );

      /*  recycler.postDelayed(new Runnable() {
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
*/

    }


    class SampleViewHolder extends BaseViewHolder {
        TextView textView;
        ImageView imageView;

        public SampleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
            imageView=(ImageView)itemView.findViewById(R.id.mSampleListItemImg);

        }

        @Override
        public void onBindViewHolder(int position) {
          //  String data = mDataList.get(position);
          //  textView.setText(data);
            textView.setVisibility(View.GONE);
            Glide.with(imageView.getContext())
                    .load(mDataList.get(position).url)
                    .centerCrop()
                    .placeholder(R.color.app_primary_color)
                    .crossFade()
                    .into(imageView);
        }

        @Override
        public void onItemClick(View view, int position) {

        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return super.onMenuItemClick(item);
    }
}


