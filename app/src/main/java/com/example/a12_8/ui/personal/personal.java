package com.example.a12_8.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a12_8.Login;
import com.example.a12_8.R;
import com.example.a12_8.bean.user_bean;
import com.example.a12_8.personal.persoanl_1;
import com.example.a12_8.personal.persoanl_2;
import com.example.a12_8.personal.persoanl_3;
import com.example.a12_8.personal.persoanl_4;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class personal extends Fragment {

    private PersonalViewModel mViewModel;
    private ImageView imageView18;
    private TextView textView22;
    private LinearLayout personal1;
    private LinearLayout personal2;
    private LinearLayout personal3;
    private LinearLayout personal4;
    private Button button6;

    public static personal newInstance() {
        return new personal();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personal_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Login.class));

            }
        });
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    user_bean usr = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
//                    Network.doImage(getActivity(),"/prod-api/profile/upload/image/2021/05/06/b9d9f081-8a76-41dc-8199-23bcb3a64fcc.png",imageView18);
                    textView22.setText(usr.getNickName());
                } else {
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        personal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), persoanl_1.class));
            }
        });
        personal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), persoanl_2.class));
            }
        });
        personal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), persoanl_3.class));
            }
        });
        personal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), persoanl_4.class));
            }
        });
    }

    private void initView(View view) {
        imageView18 = view.findViewById(R.id.imageView18);
        textView22 = view.findViewById(R.id.textView22);
        personal1 = view.findViewById(R.id.personal_1_recy);
        personal2 = view.findViewById(R.id.personal_2);
        personal3 = view.findViewById(R.id.personal_3);
        personal4 = view.findViewById(R.id.personal_4);
        button6 = view.findViewById(R.id.button6);
    }
}