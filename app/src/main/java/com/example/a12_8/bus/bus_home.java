package com.example.a12_8.bus;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.bus_Adapter;
import com.example.a12_8.R;
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

public class bus_home extends BastActivity {
    public static int[] aaa;
    private RecyclerView busHomeRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_home);
        setTitle("智慧巴士");
        initView();
        initdata();
    }

    private void initdata() {
        Network.doGet("/prod-api/api/bus/line/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<bus_home_bean> bus=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<bus_home_bean>>(){
                    }.getType());
                    aaa=new int[bus.size()];
                    List<bus_2_bean> zhand=new ArrayList<>();
                    busHomeRecy.setLayoutManager(new LinearLayoutManager(bus_home.this));
                    busHomeRecy.setAdapter(new bus_Adapter(bus_home.this,bus,zhand,0));

                }else{
                    Toast.makeText(bus_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        busHomeRecy = findViewById(R.id.bus_home_recy);
    }
}