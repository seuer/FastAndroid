package com.example.administrator.fastandroid.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.fastandroid.R;
import com.example.administrator.fastandroid.utils.L;

import java.util.ArrayList;

/**
 * banner
 * Created by zhatong
 * on 2016/9/12 15:27
 */
public class BannerView extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private static final int BANNER_SCROLL_INTERVAL =2000 ;
    private ViewPager viewPager;
    private DotView dotView;
    private OnBannerItemClickListener listener;
    private ArrayList<Banner> banners;
    BannerPagerAdapter adapter;
    private int position;

    public BannerView(Context context) {
        super(context);
        setUpView();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    private void setUpView() {

        LayoutInflater.from(getContext()).inflate(R.layout.activity_banner_layout, this);
        viewPager = (ViewPager) findViewById(R.id.banner_viewpager);
        viewPager.addOnPageChangeListener(this);
        dotView = (DotView) findViewById(R.id.banner_viewpager);
    }

    public void setUpData(ArrayList<Banner> banners, OnBannerItemClickListener listener) {
        this.banners = banners;
        this.listener = listener;
        adapter = new BannerPagerAdapter();
        viewPager.setAdapter(adapter);
        int half = Short.MAX_VALUE / 2;
        half = half - half % banners.size();
        viewPager.setCurrentItem(half);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            L.e("banner switch");
            viewPager.setCurrentItem(position + 1, true);
        }
    };

    public   void  onStart(){
        handler.removeMessages(0);
        handler.sendEmptyMessageDelayed(0, BANNER_SCROLL_INTERVAL);
    }

    public   void onStop(){
        handler.removeMessages(0);

    }

    public void ondestory() {
        handler.removeMessages(0);
        handler = null;
    }


    public class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Short.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final int index = position % banners.size();
            final Banner banner = banners.get(index);
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(banner.resId);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setOnBannerClick(index, banner);
                }
            });

            container.addView(imageView);
            return imageView;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        handler.removeMessages(0);
        this.position = position;
        dotView.notifyDataChanged(position % banners.size(), banners.size());
        handler.sendEmptyMessageAtTime(0, 2000);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnBannerItemClickListener {
        void setOnBannerClick(int index, Banner banner);
    }

    public static class Banner {
        public int resId;

        public Banner(int resId) {
            this.resId = resId;
        }
    }
}
