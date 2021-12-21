package com.example.a12_8.park;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.List;

public class park_init_2 extends BastActivity {
    public static int park_init_2_number=5;
    private TextView textView8;
    private TextView textView9;
    private TextView park2Data1;
    private TextView park2Time1;
    private ImageView imageView11;
    private TextView textView12;
    private TextView park2Data2;
    private TextView park2Time2;
    private ImageView imageView12;
    private Button park2Btn;
    private RecyclerView parkInit2Recy;
    private TextView text;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_init_2);
        setTitle("停车记录");
        initView();
        initdata();
        initclick();
    }

    private void initclick() {
        park2Data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(park_init_2.this,new Myintdata1(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        park2Data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(park_init_2.this,new Myintdata2(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        park2Time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                TimePickerDialog datePickerDialog=new TimePickerDialog(park_init_2.this,new Myinttime1(),
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        true);
                datePickerDialog.show();
            }
        });
        park2Time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                TimePickerDialog datePickerDialog=new TimePickerDialog(park_init_2.this,new Myinttime2(),
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        true);
                datePickerDialog.show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                park_init_2_number=park_init_2_number+3;
                initdata();
            }
        });
        park2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (park2Data1.getText().toString().length() == 2 | park2Time1.getText().toString().length() == 2 | park2Data2.getText().toString().length() == 2 | park2Time2.getText().toString().length() == 2) {
                    Toast.makeText(park_init_2.this, "请输入完整时间", Toast.LENGTH_SHORT).show();
                } else {
                    String data1=park2Data1.getText().toString().trim()+" "+park2Time1.getText().toString().trim();
                    String data2=park2Data2.getText().toString().trim()+" "+park2Time2.getText().toString().trim();
                    Network.doGet("/prod-api/api/park/lot/record/list?entryTime="+data1+"&?&outTime="+data2, new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code") == 200) {
                                List<park_jil_bean> jil = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<park_jil_bean>>() {
                                }.getType());
                                if (jil.size() == 0) {
                                    text.setVisibility(View.VISIBLE);
                                    parkInit2Recy.setVisibility(View.GONE);
                                    button3.setVisibility(View.GONE);
                                } else {
                                    park_init_2_number=jil.size();
                                    text.setVisibility(View.GONE);
                                    parkInit2Recy.setVisibility(View.VISIBLE);
                                    List<park_home_bean> home = new ArrayList<>();
                                    parkInit2Recy.setLayoutManager(new LinearLayoutManager(park_init_2.this));
                                    parkInit2Recy.setAdapter(new park_Adapter(park_init_2.this, home, jil, 1));
                                }

                            } else {
                                Toast.makeText(park_init_2.this, "msg", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initdata() {
        Network.doGet("/prod-api/api/park/lot/record/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<park_jil_bean> jil = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<park_jil_bean>>() {
                    }.getType());
                    List<park_home_bean> home = new ArrayList<>();
                    parkInit2Recy.setLayoutManager(new LinearLayoutManager(park_init_2.this));
                    parkInit2Recy.setAdapter(new park_Adapter(park_init_2.this, home, jil, 1));

                } else {
                    Toast.makeText(park_init_2.this, "msg", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        park2Data1 = findViewById(R.id.park_2_data_1);
        park2Time1 = findViewById(R.id.park_2_time_1);
        imageView11 = findViewById(R.id.imageView11);
        textView12 = findViewById(R.id.textView12);
        park2Data2 = findViewById(R.id.park_2_data_2);
        park2Time2 = findViewById(R.id.park_2_time_2);
        imageView12 = findViewById(R.id.imageView12);
        park2Btn = findViewById(R.id.park_2_btn);
        parkInit2Recy = findViewById(R.id.park_init_2_recy);
        text = findViewById(R.id.text);
        button3 = findViewById(R.id.button3);
    }

    private class Myintdata1 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            park2Data1.setText(year+"-"+(month+1)+"-"+dayOfMonth);
        }
    }

    private class Myintdata2 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            park2Data2.setText(year+"-"+(month+1)+"-"+dayOfMonth);
        }
    }

    private class Myinttime1 implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            park2Time1.setText(hourOfDay+":"+minute);
        }
    }


    private class Myinttime2 implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            park2Time2.setText(hourOfDay+":"+minute);
        }
    }
}