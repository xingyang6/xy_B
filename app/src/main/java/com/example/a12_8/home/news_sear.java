package com.example.a12_8.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.home_Adapter;
import com.example.a12_8.R;
import com.example.a12_8.bean.all_service;
import com.example.a12_8.bean.news_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class news_sear extends BastActivity {
    public static String path;
    private RecyclerView searRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_sear);
        setTitle("搜索结果");
        initView();
        initdata();
    }

    private void initdata() {
        Network.doGet("/prod-api/press/press/list?title=" + path, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<news_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<news_bean>>(){
                    }.getType());
                    List<all_service> tuijian=new ArrayList<>();
                    searRecy.setLayoutManager(new LinearLayoutManager(news_sear.this));
                    searRecy.setAdapter(new home_Adapter(news_sear.this,tuijian,news,3));

                }else{
                    Toast.makeText(news_sear.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        searRecy = findViewById(R.id.sear_recy);
    }
}