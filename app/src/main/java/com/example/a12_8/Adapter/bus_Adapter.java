package com.example.a12_8.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.R;
import com.example.a12_8.bean.bus_2_bean;
import com.example.a12_8.bean.bus_home_bean;
import com.example.a12_8.bus.bus_1;
import com.example.a12_8.bus.bus_home;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class bus_Adapter extends RecyclerView.Adapter<bus_Adapter.LearViewHoler> {

    private Context context;
    private int type;
    private List<bus_home_bean> bus;
    private List<bus_2_bean> zhand;

    public bus_Adapter(Context context, List<bus_home_bean> bus,List<bus_2_bean> zhand,int a){
        this.context=context;
        this.bus=bus;
        this.type=a;
        this.zhand=zhand;
    }
    @NonNull
    @Override
    public LearViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHoler learViewHoler=null;
        switch (type){
            case 0:
                learViewHoler=new LearViewHoler(LayoutInflater.from(context).inflate(R.layout.bus_home_item,parent,false));
                break;
            case 1:
                learViewHoler=new LearViewHoler(LayoutInflater.from(context).inflate(R.layout.zhandian_item,parent,false));
                break;
            case 2:
                learViewHoler=new LearViewHoler(LayoutInflater.from(context).inflate(R.layout.bus_1_item,parent,false));
                break;
        }
        return learViewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHoler holder, int position) {
        switch (type){
            case 0:
                holder.busHomeName.setText(bus.get(position).getName());
                holder.start.setText(bus.get(position).getFirst());
                holder.end.setText(bus.get(position).getEnd());
                holder.busHomePrice.setText("票价：￥"+bus.get(position).getPrice());
                holder.busHomeMileage.setText("里程："+bus.get(position).getMileage()+"km");
                holder.busHomeStartTime.setText("首发："+bus.get(position).getStartTime());
                holder.busHomeEndTime.setText("末班车："+bus.get(position).getEndTime());
                holder.busHomeName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bus_1.bus_id=bus.get(position).getId();
                        context.startActivity(new Intent(context,bus_1.class));
                    }
                });
                holder.busHomeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bus_home.aaa[position]==0){
                            bus_home.aaa[position]=1;
                            holder.busHomeInitRecy.setVisibility(View.VISIBLE);
                            holder.busHomeImg.setImageResource(R.drawable.shang);
                            List<bus_home_bean> abus=new ArrayList<>();
                            Network.doGet("/prod-api/api/bus/stop/list?linesId="+bus.get(position).getId(), new OkResult() {
                                @Override
                                public void succes(JSONObject jsonObject) {
                                    if (jsonObject.optInt("code")==200){
                                        List<bus_2_bean> zhand=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<bus_2_bean>>(){
                                        }.getType());
                                        holder.busHomeInitRecy.setLayoutManager(new LinearLayoutManager(context));
                                        holder.busHomeInitRecy.setAdapter(new bus_Adapter(context,abus,zhand,1));

                                    }else{
                                        Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else {
                            bus_home.aaa[position]=0;
                            holder.busHomeInitRecy.setVisibility(View.GONE);
                            holder.busHomeImg.setImageResource(R.drawable.xia);
                        }

                    }
                });
                break;
            case 1:
                if (position==0){
                    holder.textView20.setText("起点");
                    holder.textView20.setVisibility(View.VISIBLE);
                }
                if (position==zhand.size()-1){
                    holder.textView20.setText("终点");
                    holder.textView20.setVisibility(View.VISIBLE);
                }
                holder.zdName.setText(zhand.get(position).getName());
                break;
            case 2:
                if (position==0){
                    holder.bus1Zd.setText("起点");
                    holder.bus1Zd.setVisibility(View.VISIBLE);
                }
                if (position==zhand.size()-1){
                    holder.bus1Zd.setText("终点");
                    holder.bus1Zd.setVisibility(View.VISIBLE);
                }
                holder.bus1Name.setText(zhand.get(position).getName());
                break;
        }

    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=bus.size();
                break;
            case 1:
                a=zhand.size();
                break;
            case 2:
                a=zhand.size();
                break;
        }
        return a;
    }

    public class LearViewHoler extends RecyclerView.ViewHolder {
        private TextView busHomeName;
        private TextView start;
        private TextView end;
        private TextView busHomePrice;
        private TextView busHomeMileage;
        private TextView busHomeStartTime;
        private TextView busHomeEndTime;
        private ImageView busHomeImg;
        private RecyclerView busHomeInitRecy;

        private TextView textView20;
        private TextView zdName;

        private TextView bus1Zd;
        private TextView bus1Name;

        public LearViewHoler(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    busHomeName = itemView.findViewById(R.id.bus_home_name);
                    start = itemView.findViewById(R.id.start);
                    end = itemView.findViewById(R.id.end);
                    busHomePrice = itemView.findViewById(R.id.bus_home_price);
                    busHomeMileage = itemView.findViewById(R.id.bus_home_mileage);
                    busHomeStartTime = itemView.findViewById(R.id.bus_home_startTime);
                    busHomeEndTime = itemView.findViewById(R.id.bus_home_endTime);
                    busHomeImg = itemView.findViewById(R.id.bus_home_img);
                    busHomeInitRecy = itemView.findViewById(R.id.bus_home_init_recy);
                    break;
                case 1:
                    textView20 = itemView.findViewById(R.id.textView20);
                    zdName = itemView.findViewById(R.id.zd_name);
                    break;
                case 2:
                    bus1Zd = itemView.findViewById(R.id.bus_1_zd);
                    bus1Name = itemView.findViewById(R.id.bus_1_name);
                    break;
            }

        }
    }
}
