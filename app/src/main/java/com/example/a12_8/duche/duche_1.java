package com.example.a12_8.duche;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a12_8.R;
import com.example.a12_8.util.Network;

public class duche_1 extends Fragment {


    private EditText duche1Chep;
    private EditText duche1Name;
    private EditText duche1Phone;
    private EditText duche1Cardid;
    private TextView duche1Adress;
    private EditText duche1AdressInit;
    private ImageView duche1Img;
    private TextView duche1Imgduche1Text;
    private Button duche1Btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_duche_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
        initclick();
    }

    private void initclick() {
        duche1Adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Network.doAdress(getActivity(),duche1Adress);
            }
        });
        duche1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (duche1Chep.getText().toString().trim().isEmpty()|duche1Name.getText().toString().trim().isEmpty()|duche1Phone.getText().toString().trim().isEmpty()|duche1Cardid.getText().toString().trim().isEmpty()|duche1Adress.getText().toString().equals("地址")|duche1AdressInit.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "请完善信息", Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    View view= LayoutInflater.from(getActivity()).inflate(R.layout.tank_item,null);
                    builder.setView(view);
                    LinearLayout linearLayout=view.findViewById(R.id.liner_1);
                    TextView textnaem=view.findViewById(R.id.textView16);
                    TextView texttel=view.findViewById(R.id.textView17);
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+texttel.getText().toString().trim()));
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }


            }
        });
        duche1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK &&requestCode==1){
            Bundle ex=data.getExtras();
            Bitmap bitmap=(Bitmap) ex.get("data");
            duche1Img.setImageBitmap(bitmap);
            duche1Imgduche1Text.setVisibility(View.GONE);
        }
    }

    private void initdata() {

    }

    private void initView(View view) {
        duche1Chep = view.findViewById(R.id.duche_1_chep);
        duche1Name = view.findViewById(R.id.duche_1_name);
        duche1Phone = view.findViewById(R.id.duche_1_phone);
        duche1Cardid = view.findViewById(R.id.duche_1_cardid);
        duche1Adress = view.findViewById(R.id.duche_1_adress);
        duche1AdressInit = view.findViewById(R.id.duche_1_adress_init);
        duche1Img = view.findViewById(R.id.duche_1_img);
        duche1Imgduche1Text = view.findViewById(R.id.duche_1_imgduche_1_text);
        duche1Btn = view.findViewById(R.id.duche_1_btn);
    }
}