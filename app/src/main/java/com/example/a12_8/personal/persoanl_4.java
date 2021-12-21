package com.example.a12_8.personal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_8.R;
import com.example.a12_8.util.BastActivity;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class persoanl_4 extends BastActivity {

    private EditText editTextTextPersonName3;
    private TextView textView44;
    private Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoanl_4);
        setTitle("意见反馈");
        initView();
        inidata();
    }

    private void inidata() {
        editTextTextPersonName3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textView44.setText("字数："+s.toString().length()+"/150");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView44.getText().toString().isEmpty()){
                    Toast.makeText(persoanl_4.this, "请输入意见", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("content",editTextTextPersonName3.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPost("/prod-api/api/common/feedback", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(persoanl_4.this, "提交成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(persoanl_4.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        textView44 = findViewById(R.id.textView44);
        button8 = findViewById(R.id.button8);
    }
}