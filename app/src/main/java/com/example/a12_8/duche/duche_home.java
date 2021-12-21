package com.example.a12_8.duche;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.a12_8.R;
import com.example.a12_8.util.BastActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class duche_home extends BastActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duche_home);
        setTitle("堵车移车");
        BottomNavigationView navView = findViewById(R.id.duche_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_jl)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.duc_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}