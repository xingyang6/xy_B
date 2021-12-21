package com.example.a12_8.bus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a12_8.R;
import com.example.a12_8.bean.bus_2_bean;
import com.example.a12_8.bean.user_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class bus_2 extends BastActivity {

    private TextView bus2Data;
    private TextView bus2Time;
    private EditText bus2Name;
    private EditText bus2Phone;
    private Spinner bus2Spinner1;
    private Spinner bus2Spinner2;

    private List<String> zd_name = new ArrayList<>();
    private List<String> zd_shang = new ArrayList<>();
    private List<String> zd_xia = new ArrayList<>();
    private TextView shangText;
    private Button bus2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_2);
        setTitle("乘车信息");
        initView();
        initdata();
    }

    private void initdata() {
        bus2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus_3.bus_3_name=bus2Name.getText().toString().trim();
                bus_3.bus_3_phone=bus2Phone.getText().toString().trim();
                bus_3.bus_3_data=bus2Data.getText().toString().trim()+" "+bus2Time.getText().toString().trim();
                startActivity(new Intent(bus_2.this,bus_3.class));
            }
        });
        bus2Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog DP = new DatePickerDialog(bus_2.this, new bus2data(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                DP.show();
            }
        });
        bus2Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog aa = new TimePickerDialog(bus_2.this, new bus2time(),
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true);
                aa.show();
            }
        });
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    user_bean usr = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
                    bus2Name.setText(usr.getNickName());
                    bus2Phone.setText(usr.getPhonenumber());
                } else {
                    Toast.makeText(bus_2.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //取站点名字
        Network.doGet("/prod-api/api/bus/stop/list?linesId=" + bus_1.bus_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<bus_2_bean> zhand = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<bus_2_bean>>() {
                    }.getType());
                    for (int a = 0; a < zhand.size(); a++) {
                        if (a != zhand.size() - 1) {
                            zd_shang.add(zhand.get(a).getName());
                        }
                        if (a != 0) {
                            zd_xia.add(zhand.get(a).getName());
                        }
                        zd_name.add(zhand.get(a).getName());
                    }
                    ArrayAdapter<String> mspinner1 = new ArrayAdapter<>(bus_2.this, R.layout.item, zd_shang);
                    mspinner1.setDropDownViewResource(R.layout.item);
                    bus2Spinner1.setAdapter(mspinner1);
                    bus2Spinner2.setAdapter(mspinner1);
                } else {
                    Toast.makeText(bus_2.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });


        bus2Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bus_3.bus_3_shang=zd_shang.get(position);
                zd_xia.clear();
                for (int i = 0; i < zd_shang.size(); i++) {
                    if (i >= position) {
                        zd_xia.add(zd_name.get(i + 1));
                    }
                }
                ArrayAdapter<String> mspinner2 = new ArrayAdapter<>(bus_2.this, R.layout.item, zd_xia);
                mspinner2.setDropDownViewResource(R.layout.item);
                bus2Spinner2.setAdapter(mspinner2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bus2Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bus_3.bus_3_xia=zd_xia.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void initView() {
        bus2Data = findViewById(R.id.bus_2_data);
        bus2Time = findViewById(R.id.bus_2_time);
        bus2Name = findViewById(R.id.bus_2_name);
        bus2Phone = findViewById(R.id.bus_2_phone);
        bus2Spinner1 = findViewById(R.id.bus_2_spinner1);
        bus2Spinner2 = findViewById(R.id.bus_2_spinner2);
        bus2Btn = findViewById(R.id.bus2_btn);
    }

    private class bus2data implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            bus2Data.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
        }
    }

    private class bus2time implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            bus2Time.setText(hourOfDay + ":" + minute);
        }
    }
}