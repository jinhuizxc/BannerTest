package com.jh.bannertest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jh.bannertest.demo.BannerAnimationActivity;
import com.jh.bannertest.demo.BannerLocalActivity;
import com.jh.bannertest.demo.BannerStyleActivity;
import com.jh.bannertest.demo.CustomBannerActivity;
import com.jh.bannertest.demo.CustomViewPagerActivity;
import com.jh.bannertest.demo.IndicatorPositionActivity;
import com.jh.bannertest.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Android广告图片轮播控件，支持无限循环和多种主题，
 * 可以灵活设置轮播样式、动画、轮播和切换时间、位置、图片加载框架等！
 * https://github.com/youth5201314/banner
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, OnBannerListener {

    static final int REFRESH_COMPLETE = 0X1112;
    SuperSwipeRefreshLayout mSwipeLayout;
    ListView listView;
    Banner banner;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    String[] urls = getResources().getStringArray(R.array.url4);
                    List list = Arrays.asList(urls);
                    List arrayList = new ArrayList(list);
                    banner.update(arrayList);
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeLayout.setOnRefreshListener(this);
        listView = (ListView) findViewById(R.id.list);
        // 添加header
        View header = LayoutInflater.from(this).inflate(R.layout.header, null);
        banner = (Banner) header.findViewById(R.id.banner);
        banner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                App.H / 4));
        listView.addHeaderView(banner);

        String[] data = getResources().getStringArray(R.array.demo_list);
        listView.setAdapter(new SampleAdapter(this, data));
        listView.setOnItemClickListener(this);


        //简单使用
        banner.setImages(App.images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .setDelayTime(5000) // 设置轮播图片间隔时间（单位毫秒，默认为2000）
                .start();

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        // 开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                startActivity(new Intent(this, BannerAnimationActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, BannerStyleActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, IndicatorPositionActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, CustomBannerActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, BannerLocalActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, CustomViewPagerActivity.class));
                break;
        }
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }
}
