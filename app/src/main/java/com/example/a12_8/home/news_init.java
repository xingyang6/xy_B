package com.example.a12_8.home;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a12_8.R;
import com.example.a12_8.bean.news_init_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class news_init extends BastActivity {
    public static int news_iod;
    private TextView newsTitle;
    private ImageView newsImg;
    private TextView newsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_init);
        setTitle("新闻详情");

        initView();
        initdata();
    }

    private void initdata() {
        Network.doGet("/prod-api/press/press/" + news_iod, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                news_init_bean init=new Gson().fromJson(jsonObject.optString("data"),news_init_bean.class);
                newsTitle.setText(init.getTitle());
                Network.doImage(news_init.this,init.getCover(),newsImg);
                newsCount.setText(Html.fromHtml(init.getContent()));
            }
        });

    }

    private void initView() {
        newsTitle = findViewById(R.id.news_title);
        newsImg = findViewById(R.id.news_img);
        newsCount = findViewById(R.id.news_count);
    }
}