package com.example.a12_8.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.R;
import com.example.a12_8.zhihui.A_zhihui;
import com.example.a12_8.zhihui.huishou_init;

public class zhihzui_Adapter extends RecyclerView.Adapter<zhihzui_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    public zhihzui_Adapter(Context context,int a){
        this.context=context;
        this.type=a;
    }
    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder=null;
        switch (type){
            case 0:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.zz_item,parent,false));
                break;
            case 1:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.zhihui_sear_item,parent,false));
                break;
            case 2:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.hs_item,parent,false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        switch (type){
            case 0:
                switch (huishou_init.type){
                    case 1:
                        holder.huishouTitle.setText(A_zhihui.pl_text1.get(position));
                        holder.huishouData.setText(A_zhihui.pl_data1.get(position));
                        holder.huishouCount.setText(A_zhihui.pl_count1.get(position));
                        break;
                    case 2:
                        holder.huishouTitle.setText(A_zhihui.pl_text2.get(position));
                        holder.huishouData.setText(A_zhihui.pl_data2.get(position));
                        holder.huishouCount.setText(A_zhihui.pl_count2.get(position));
                        break;
                    case 3:
                        holder.huishouTitle.setText(A_zhihui.pl_text3.get(position));
                        holder.huishouData.setText(A_zhihui.pl_data3.get(position));
                        holder.huishouCount.setText(A_zhihui.pl_count3.get(position));
                        break;
                    case 4:
                        holder.huishouTitle.setText(A_zhihui.pl_text4.get(position));
                        holder.huishouData.setText(A_zhihui.pl_data4.get(position));
                        holder.huishouCount.setText(A_zhihui.pl_count4.get(position));
                        break;
                    case 5:
                        holder.huishouTitle.setText(A_zhihui.wz_text1.get(position));
                        holder.huishouData.setText(A_zhihui.wz_data1.get(position));
                        holder.huishouCount.setText(A_zhihui.wz_count1.get(position));
                        break;
                }
                break;
            case 1:
                holder.textView45.setText(A_zhihui.hb_title.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        huishou_init.type=position+1;
                        context.startActivity(new Intent(context,huishou_init.class));
                    }
                });
                break;
            case 2:
                holder.yyName.setText(A_zhihui.hs_name.get(position));
                holder.yyPhone.setText(A_zhihui.hs_phone.get(position));
                holder.yyAdress.setText(A_zhihui.hs_adress.get(position));
                holder.yyData.setText(A_zhihui.hs_data.get(position));
                holder.yyType.setText(A_zhihui.hs_type.get(position));
                break;
        }

    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                switch (huishou_init.type){
                    case 1:
                        a=A_zhihui.pl_text1.size();
                        break;
                    case 2:
                        a=A_zhihui.pl_text2.size();
                        break;
                    case 3:
                        a=A_zhihui.pl_text3.size();
                        break;
                    case 4:
                        a=A_zhihui.pl_text4.size();
                        break;
                        case 5:
                        a=A_zhihui.wz_text1.size();
                        break;
                }
                break;
            case 1:
                a=4;
                break;
            case 2:
                a=A_zhihui.hs_name.size();
                break;
        }
        return a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private TextView huishouTitle;
        private TextView huishouData;
        private TextView huishouCount;

        private TextView textView45;

        private TextView yyName;
        private TextView yyPhone;
        private TextView yyAdress;
        private TextView yyData;
        private TextView yyType;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    huishouTitle = itemView.findViewById(R.id.huishou_title);
                    huishouData = itemView.findViewById(R.id.huishou_data);
                    huishouCount = itemView.findViewById(R.id.huishou_count);
                    break;
                case 1:
                    textView45 = itemView.findViewById(R.id.textView45);
                    break;
                case 2:
                    yyName = itemView.findViewById(R.id.yy_name);
                    yyPhone = itemView.findViewById(R.id.yy_phone);
                    yyAdress = itemView.findViewById(R.id.yy_adress);
                    yyData = itemView.findViewById(R.id.yy_data);
                    yyType = itemView.findViewById(R.id.yy_type);

                    break;
            }
        }
    }
}
