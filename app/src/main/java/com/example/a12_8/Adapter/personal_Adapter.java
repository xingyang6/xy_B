package com.example.a12_8.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.R;
import com.example.a12_8.bean.bus_dd_bean;
import com.example.a12_8.personal.persoanl_2;
import com.example.a12_8.util.Network;
import com.example.a12_8.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class personal_Adapter extends RecyclerView.Adapter<personal_Adapter.LearViewHolder> {
    private Context context;
//    private int type;
    private List<bus_dd_bean> bus;


    public personal_Adapter(Context context, List<bus_dd_bean> bus) {
        this.context = context;
        this.bus = bus;
    }

    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.personal_2_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        holder.personal2OrderNum.setText("订单："+bus.get(position).getOrderNum());
        holder.personal2Price.setText("￥"+bus.get(position).getPrice());
        holder.personal2Start.setText(bus.get(position).getStart());
        holder.personal2End.setText(bus.get(position).getEnd());
        holder.personal2Path.setText("线路          "+bus.get(position).getPath());
        holder.personal2UserName.setText("乘车人        "+bus.get(position).getUserName());
        holder.personal2UserTel.setText("手机号        "+bus.get(position).getUserTel());
        if (bus.get(position).getStatus()==0){
            holder.button.setText("立即支付");
            holder.button.setEnabled(true);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        //支付
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("orderNum" ,bus.get(position).getOrderNum())
                                .put("paymentType","电子支付");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPost("/prod-api/api/bus/pay", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                                //刷新订单
                                Network.doGet("/prod-api/api/bus/order/list?status=0", new OkResult() {
                                    @Override
                                    public void succes(JSONObject jsonObject) {
                                        if (jsonObject.optInt("code")==200){
                                            persoanl_2.unzf.setBackgroundColor(Color.rgb(33,150,243));
                                            persoanl_2.unzf.setTextColor(Color.rgb(255,255,255));
                                            persoanl_2.zf.setBackgroundColor(Color.rgb(255,255,255));
                                            persoanl_2.zf.setTextColor(Color.rgb(33,150,243));
                                            List<bus_dd_bean> dd=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<bus_dd_bean>>(){
                                            }.getType());
                                            persoanl_2.personal2Recy.setLayoutManager(new LinearLayoutManager(context));
                                            persoanl_2.personal2Recy.setAdapter(new personal_Adapter(context,dd));

                                        }else{
                                            Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }else{
            holder.button.setText("已支付");
            holder.button.setEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        return bus.size();
    }


    public class LearViewHolder extends RecyclerView.ViewHolder {
        private TextView personal2OrderNum;
        private TextView personal2Price;
        private TextView personal2Start;
        private TextView personal2End;
        private TextView personal2Path;
        private TextView personal2UserName;
        private TextView personal2UserTel;
        private Button button;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            personal2OrderNum = itemView.findViewById(R.id.personal_2_orderNum);
            personal2Price = itemView.findViewById(R.id.personal_2_price);
            personal2Start = itemView.findViewById(R.id.personal_2_start);
            personal2End = itemView.findViewById(R.id.personal_2_end);
            personal2Path = itemView.findViewById(R.id.personal_2_path);
            personal2UserName = itemView.findViewById(R.id.personal_2_userName);
            personal2UserTel = itemView.findViewById(R.id.personal_2_userTel);
            button = itemView.findViewById(R.id.button7);
        }
    }
}
