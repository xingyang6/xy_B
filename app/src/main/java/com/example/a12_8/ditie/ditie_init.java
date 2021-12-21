package com.example.a12_8.ditie;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.ditie_Adapter;
import com.example.a12_8.R;
import com.example.a12_8.bean.ditie_home_bean;
import com.example.a12_8.bean.ditie_init_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ditie_init extends BastActivity {
    public static int xl_id;
    private TextView ditieInitStart;
    private TextView ditieInitEnd;
    private TextView ditieInitTime;
    private TextView ditieInitKm;
    private RecyclerView ditieInitRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditie_init);
        setTitle("线路详情");
        initView();
        initdata();
    }

    private void initdata() {
        Network.doGet("/prod-api/api/metro/line/" + xl_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    ditie_init_bean init=new Gson().fromJson(jsonObject.optString("data"),ditie_init_bean.class);
                    ditieInitStart.setText(init.getFirst());
                    ditieInitEnd.setText(init.getEnd());
                    ditieInitTime.setText(init.getRemainingTime()+"分钟");
                    String s=init.getStationsNumber().toString();
                    s=s.substring(0,s.length()-2);
                    ditieInitKm.setText(init.getKm()+"km/"+s+"站");
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ditie_init.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    ditieInitRecy.setLayoutManager(linearLayoutManager);
                    List<ditie_home_bean> honme=new ArrayList<>();
                    ditieInitRecy.setAdapter(new ditie_Adapter(ditie_init.this,honme,init,1));
                }else{
                    Toast.makeText(ditie_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
        ditieInitStart = findViewById(R.id.ditie_init_start);
        ditieInitEnd = findViewById(R.id.ditie_init_end);
        ditieInitTime = findViewById(R.id.ditie_init_time);
        ditieInitKm = findViewById(R.id.ditie_init_km);
        ditieInitRecy = findViewById(R.id.ditie_init_recy);
    }
}