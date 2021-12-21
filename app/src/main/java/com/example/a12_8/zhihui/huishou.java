package com.example.a12_8.zhihui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.a12_8.R;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class huishou extends BastActivity {

    private Banner hsBanenr;
    private EditText hsName;
    private TextView hsAdress;
    private EditText hsPhone;
    private TextView hsType;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private Button hsBtn;

    private List<Integer> data = new ArrayList<>();
    private EditText hsAdressInit;
    private TextView hsData;
    private TextView hsTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huishou);
        setTitle("上门回收垃圾");
        initView();
        initdata();
    }

    private void initdata() {
        data.add(R.drawable.hs1);
        data.add(R.drawable.hs2);
        data.add(R.drawable.hs3);
        hsData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog dp=new DatePickerDialog(huishou.this,new hsMydata(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
        hsTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                TimePickerDialog tp=new TimePickerDialog(huishou.this,new hsMytine(),
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        true);
                tp.show();
            }
        });
        hsBanenr.setImages(data).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        }).start();
        hsBanenr.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                huishou_init.type = 1;
                startActivity(new Intent(huishou.this, huishou_init.class));
            }
        }).start();
        hsAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Network.doAdress(huishou.this, hsAdress);
            }
        });
        hsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hsName.getText().toString().isEmpty() | hsPhone.getText().toString().isEmpty() | hsAdressInit.getText().toString().isEmpty()|hsData.getText().toString().length()==2|hsTime.getText().toString().length()==2) {
                    Toast.makeText(huishou.this, "请完善信息", Toast.LENGTH_SHORT).show();
                } else {
                    A_zhihui.hs_name.add(hsName.getText().toString().trim());
                    A_zhihui.hs_phone.add(hsPhone.getText().toString().trim());
                    A_zhihui.hs_adress.add(hsAdress.getText().toString().trim() + hsAdressInit.getText().toString().trim());
                    A_zhihui.hs_type.add(hsType.getText().toString().trim());
                    A_zhihui.hs_data.add(hsData.getText().toString().trim()+" "+hsTime.getText().toString().trim());
                    Toast.makeText(huishou.this, "预约成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsType.setText("塑料瓶");
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsType.setText("书本");
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsType.setText("显示器");
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsType.setText("纸箱");
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsType.setText("易拉罐");
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsType.setText("棉被");
            }
        });
    }

    private void initView() {
        hsBanenr = findViewById(R.id.hs_banenr);
        hsName = findViewById(R.id.hs_name);
        hsAdress = findViewById(R.id.hs_adress);
        hsPhone = findViewById(R.id.hs_phone);
        hsType = findViewById(R.id.hs_type);
        img1 = findViewById(R.id.img_1);
        img2 = findViewById(R.id.img_2);
        img3 = findViewById(R.id.img_3);
        img4 = findViewById(R.id.img_4);
        img5 = findViewById(R.id.img_5);
        img6 = findViewById(R.id.img_6);
        hsBtn = findViewById(R.id.hs_btn);
        hsAdressInit = findViewById(R.id.hs_adress_init);
        hsData = findViewById(R.id.hs_data);
        hsTime = findViewById(R.id.hs_time);
    }

    private class hsMydata implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            hsData.setText(year+"-"+(month+1)+"-"+dayOfMonth);
        }
    }

    private class hsMytine implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hsTime.setText(hourOfDay+":"+minute);
        }
    }
}