package com.example.a12_8.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.bus_Adapter;
import com.example.a12_8.R;
import com.example.a12_8.bean.bus1_bean;
import com.example.a12_8.bean.bus_2_bean;
import com.example.a12_8.bean.bus_home_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class bus_1 extends BastActivity {
    public static int bus_id;
    private TextView bus1Start;
    private TextView bus1End;
    private TextView bus1Time;
    private TextView bus1Km;
    public static RecyclerView bus1Recy;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_1);
        setTitle("线路详情");
        initView();
        initdata();
    }

    private void initdata() {
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bus_1.this,bus_2.class));
            }
        });
        Network.doGet("/prod-api/api/bus/line/" + bus_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    bus1_bean init = new Gson().fromJson(jsonObject.optString("data"), bus1_bean.class);
                    bus1Start.setText(init.getFirst());
                    bus1End.setText(init.getEnd());
                    bus1Time.setText("￥" + init.getPrice() + "元");
                    bus1Km.setText("全程" + init.getMileage() + "km");
                    bus_3.bus_3_picture=init.getPrice();
                    bus_3.bus_3_xl=init.getName();
                    bus1Recy.setLayoutManager(new LinearLayoutManager(bus_1.this));
                    List<bus_home_bean> bus = new ArrayList<>();
                    List<bus_2_bean> zhand = new ArrayList<>();
                    bus1Recy.setAdapter(new bus_Adapter(bus_1.this, bus, zhand, 2));

                } else {
                    Toast.makeText(bus_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });


        Network.doGet("/prod-api/api/bus/stop/list?linesId=" + bus_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<bus_2_bean> zhand = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<bus_2_bean>>() {
                    }.getType());
                    List<bus_home_bean> bus = new ArrayList<>();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(bus_1.this);
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    bus1Recy.setLayoutManager(linearLayoutManager);
                    bus1Recy.setAdapter(new bus_Adapter(bus_1.this, bus, zhand, 2));

                } else {
                    Toast.makeText(bus_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        bus1Start = findViewById(R.id.bus_1_start);
        bus1End = findViewById(R.id.bus_1_end);
        bus1Time = findViewById(R.id.bus_1_time);
        bus1Km = findViewById(R.id.bus_1_km);
        bus1Recy = findViewById(R.id.bus_1_recy);
        button4 = findViewById(R.id.button4);
    }
}