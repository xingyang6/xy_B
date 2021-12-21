package com.example.a12_8.park;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_8.R;
import com.example.a12_8.bean.park_init_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class park_init extends BastActivity {
    public static int park_id;
    private TextView name;
    private TextView km;
    private TextView open;
    private ImageView imageView9;
    private TextView adress;
    private TextView initMoney;
    private TextView initNumber;
    private TextView maxMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_init);
        setTitle("停车场详情");
        initView();
        initdata();
    }

    private void initdata() {
        Network.doGet("/prod-api/api/park/lot/" + park_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    park_init_bean init=new Gson().fromJson(jsonObject.optString("data"),park_init_bean.class);
                    name.setText(init.getParkName());
                    km.setText(init.getDistance()+"km");
                    if (init.getOpen().equals("Y")){
                        open.setText("对外开发");
                    }else{
                        open.setText("不对外开发");
                    }
                    adress.setText(init.getAddress());
                    initMoney.setText(init.getRates()+"元/小时");
                    initNumber.setText(init.getVacancy()+"个/"+init.getAllPark()+"个");
                    maxMoney.setText("每小时"+init.getRates()+"元,每天"+ init.getPriceCaps()+"元封顶");
                }else {
                    Toast.makeText(park_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        name = findViewById(R.id.name);
        km = findViewById(R.id.km);
        open = findViewById(R.id.open);
        imageView9 = findViewById(R.id.imageView9);
        adress = findViewById(R.id.adress);
        initMoney = findViewById(R.id.init_money);
        initNumber = findViewById(R.id.init_number);
        maxMoney = findViewById(R.id.max_money);
    }
}