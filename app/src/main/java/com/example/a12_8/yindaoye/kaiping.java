package com.example.a12_8.yindaoye;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12_8.R;

public class kaiping extends AppCompatActivity {

    private ProgressBar kaipBar;
    private TextView kaipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaiping);
        getSupportActionBar().hide();
        initView();
        initdata();
    }

    private void initdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(new Intent(kaiping.this,yindaop.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        CountDownTimer countDownTimer=new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                kaipText.setText(millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void initView() {
        kaipBar = findViewById(R.id.kaip_bar);
        kaipText = findViewById(R.id.kaip_text);
    }
}