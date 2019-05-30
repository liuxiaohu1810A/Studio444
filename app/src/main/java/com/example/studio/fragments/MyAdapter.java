package com.example.studio.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.studio.R;
import com.example.studio.User;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<User.DataBean.DatasBean> list;

    public MyAdapter(Context context, List<User.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            View view = View.inflate(context, R.layout.fragmentone_item2, null);
            return new ViewHolder(view);
        }else{
            View view = View.inflate(context, R.layout.fragmentone_item, null);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        if (type==0){
            ViewHolder holder = (ViewHolder) viewHolder;
            Glide.with(context).load(list.get(i).getEnvelopePic()).apply(new RequestOptions().circleCrop()).into(holder.tu);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iii = new Intent("iii");
                    iii.putExtra("data",i);
                    context.sendBroadcast(iii);
                }
            });

        }else{
            ViewHolder2 holder2 = (ViewHolder2) viewHolder;
            holder2.title.setText(list.get(i).getDesc());
            Glide.with(context).load(list.get(i).getEnvelopePic()).apply(new RequestOptions().circleCrop()).into(holder2.tu);
            holder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent uuu = new Intent("uuu");
                    uuu.putExtra("data",i);
                    context.sendBroadcast(uuu);
                }
            });
            holder2.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent jjj = new Intent("jjj");
                    jjj.putExtra("da",i);
                    context.sendBroadcast(jjj);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==2||position==9||position==17){
            return 0;
        }else{
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView tu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tu = itemView.findViewById(R.id.iv_tu);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{
        private final ImageView tu;
        private final TextView title;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            tu = itemView.findViewById(R.id.iv_tu);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
}
