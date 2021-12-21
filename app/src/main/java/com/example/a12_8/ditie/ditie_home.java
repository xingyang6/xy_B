package com.example.a12_8.ditie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class ditie_home extends BastActivity {

    private RecyclerView ditieHomeRecy;
    private ImageView imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditie_home);
        setTitle("城市地铁");
        initView();
        intidata();
    }

    private void intidata() {
        Network.doGet("/prod-api/api/metro/list?currentName=建国门", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<ditie_home_bean> honme = new Gson().fromJson(jsonObject.optString("data"), new TypeToken<List<ditie_home_bean>>() {
                    }.getType());
                    ditie_init_bean init = null;
                    ditieHomeRecy.setLayoutManager(new LinearLayoutManager(ditie_home.this));
                    ditieHomeRecy.setAdapter(new ditie_Adapter(ditie_home.this, honme, init, 0));
                } else {
                    Toast.makeText(ditie_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ditie_home.this,detie_tu.class));
            }
        });
    }

    private void initView() {
        ditieHomeRecy = findViewById(R.id.ditie_home_recy);
        imageView8 = findViewById(R.id.imageView8);
    }
}