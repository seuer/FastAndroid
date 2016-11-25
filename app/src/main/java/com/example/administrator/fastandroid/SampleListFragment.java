package com.example.administrator.fastandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.fastandroid.api.Api;
import com.example.administrator.fastandroid.base.BaseFragment;
import com.example.administrator.fastandroid.base.BaseListFragment;
import com.example.administrator.fastandroid.base.BaseViewHolder;
import com.example.administrator.fastandroid.contansts.ConstantValues;
import com.example.administrator.fastandroid.layoutmanager.ILayoutManager;
import com.example.administrator.fastandroid.layoutmanager.MyGridLayoutManager;
import com.example.administrator.fastandroid.model.BaseModel;
import com.example.administrator.fastandroid.model.Benefit;
import com.example.administrator.fastandroid.tab.TTabFragment;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zhatong
 * on 2016/9/1 18:37
 */
public class SampleListFragment extends BaseListFragment<Benefit>  implements TTabFragment{
    private int page = 1;


    public static SampleListFragment newInstance( int page){
        SampleListFragment fragment=new SampleListFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(ConstantValues.KEY_FRAGMENT_PAGE,page);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setRefreshing();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,
                parent, false);
        return new SampleViewHolder(view);
    }


    @Override
    protected ILayoutManager getLayoutManager() {
        return new MyGridLayoutManager(getContext(),3);
    }


    @Override
    public void onRefresh(final int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }

         if(action == PullRecycler.ACTION_LOAD_MORE_REFRESH){
             page=1;
         }
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gank.io/")
                 .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);
        api.rxBenefits(20,page++).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseModel<ArrayList<Benefit>>>() {
                    @Override
                    public void call(BaseModel<ArrayList<Benefit>> model) {
                        if (action == PullRecycler.ACTION_LOAD_MORE_REFRESH) {
                            mDataList.clear();
                        }
                        if (model.results == null || model.results.size() == 0) {
                            recycler.enableLoadMore(false);
                        } else {
                            recycler.enableLoadMore(true);
                            mDataList.addAll(model.results);
                            adapter.notifyDataSetChanged();
                        }
                        recycler.onRefreshCompleted();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        recycler.onRefreshCompleted();
                    }
                });

      /*  //模拟网络请求数据，等待3s
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //状态如果处于下拉刷新，要清理之前的数据，防止数据重复
                if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
                    mDataList.clear();
                }
                //每一次加载更多的数据的数量
                int loadSizeOnce = mDataList.size();
                for (int i = loadSizeOnce; i < loadSizeOnce + 20; i++) {
                   // mDataList.add("sample list item" + i);

                }
                adapter.notifyDataSetChanged();
                recycler.onRefreshCompleted();
                if (mDataList.size() < 100) {
                    recycler.enableLoadMore(true);
                } else {
                    recycler.enableLoadMore(false);
                }
            }
        }, 3000);*/


    }

    @Override
    public BaseFragment getFragment() {
        return this;
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
           /* String data = mDataList.get(position);
            textView.setText(data);*/
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

}
