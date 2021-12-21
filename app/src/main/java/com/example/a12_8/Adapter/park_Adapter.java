package com.example.a12_8.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.R;
import com.example.a12_8.bean.park_home_bean;
import com.example.a12_8.bean.park_jil_bean;
import com.example.a12_8.park.park_home;
import com.example.a12_8.park.park_init;
import com.example.a12_8.park.park_init_2;

import java.util.List;

public class park_Adapter extends RecyclerView.Adapter<park_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    private List<park_home_bean> home;
    private List<park_jil_bean> jil;
    public park_Adapter(Context context,List<park_home_bean> home,List<park_jil_bean> jil,int b){
        this.context=context;
        this.type=b;
        this.home=home;
        this.jil=jil;
    }
    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder = null;
        switch (type) {
            case 0:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.park_home_item, parent, false));
                break;
            case 1:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.jl_item, parent, false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
            switch (type){
                case 0:
                    holder.patkName.setText(home.get(position).getParkName());
                    holder.patkNumber.setText(home.get(position).getVacancy()+"个空位");
                    holder.patkAdress.setText(home.get(position).getAddress());
                    holder.patkKm.setText(home.get(position).getDistance()+"米");
                    holder.patkMoney.setText(home.get(position).getRates()+"元/小时");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            park_init.park_id=home.get(position).getId();
                            context.startActivity(new Intent(context,park_init.class));
                        }
                    });
                    break;
                case 1:
                    holder.jlEntryTime.setText("入场时间："+jil.get(position).getEntryTime());
                    holder.jlOutTime.setText("出场时间："+jil.get(position).getOutTime());
                    holder.jlPlateNumber.setText(jil.get(position).getPlateNumber());
                    holder.jlMonetary.setText("￥"+jil.get(position).getMonetary());
                    holder.jlParkName.setText("停车场名称："+jil.get(position).getParkName());
                    break;
            }
    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a= park_home.a;
                break;
            case 1:
                a= park_init_2.park_init_2_number;
                break;
        }
        return a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private TextView patkName;
        private TextView patkNumber;
        private TextView patkAdress;
        private TextView patkMoney;
        private TextView patkKm;

        private TextView jlPlateNumber;
        private TextView jlMonetary;
        private TextView jlEntryTime;
        private TextView jlOutTime;
        private TextView jlParkName;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    patkName = itemView.findViewById(R.id.patk_name);
                    patkNumber = itemView.findViewById(R.id.patk_number);
                    patkAdress = itemView.findViewById(R.id.patk_adress);
                    patkMoney = itemView.findViewById(R.id.patk_money);
                    patkKm = itemView.findViewById(R.id.patk_km);
                    break;
                case 1:
                    jlPlateNumber = itemView.findViewById(R.id.jl_plateNumber);
                    jlMonetary = itemView.findViewById(R.id.jl_monetary);
                    jlEntryTime = itemView.findViewById(R.id.jl_entryTime);
                    jlOutTime = itemView.findViewById(R.id.jl_outTime);
                    jlParkName = itemView.findViewById(R.id.jl_parkName);
                    break;
            }
        }
    }
}
