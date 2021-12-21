package com.example.a12_8.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_8.R;
import com.example.a12_8.bean.user_bean;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class persoanl_1 extends BastActivity {

    private ImageView personalImg;
    private EditText personalNickName;
    private RadioButton sex0;
    private RadioButton sex1;
    private EditText personalPhone;
    private TextView personalIdcard;
    private Button persona1Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoanl_1);
        setTitle("个人信息");
        initView();
        initdata();
    }

    private void initdata() {
        //查信息
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    user_bean usr = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
                    Network.doImage(persoanl_1.this,"/prod-api/profile/upload/image/2021/05/06/b9d9f081-8a76-41dc-8199-23bcb3a64fcc.png",personalImg);
                    personalNickName.setText(usr.getPhonenumber());
                    if (usr.getSex().equals("0")){
                        sex0.setChecked(true);
                    }else{
                        sex1.setChecked(true);
                    }
                    personalPhone.setText(usr.getPhonenumber());
                    String s=usr.getIdCard().substring(0,2);
                    s=s+"************"+usr.getIdCard().substring(usr.getIdCard().length()-4,usr.getIdCard().length());
                    personalIdcard.setText(s);
                } else {
                    Toast.makeText(persoanl_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        persona1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personalPhone.getText().toString().isEmpty()|personalNickName.getText().toString().isEmpty()){
                    Toast.makeText(persoanl_1.this, "请完善信息", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject=new JSONObject();
                    String s;
                    if (sex0.isChecked()){
                        s="0";
                    }else{
                        s="1";
                    }
                    try {
                        jsonObject.put("nickName",personalNickName.getText().toString().trim())
                                .put("phonenumber",personalPhone.getText().toString().trim())
                                .put("sex",s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPut("/prod-api/api/common/user", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(persoanl_1.this, "修改成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(persoanl_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        personalImg = findViewById(R.id.personal_img);
        personalNickName = findViewById(R.id.personal_nickName);
        sex0 = findViewById(R.id.sex_0);
        sex1 = findViewById(R.id.sex_1);
        personalPhone = findViewById(R.id.personal_phone);
        personalIdcard = findViewById(R.id.personal_idcard);
        persona1Btn = findViewById(R.id.persona_1_btn);
    }
}