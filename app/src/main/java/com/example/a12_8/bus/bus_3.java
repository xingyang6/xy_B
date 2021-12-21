package com.example.a12_8.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_8.R;
import com.example.a12_8.personal.persoanl_2;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class bus_3 extends BastActivity {
    public static String bus_3_name;
    public static String bus_3_phone;
    public static String bus_3_shang;
    public static String bus_3_xia;
    public static String bus_3_data;
    public static String bus_3_xl;
    public static int bus_3_picture;
    private TextView bus3Name;
    private TextView bus3Phone;
    private TextView bus3Shang;
    private TextView bus3Xia;
    private TextView bus3Data;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_3);
        setTitle("订单确认");
        initView();
        initdata();
    }

    private void initdata() {

        bus3Name.setText(bus_3_name);
        bus3Phone.setText(bus_3_phone);
        bus3Shang.setText(bus_3_shang);
        bus3Xia.setText(bus_3_xia);
        bus3Data.setText(bus_3_data);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("start",bus3Shang.getText().toString().trim())
                            .put("end",bus3Xia.getText().toString().trim())
                            .put("path",bus_3_xl)
                            .put("price",bus_3_picture)
                            .put("status",0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/bus/order", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(bus_3.this, "提交成功", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(bus_3.this, persoanl_2.class));
                        }else{
                            Toast.makeText(bus_3.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        bus3Name = findViewById(R.id.bus3_name);
        bus3Phone = findViewById(R.id.bus3_phone);
        bus3Shang = findViewById(R.id.bus3_shang);
        bus3Xia = findViewById(R.id.bus3_xia);
        bus3Data = findViewById(R.id.bus3_data);
        button5 = findViewById(R.id.button5);
    }
}