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
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_8.R;
import com.example.a12_8.bean.ditie_home_bean;
import com.example.a12_8.bean.ditie_init_bean;
import com.example.a12_8.ditie.ditie_init;

import java.util.List;

public class ditie_Adapter extends RecyclerView.Adapter<ditie_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    private List<ditie_home_bean> honme;
    private ditie_init_bean init;
    public ditie_Adapter(Context context,List<ditie_home_bean> honme,  ditie_init_bean init,int a) {
        this.context = context;
        this.type = a;
        this.honme =honme;
        this.init=init;
    }

    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder = null;
        switch (type) {
            case 0:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.ditie_home_item, parent, false));
                break;
            case 1:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.ditie_init_item, parent, false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        switch (type) {
            case 0:
                holder.dtName.setText(honme.get(position).getLineName());
                holder.dtMinte.setText(honme.get(position).getReachTime()+"分钟");
                holder.dtNextname.setText("下一站:"+honme.get(position).getPreStep().getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ditie_init.xl_id=honme.get(position).getLineId();
                        context.startActivity(new Intent(context,ditie_init.class));
                    }
                });
                break;
            case 1:
                if (init.getRunStationsName().equals(init.getMetroStepList().get(position).getName())){
                    holder.ditieInitImg1.setVisibility(View.VISIBLE);
                }else{
                    holder.ditieInitImg1.setVisibility(View.INVISIBLE);
                }
                holder.ditieInitText.setText(init.getMetroStepList().get(position).getName());
                break;
        }

    }

    @Override
    public int getItemCount() {
        int a = 0;
        switch (type) {
            case 0:
                a = honme.size();
                break;
            case 1:
                a = init.getMetroStepList().size();
                break;
        }
        return a;
    }


    public class LearViewHolder extends RecyclerView.ViewHolder {
        private TextView dtName;
        private TextView dtNextname;
        private TextView dtMinte;

        private ImageView ditieInitImg1;
        private TextView ditieInitText;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type) {
                case 0:
                    dtName = itemView.findViewById(R.id.dt_name);
                    dtNextname = itemView.findViewById(R.id.dt_nextname);
                    dtMinte = itemView.findViewById(R.id.dt_minte);
                    break;
                case 1:
                    ditieInitImg1 = itemView.findViewById(R.id.ditie_init_img1);
                    ditieInitText = itemView.findViewById(R.id.ditie_init_text);
                    break;
            }
        }
    }
}
