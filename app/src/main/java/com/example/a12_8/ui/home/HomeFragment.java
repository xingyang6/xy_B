package com.example.a12_8.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a12_8.Adapter.home_Adapter;
import com.example.a12_8.R;
import com.example.a12_8.bean.all_service;
import com.example.a12_8.bean.home_banenr_bean;
import com.example.a12_8.bean.news_bean;
import com.example.a12_8.bean.news_list_bean;
import com.example.a12_8.bean.news_type;
import com.example.a12_8.home.news_init;
import com.example.a12_8.home.news_sear;
import com.example.a12_8.ui.news.news;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.example.a12_8.util.gongge;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SearchView homeSear;
    private Banner homeBanenr;
    private RecyclerView homeTuijianRecy;
    private RecyclerView homeRemenRecy;
    private TabLayout homeTab;
    private RecyclerView homeNewsRecy;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        intdata();
        initbanner();
        initremen();
        inittab();
        initsearch();
    }


    private void initsearch() {
        homeSear.setSubmitButtonEnabled(true);
        homeSear.setIconified(true);
        homeSear.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        homeSear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (gongge.isFast()){
                    return false;
                }else{
                    news_sear.path=query;
                  startActivity(new Intent(getActivity(),news_sear.class));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initnews(Object s) {
        Network.doGet("/prod-api/press/press/list?type=" + s, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<news_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<news_bean>>(){
                    }.getType());
                    List<all_service> tuijian=new ArrayList<>();
                    homeNewsRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                    homeNewsRecy.setAdapter(new home_Adapter(getActivity(),tuijian,news,2));
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inittab() {
        Network.doGet("/prod-api/press/category/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<news_type> news=new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<news_type>>(){
                    }.getType());
                    for (int i=0;i<news.size();i++){
                        homeTab.addTab(homeTab.newTab().setText(news.get(i).getName()).setTag(news.get(i).getId()));
                    }
                    initnews(news.get(0).getId());
                    homeTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            initnews(tab.getTag().toString().trim());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initremen() {
        Network.doGet("/prod-api/press/press/list?hot=Y", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<news_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<news_bean>>(){
                    }.getType());
                    List<all_service> tuijian=new ArrayList<>();
                    homeRemenRecy.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    homeRemenRecy.setAdapter(new home_Adapter(getActivity(),tuijian,news,1 ));
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initbanner() {
        Network.doGet("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_banenr_bean> banenr=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_banenr_bean>>(){
                    }.getType());
                    homeBanenr.setImages(banenr).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            home_banenr_bean ban=(home_banenr_bean) o ;
                            Network.doImage(context,ban.getAdvImg(),imageView);
                        }
                    }).start();
                    homeBanenr.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int i) {
                            news_init.news_iod= banenr.get(i).getTargetId();
                            startActivity(new Intent(getActivity(),news_init.class));
                        }
                    }).start();
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intdata() {
        //推荐服务
        Comparator<all_service> comparator=new Comparator<all_service>() {
            @Override
            public int compare(all_service o1, all_service o2) {
               if (o1.getId()!=o2.getId()){
                   return o2.getId()-o1.getId();
               }else {
                   return o1.getId()-o2.getId();
               }
            }
        };
        Network.doGet("/prod-api/api/service/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<all_service> tuijian=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<all_service>>(){
                    }.getType());
                    Collections.sort(tuijian,comparator);
                    List<news_bean> news=new ArrayList<>();
                    homeTuijianRecy.setLayoutManager(new GridLayoutManager(getActivity(),5));
                    homeTuijianRecy.setAdapter(new home_Adapter(getActivity(),tuijian,news,0));
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(View view) {
        homeSear = view.findViewById(R.id.home_sear);
        homeBanenr = view.findViewById(R.id.home_banenr);
        homeTuijianRecy = view.findViewById(R.id.home_tuijian_recy);
        homeRemenRecy = view.findViewById(R.id.home_remen_recy);
        homeTab = view.findViewById(R.id.home_tab);
        homeNewsRecy = view.findViewById(R.id.home_news_recy);
    }
}