package com.example.lianximvp.data.video;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lianximvp.R;

import java.util.ArrayList;

class MyVideoItemAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<UserVideoList.ArticleList> lists;

    public MyVideoItemAdapter(Context context, ArrayList<UserVideoList.ArticleList> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        myHolder.tv1.setText(lists.get(position).getTheme());
        myHolder.tv2.setText(lists.get(position).getColumn_name());
        Glide.with(context).load(lists.get(position).getImage_url()).into(myHolder.iv);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv1;
        private TextView tv2;

        public MyHolder(View inflate) {
            super(inflate);
            iv =itemView.findViewById(R.id.video_center_iv);
            tv1 = itemView.findViewById(R.id.video_center_tv1);
            tv2 = itemView.findViewById(R.id.video_center_tv2);
        }
    }
}
