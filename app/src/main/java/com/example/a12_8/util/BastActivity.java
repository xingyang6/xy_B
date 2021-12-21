package com.example.a12_8.util;

import androidx.appcompat.app.AppCompatActivity;

public class BastActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
