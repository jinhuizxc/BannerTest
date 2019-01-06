package com.jh.bannertest.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jh.bannertest.App;
import com.jh.bannertest.R;
import com.jh.bannertest.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2019/1/6.
 */
public class CustomBannerActivity extends AppCompatActivity {

    Banner banner1, banner2, banner3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_banner);
        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);
        banner3 = (Banner) findViewById(R.id.banner3);

        banner1.setImages(App.images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner2.setImages(App.images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner3.setImages(App.images)
                .setBannerTitles(App.titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();

    }
}
