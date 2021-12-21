package com.example.a12_8.yindaoye;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.a12_8.Login;
import com.example.a12_8.MainActivity;
import com.example.a12_8.R;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.example.a12_8.zhihui.A_zhihui;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class yindaop extends AppCompatActivity {
    private List<Integer> data = new ArrayList<>();
    private ViewPager viewpager;
    private LinearLayout lear;
    private int curr = 0;
    private Button IP;
    private Button goHome;
    public static String cun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindaop);
        getSupportActionBar().hide();
        A_zhihui.initdata();
        initView();
        initdata();
        initclick();
        init();

    }

    private void init() {

    }

    private void initclick() {
        IP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(yindaop.this,ip_yindao.class));
            }
        });
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //取端口
                SharedPreferences ssp=getSharedPreferences("sp", Activity.MODE_PRIVATE);
                if (ssp.getString("url","").isEmpty()){
                    SharedPreferences.Editor editor=ssp.edit();
                    editor.putString("url","http://124.93.196.45:10001");
                    editor.commit();
                }
                Network.baseurl=ssp.getString("url",null);

                SharedPreferences sp = getSharedPreferences("zh", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                String data=sp.getString("password","");
                if (data.length()==0){
                    editor.putString("username", "text01");
                    editor.putString("password", "151407");
                    editor.commit();
                }
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("username",sp.getString("username",""))
                            .put("password",sp.getString("password",""));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(yindaop.this, "登录成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(yindaop.this, MainActivity.class));
                            Network.token=jsonObject.optString("token");
                            finish();
                            startActivity(new Intent(yindaop.this,MainActivity.class));
                        }else{
                            startActivity(new Intent(yindaop.this,Login.class));
                        }
                    }
                });
            }
        });
    }

    private void initdata() {
        data.add(R.drawable.yind_1);
        data.add(R.drawable.yind_2);
        data.add(R.drawable.yind_3);
        data.add(R.drawable.yind_4);
        data.add(R.drawable.yind_5);
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int img : data) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(img);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = imageViews.get(position);
                container.addView(imageView);
                return imageView;
            }
        });
        for (int i = 0; i < data.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            View view = new View(this);
            view.setBackgroundResource(R.drawable.select_picture_brean);
            view.setEnabled(false);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            } else view.setEnabled(false);
            {
                lear.addView(view, layoutParams);
            }
        }
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == data.size() - 1) {
                    IP.setVisibility(View.VISIBLE);
                    goHome.setVisibility(View.VISIBLE);
                }else {
                    IP.setVisibility(View.GONE);
                    goHome.setVisibility(View.GONE);
                }
                lear.getChildAt(curr).setEnabled(false);
                lear.getChildAt(position).setEnabled(true);
                curr=position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initView() {
        viewpager = findViewById(R.id.viewpager);
        lear = findViewById(R.id.lear);
        IP = findViewById(R.id.IP);
        goHome = findViewById(R.id.go_home);
    }
}