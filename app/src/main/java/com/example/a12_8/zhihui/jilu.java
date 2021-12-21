package com.example.a12_8.zhihui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.zhihzui_Adapter;
import com.example.a12_8.R;
import com.example.a12_8.util.BastActivity;

public class jilu extends BastActivity {

    private RecyclerView yuyue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jilu);
        setTitle("预约历史");
        initView();
        initdat();
    }

    private void initdat() {
        yuyue.setLayoutManager(new LinearLayoutManager(jilu.this));
        yuyue.setAdapter(new zhihzui_Adapter(jilu.this,2));
    }

    private void initView() {
        yuyue = findViewById(R.id.yuyue);
    }
}