package com.example.a12_8.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.R;
import com.example.a12_8.bean.all_service;
import com.example.a12_8.bean.news_bean;
import com.example.a12_8.bus.bus_home;
import com.example.a12_8.ditie.ditie_home;
import com.example.a12_8.duche.duche_home;
import com.example.a12_8.home.news_init;
import com.example.a12_8.park.park_home;
import com.example.a12_8.util.Network;

import java.util.List;

public class home_Adapter extends RecyclerView.Adapter<home_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    private List<all_service> tuijian;
    private List<news_bean> news;
    public home_Adapter(Context context, List<all_service> tuijian,List<news_bean> news,int a){
        this.context=context;
        this.type=a;
        this.tuijian=tuijian;
        this.news=news;
    }
    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder=null;
        switch (type){
            case 0:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.home_tuijain_item,parent,false));
                break;
            case 1:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.remen_item,parent,false));
                break;
            case 2:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item,parent,false));
                break;
            case 3:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.sear_item,parent,false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        switch (type){
            case 0:
                switch (position){
                    case 5:
                        holder.tuijainImg.setImageResource(R.drawable.dt);
                        holder.tuijainName.setText("城市地铁");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, ditie_home.class));
                            }
                        });
                        break;
                    case 6:
                        holder.tuijainImg.setImageResource(R.drawable.ting);
                        holder.tuijainName.setText("停车场");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, park_home.class));
                            }
                        });
                        break;
                    case 7:
                        holder.tuijainImg.setImageResource(R.drawable.duche);
                        holder.tuijainName.setText("堵车移车");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, duche_home.class));
                            }
                        });
                        break;
                    case 8:
                        holder.tuijainImg.setImageResource(R.drawable.bashi);
                        holder.tuijainName.setText("智慧巴士");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, bus_home.class));
                            }
                        });
                        break;
                    case 9:
                       holder.tuijainImg.setImageResource(R.drawable.ic_dashboard_black_24dp);
                        holder.tuijainName.setText("全部服务");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(v).navigate(R.id.navigation_dashboard);
                            }
                        });
                        break;
                    default:
                        Network.doImage(context,tuijian.get(position).getImgUrl(),holder.tuijainImg);
                        holder.tuijainName.setText(tuijian.get(position).getServiceName());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, "点击了"+tuijian.get(position).getServiceName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;


                }

                break;
            case 1:
                Network.doImage(context,news.get(position).getCover(),holder.remenImg);
                holder.remenCount.setText(Html.fromHtml(news.get(position).getContent()));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        news_init.news_iod=news.get(position).getId();
                        context.startActivity(new Intent(context,news_init.class));
                    }
                });

                break;
            case 2:
                Network.doImage(context,news.get(position).getCover(),holder.imageView);
                holder.textView4.setText(Html.fromHtml(news.get(position).getContent()));
                holder.textView3.setText(news.get(position).getTitle());
                holder.plCount.setText(news.get(position).getCommentNum()+"");
                holder.newsData.setText(news.get(position).getPublishDate());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        news_init.news_iod=news.get(position).getId();
                        context.startActivity(new Intent(context,news_init.class));
                    }
                });

                break;
            case 3:
                holder.textView5.setText(news.get(position).getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        news_init.news_iod=news.get(position).getId();
                        context.startActivity(new Intent(context,news_init.class));
                    }
                });

                break;
        }

    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=10;
                break;
            case 1:
                a=news.size();
                break;
            case 2:
                a=news.size();
                break;
            case 3:
                a=news.size();
                break;
        }
        return a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private ImageView tuijainImg;
        private TextView tuijainName;

        private ImageView remenImg;
        private TextView remenCount;

        private ImageView imageView;
        private TextView textView3;
        private TextView textView4;
        private ImageView imageView2;
        private TextView plCount;
        private TextView newsData;

        private TextView textView5;

        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    tuijainImg = itemView.findViewById(R.id.tuijain_img);
                    tuijainName = itemView.findViewById(R.id.tuijain_name);
                    break;
                case 1:
                    remenImg = itemView.findViewById(R.id.remen_img);
                    remenCount = itemView.findViewById(R.id.remen_count);
                    break;
                case 2:
                    imageView = itemView.findViewById(R.id.imageView);
                    textView3 = itemView.findViewById(R.id.textView3);
                    textView4 = itemView.findViewById(R.id.textView4);
                    imageView2 = itemView.findViewById(R.id.imageView2);
                    plCount = itemView.findViewById(R.id.pl_count);
                    newsData = itemView.findViewById(R.id.news_data);
                    break;
                case 3:
                    textView5 =  itemView.findViewById(R.id.textView5);
                    break;

            }
        }
    }
}
