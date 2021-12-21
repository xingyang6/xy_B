package com.example.a12_8.yindaoye;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a12_8.R;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;

public class ip_yindao extends BastActivity {

    private EditText editTextTextPersonName4;
    private Button button10;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_yindao);
        setTitle("端口设置");
        initView();
        initdata();
    }

    private void initdata() {
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextPersonName4.getText().toString().isEmpty()){
                    Toast.makeText(ip_yindao.this, "不能为空哟", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sp=getSharedPreferences("sp", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("url","http://"+editTextTextPersonName4.getText().toString().trim());
                    editor.commit();
                    Toast.makeText(ip_yindao.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    private void initView() {
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        button10 = findViewById(R.id.button10);
    }
}