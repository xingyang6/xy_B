package com.example.a12_8.park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.park_Adapter;
import com.example.a12_8.R;
import com.example.a12_8.bean.park_home_bean;
import com.example.a12_8.bean.park_jil_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class park_home extends BastActivity {
    public static int a = 5;
    private RecyclerView parkHomeRecy;
    private Button parkHomeBtn;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_home);
        setTitle("停车场");
        initView();
        initdata();
        initclick();
    }

    private void initclick() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(park_home.this,park_init_2.class));
            }
        });
        parkHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = a + 3;
                initdata();
            }
        });
    }

    private void initdata() {
        Comparator<park_home_bean> comparator = new Comparator<park_home_bean>() {
            @Override
            public int compare(park_home_bean o1, park_home_bean o2) {
                if (o1.getDistance() != o2.getDistance()) {
                    return o1.getDistance().compareTo(o2.getDistance());
                } else {
                    return o2.getDistance().compareTo(o1.getDistance());
                }
            }
        };
        Network.doGet("/prod-api/api/park/lot/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<park_home_bean> home = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<park_home_bean>>() {
                    }.getType());
                    Collections.sort(home, comparator);
                    List<park_jil_bean> jil = new ArrayList<>();
                    parkHomeRecy.setLayoutManager(new LinearLayoutManager(park_home.this));
                    parkHomeRecy.setAdapter(new park_Adapter(park_home.this, home, jil, 0));
                } else {
                    Toast.makeText(park_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
        parkHomeRecy = findViewById(R.id.park_home_recy);
        parkHomeBtn = findViewById(R.id.park_home_btn);
        button2 = findViewById(R.id.button2);
    }
}