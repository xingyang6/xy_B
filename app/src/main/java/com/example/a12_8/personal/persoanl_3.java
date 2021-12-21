package com.example.a12_8.personal;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.a12_8.R;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class persoanl_3 extends BastActivity {

    private TextView textView42;
    private EditText oldpass;
    private TextView textView43;
    private EditText newspass;
    private Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoanl_3);
        setTitle("修改密码");
        initView();
        initdata();
    }

    private void initdata() {

            button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldpass.getText().toString().isEmpty()|newspass.getText().toString().isEmpty()){
                    Toast.makeText(persoanl_3.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("oldPassword",oldpass.getText().toString().trim())
                                .put("newPassword",newspass.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPut("/prod-api/api/common/user/resetPwd", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(persoanl_3.this, "修改成功", Toast.LENGTH_SHORT).show();

                                //存密码
                                // 获取SharedPreferences对象
                                SharedPreferences sp = getSharedPreferences("zh", Activity.MODE_PRIVATE);
                                // 获取Editor对象
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("password", newspass.getText().toString().trim());
                                editor.commit();

                                finish();
                            }else{
                                Toast.makeText(persoanl_3.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        textView42 = findViewById(R.id.textView42);
        oldpass = findViewById(R.id.oldpass);
        textView43 = findViewById(R.id.textView43);
        newspass = findViewById(R.id.newspass);
        button9 = findViewById(R.id.button9);
    }
}