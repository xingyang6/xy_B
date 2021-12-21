package com.example.a12_8.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.a12_8.R;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.gongge;
import com.example.a12_8.zhihui.huishou;
import com.example.a12_8.zhihui.huishou_init;
import com.example.a12_8.zhihui.jilu;
import com.example.a12_8.zhihui.zhizhui_sear_init;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private TextView daress;
    private Banner hbBanenr;
    private LinearLayout hbLiner1;
    private SearchView hbSear;
    private LinearLayout hbHuishou;
    private LinearLayout hbJilu;
    private LinearLayout hbNews;

    private List<Integer> imgs=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        imgs.add(R.drawable.hb1);
        imgs.add(R.drawable.hb2);
        imgs.add(R.drawable.hb3);
        imgs.add(R.drawable.hb4);

        hbSear.setSubmitButtonEnabled(true);
        hbSear.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        hbSear.setIconified(true);
        hbSear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (gongge.isFast()){
                    return false;
                }else {
                    startActivity(new Intent(getActivity(), zhizhui_sear_init.class));
                }
                return  true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        daress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Network.doAdress(getActivity(),daress);
            }
        });

        hbBanenr.setImages(imgs).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        }).start();
        hbBanenr.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                huishou_init.type=1;
                startActivity(new Intent(getActivity(), huishou_init.class));
            }
        }).start();
        hbLiner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huishou_init.type=1;
                startActivity(new Intent(getActivity(), huishou_init.class));
            }
        });
        hbNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huishou_init.type=5;
                startActivity(new Intent(getActivity(), huishou_init.class));
            }
        });
        hbHuishou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), huishou.class));
            }
        });
        hbJilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), jilu.class));
            }
        });
    }

    private void initView(View view) {
        daress = view.findViewById(R.id.daress);
        hbBanenr = view.findViewById(R.id.hb_banenr);
        hbLiner1 = view.findViewById(R.id.hb_liner_1);
        hbSear = view.findViewById(R.id.hb_sear);
        hbHuishou = view.findViewById(R.id.hb_huishou);
        hbJilu = view.findViewById(R.id.hb_jilu);
        hbNews = view.findViewById(R.id.hb_news);
    }
}