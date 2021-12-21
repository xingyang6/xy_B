package com.example.a12_8.zhihui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.Adapter.zhihzui_Adapter;
import com.example.a12_8.R;
import com.example.a12_8.bean.user_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class huishou_init extends BastActivity {
    public static int type;
    private TextView initTitle;
    private ImageView initImg;
    private TextView initCount;
    private EditText hbPl;
    private Button hbBtn;
    private RecyclerView huishouInitRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huishou_init);
        setTitle("知识窗口");
        initView();
        initdata();
    }

    private void initdata() {
        switch (type) {
            case 1:
                initTitle.setText(A_zhihui.hb_title.get(0));
                initImg.setImageResource(A_zhihui.hb_img.get(0));
                initCount.setText(A_zhihui.hb_count.get(0));
                hbBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hbPl.getText().toString().isEmpty()) {
                            Toast.makeText(huishou_init.this, "评论内容不能为空哟", Toast.LENGTH_SHORT).show();
                        } else {
                            //获取姓名
                            Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
                                @Override
                                public void succes(JSONObject jsonObject) {
                                    if (jsonObject.optInt("code") == 200) {
                                        user_bean usr = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
                                        A_zhihui.pl_text1.add(usr.getUserName());
                                        //获取时间
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                                        Date date = new Date(System.currentTimeMillis());
                                        A_zhihui.pl_data1.add(simpleDateFormat.format(date));
                                        A_zhihui.pl_count1.add(hbPl.getText().toString().trim());
                                        Toast.makeText(huishou_init.this, "评论已发表", Toast.LENGTH_SHORT).show();
                                        huishouInitRecy.setLayoutManager(new LinearLayoutManager(huishou_init.this));
                                        huishouInitRecy.setAdapter(new zhihzui_Adapter(huishou_init.this,0));
                                    } else {
                                        Toast.makeText(huishou_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }
                });
                break;
            case 2:
                initTitle.setText(A_zhihui.hb_title.get(1));
                initImg.setImageResource(A_zhihui.hb_img.get(1));
                initCount.setText(A_zhihui.hb_count.get(1));
                hbBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hbPl.getText().toString().isEmpty()) {
                            Toast.makeText(huishou_init.this, "评论内容不能为空哟", Toast.LENGTH_SHORT).show();
                        } else {
                            //获取姓名
                            Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
                                @Override
                                public void succes(JSONObject jsonObject) {
                                    if (jsonObject.optInt("code") == 200) {
                                        user_bean usr = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
                                        A_zhihui.pl_text2.add(usr.getPhonenumber());
                                        //获取时间
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                                        Date date = new Date(System.currentTimeMillis());
                                        A_zhihui.pl_data2.add(simpleDateFormat.format(date));
                                        A_zhihui.pl_count2.add(hbPl.getText().toString().trim());
                                        huishouInitRecy.setLayoutManager(new LinearLayoutManager(huishou_init.this));
                                        huishouInitRecy.setAdapter(new zhihzui_Adapter(huishou_init.this,0));
                                        Toast.makeText(huishou_init.this, "评论已发表", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(huishou_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }
                });
                break;
            case 3:
                initTitle.setText(A_zhihui.hb_title.get(2));
                initImg.setImageResource(A_zhihui.hb_img.get(2));
                initCount.setText(A_zhihui.hb_count.get(2));

                hbBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hbPl.getText().toString().isEmpty()) {
                            Toast.makeText(huishou_init.this, "评论内容不能为空哟", Toast.LENGTH_SHORT).show();
                        } else {
                            //获取姓名
                            Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
                                @Override
                                public void succes(JSONObject jsonObject) {
                                    if (jsonObject.optInt("code") == 200) {
                                        user_bean usr = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
                                        A_zhihui.pl_text3.add(usr.getPhonenumber());
                                        //获取时间
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                                        Date date = new Date(System.currentTimeMillis());
                                        A_zhihui.pl_data3.add(simpleDateFormat.format(date));
                                        A_zhihui.pl_count3.add(hbPl.getText().toString().trim());
                                        Toast.makeText(huishou_init.this, "评论已发表", Toast.LENGTH_SHORT).show();
                                        huishouInitRecy.setLayoutManager(new LinearLayoutManager(huishou_init.this));
                                        huishouInitRecy.setAdapter(new zhihzui_Adapter(huishou_init.this,0));
                                    } else {
                                        Toast.makeText(huishou_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }
                });
                break;
            case 4:
                initTitle.setText(A_zhihui.hb_title.get(3));
                initImg.setImageResource(A_zhihui.hb_img.get(3));
                initCount.setText(A_zhihui.hb_count.get(3));
                hbBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hbPl.getText().toString().isEmpty()) {
                            Toast.makeText(huishou_init.this, "评论内容不能为空哟", Toast.LENGTH_SHORT).show();
                        } else {
                            //获取姓名
                            Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
                                @Override
                                public void succes(JSONObject jsonObject) {
                                    if (jsonObject.optInt("code") == 200) {
                                        user_bean usr = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
                                        A_zhihui.pl_text4.add(usr.getPhonenumber());
                                        //获取时间
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                                        Date date = new Date(System.currentTimeMillis());
                                        A_zhihui.pl_data4.add(simpleDateFormat.format(date));
                                        A_zhihui.pl_count4.add(hbPl.getText().toString().trim());
                                        Toast.makeText(huishou_init.this, "评论已发表", Toast.LENGTH_SHORT).show();
                                        huishouInitRecy.setLayoutManager(new LinearLayoutManager(huishou_init.this));
                                        huishouInitRecy.setAdapter(new zhihzui_Adapter(huishou_init.this,0));
                                    } else {
                                        Toast.makeText(huishou_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }
                });
                break;
            case 5:
                initTitle.setText(A_zhihui.hb_title.get(4));
                initImg.setImageResource(A_zhihui.hb_img.get(4));
                initCount.setText(A_zhihui.hb_count.get(4));
                hbBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hbPl.getText().toString().isEmpty()) {
                            Toast.makeText(huishou_init.this, "评论内容不能为空哟", Toast.LENGTH_SHORT).show();
                        } else {
                            //获取姓名
                            Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
                                @Override
                                public void succes(JSONObject jsonObject) {
                                    if (jsonObject.optInt("code") == 200) {
                                        user_bean usr = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
                                        A_zhihui.pl_text4.add(usr.getPhonenumber());
                                        //获取时间
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                                        Date date = new Date(System.currentTimeMillis());
                                        A_zhihui.pl_data4.add(simpleDateFormat.format(date));
                                        A_zhihui.pl_count4.add(hbPl.getText().toString().trim());
                                        Toast.makeText(huishou_init.this, "评论已发表", Toast.LENGTH_SHORT).show();
                                        huishouInitRecy.setLayoutManager(new LinearLayoutManager(huishou_init.this));
                                        huishouInitRecy.setAdapter(new zhihzui_Adapter(huishou_init.this,0));
                                    } else {
                                        Toast.makeText(huishou_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }
                });
                break;
        }

        huishouInitRecy.setLayoutManager(new LinearLayoutManager(huishou_init.this));
        huishouInitRecy.setAdapter(new zhihzui_Adapter(huishou_init.this,0));
    }

    private void initView() {
        initTitle = findViewById(R.id.init_title);
        initImg = findViewById(R.id.init_img);
        initCount = findViewById(R.id.init_count);
        hbPl = findViewById(R.id.hb_pl);
        hbBtn = findViewById(R.id.hb_btn);
        huishouInitRecy = findViewById(R.id.huishou_init_recy);
    }
}