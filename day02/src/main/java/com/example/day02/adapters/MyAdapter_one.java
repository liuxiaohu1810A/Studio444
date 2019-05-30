package com.example.day02.adapters;

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
import com.example.day02.R;
import com.example.day02.bean.DatasBean;
import com.example.day02.bean.User;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class MyAdapter_one extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<User.DataBean> listban;
    private List<DatasBean> list;
    private int mi;
    public MyAdapter_one(Context context, List<User.DataBean> listban, List<DatasBean> list) {
        this.context = context;
        this.listban = listban;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            View view = View.inflate(context, R.layout.fargment_one_ban, null);
            return new ViewHolder(view);
        }else{
            View view = View.inflate(context, R.layout.fragment_one_item, null);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        if (type==0){
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.ban.setImages(listban).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    User.DataBean path1 = (User.DataBean) path;
                    Glide.with(context).load(path1.getImagePath()).into(imageView);
                }
            }).start();
        }else{
            ViewHolder2 holder1 = (ViewHolder2) viewHolder;
            if (listban.size()>0){
                mi=i-1;
            }
            holder1.title.setText(list.get(mi).getTitle());
            Glide.with(context).load(list.get(mi).getEnvelopePic())
                    .apply(new RequestOptions().circleCrop()).into(holder1.tu);
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ii = new Intent("ii");
                    ii.putExtra("data",mi);
                    context.sendBroadcast(ii);
                }
            });
            holder1.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent pp = new Intent("pp");
                    pp.putExtra("data",mi);
                    context.sendBroadcast(pp);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (listban.size()>0){
            return list.size()+1;
        }else{
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int i) {
        if (i==0&&listban.size()>0){
            return 0;
        }else {
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final Banner ban;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ban = itemView.findViewById(R.id.ban);
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
