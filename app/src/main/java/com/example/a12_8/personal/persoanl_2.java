package com.example.a12_8.personal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.personal_Adapter;
import com.example.a12_8.MainActivity;
import com.example.a12_8.R;
import com.example.a12_8.bean.bus_dd_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class persoanl_2 extends AppCompatActivity {

    public static TextView unzf;
    public static TextView zf;
    public static RecyclerView personal2Recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoanl_2);
        setTitle("订单列表");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initdata();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        startActivity(new Intent(persoanl_2.this,MainActivity.class));
        return super.onSupportNavigateUp();
    }

    private void initdata() {
        //进入默认显示未支付
        unzf.setBackgroundColor(Color.rgb(33,150,243));
        unzf.setTextColor(Color.rgb(255,255,255));
        zf.setBackgroundColor(Color.rgb(255,255,255));
        zf.setTextColor(Color.rgb(33,150,243));
        Network.doGet("/prod-api/api/bus/order/list?status=0", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<bus_dd_bean> dd=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<bus_dd_bean>>(){
                    }.getType());
                    personal2Recy.setLayoutManager(new LinearLayoutManager(persoanl_2.this));
                    personal2Recy.setAdapter(new personal_Adapter(persoanl_2.this,dd));

                }else{
                    Toast.makeText(persoanl_2.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });


        unzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unzf.setBackgroundColor(Color.rgb(33,150,243));
                unzf.setTextColor(Color.rgb(255,255,255));
                zf.setBackgroundColor(Color.rgb(255,255,255));
                zf.setTextColor(Color.rgb(33,150,243));
                Network.doGet("/prod-api/api/bus/order/list?status=0", new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            List<bus_dd_bean> dd=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<bus_dd_bean>>(){
                            }.getType());
                            personal2Recy.setLayoutManager(new LinearLayoutManager(persoanl_2.this));
                            personal2Recy.setAdapter(new personal_Adapter(persoanl_2.this,dd));

                        }else{
                            Toast.makeText(persoanl_2.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        zf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zf.setBackgroundColor(Color.rgb(33,150,243));
                zf.setTextColor(Color.rgb(255,255,255));
                unzf.setBackgroundColor(Color.rgb(255,255,255));
                unzf.setTextColor(Color.rgb(33,150,243));
                Network.doGet("/prod-api/api/bus/order/list?status=1", new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            List<bus_dd_bean> dd=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<bus_dd_bean>>(){
                            }.getType());
                            personal2Recy.setLayoutManager(new LinearLayoutManager(persoanl_2.this));
                            personal2Recy.setAdapter(new personal_Adapter(persoanl_2.this,dd));

                        }else{
                            Toast.makeText(persoanl_2.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        unzf = findViewById(R.id.unzf);
        zf = findViewById(R.id.zf);
        personal2Recy = findViewById(R.id.personal_2_recy);
    }
}