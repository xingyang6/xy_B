package com.example.a12_8.zhihui;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.zhihzui_Adapter;
import com.example.a12_8.R;
import com.example.a12_8.util.BastActivity;

public class zhizhui_sear_init extends BastActivity {

    private RecyclerView zhSearInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhizhui_sear_init);
        setTitle("搜索结果");
        initView();
        initdata();
    }

    private void initdata() {
        zhSearInit.setLayoutManager(new LinearLayoutManager(zhizhui_sear_init.this));
        zhSearInit.setAdapter(new zhihzui_Adapter(zhizhui_sear_init.this,1));
    }

    private void initView() {
        zhSearInit = findViewById(R.id.zh_sear_init);
    }
}