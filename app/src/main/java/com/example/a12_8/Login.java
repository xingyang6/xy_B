package com.example.a12_8;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.example.a12_8.zhihui.A_zhihui;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private ImageView imageView4;
    private EditText editTextTextPersonName;
    private ImageView imageView6;
    private EditText editTextTextPersonName2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        initView();
        initdata();
    }

    private void initdata() {
//        // 获取SharedPreferences对象
//        SharedPreferences sp = getSharedPreferences("zh", Activity.MODE_PRIVATE);
//        // 获取Editor对象
//        SharedPreferences.Editor editor = sp.edit();
//        String data=sp.getString("password","");
//        if (data.length()==0){
//            editor.putString("username", "text01");
//            editor.putString("password", "151407");
//            editor.commit();
//        }




//        JSONObject jsonObject=new JSONObject();
//        try {
//            //取
//            jsonObject.put("username",)
//                    .put("password",sp.getString("password",""));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Network.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
//            @Override
//            public void succes(JSONObject jsonObject) {
//                if (jsonObject.optInt("code")==200){
//                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(Login.this,MainActivity.class));
//                    Network.token=jsonObject.optString("token");
//                }else{
//                    Toast.makeText(Login.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("username",editTextTextPersonName.getText().toString().trim())
                            .put("password",editTextTextPersonName2.getText().toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            SharedPreferences sp = getSharedPreferences("zh", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("username",editTextTextPersonName.getText().toString().trim());
                            editor.putString("password",editTextTextPersonName2.getText().toString().trim());
                            editor.commit();

                            Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,MainActivity.class));
                            Network.token=jsonObject.optString("token");
                        }else{
                            Toast.makeText(Login.this,  jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        imageView4 = findViewById(R.id.imageView4);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        imageView6 = findViewById(R.id.imageView6);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        button = findViewById(R.id.button);
    }
}