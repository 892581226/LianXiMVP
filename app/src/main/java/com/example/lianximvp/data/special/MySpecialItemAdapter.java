package com.example.lianximvp.data.special;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lianximvp.R;
import com.example.lianximvp.data.home.UserColumnListLan;
import com.example.lianximvp.data.home.homeChild.MyChildItemAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MySpecialItemAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<UserSpecialList.ArticleListS> articleLists;
    private ArrayList<UserSpecialList.BannerListS> bannerListS;

    public MySpecialItemAdapter(Context context, ArrayList<UserSpecialList.ArticleListS> articleLists, ArrayList<UserSpecialList.BannerListS> bannerListS) {
        this.context = context;
        this.articleLists = articleLists;
        this.bannerListS = bannerListS;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.mybanner, parent, false);
            return new MyBanner(inflate);
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.myrecycleritem, parent, false);
        return new MyHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==0){
            MyBanner myBanner= (MyBanner) holder;
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < bannerListS.size() ; i++) {
                strings.add(bannerListS.get(i).getTheme());
            }
            myBanner.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                    .setImageLoader(new MyLoader())
                    .setBannerTitles(strings)
                    .setImages(bannerListS)
                    .setDelayTime(3000)
                    .start();
        }
        if (itemViewType==1) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.tv1.setText(articleLists.get(position - 1).getTheme());
            myHolder.tv2.setText(articleLists.get(position - 1).getColumn_name());
            Glide.with(context).load(articleLists.get(position - 1).getImage_url()).into(myHolder.iv);
        }
    }

    @Override
    public int getItemCount() {
        return articleLists.size()+1;
    }

    private class MyBanner extends RecyclerView.ViewHolder {
        private Banner banner;
        public MyBanner(View inflate) {
            super(inflate);
            banner=itemView.findViewById(R.id.mybanner);
        }
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv1,tv2;
        public MyHolder(View inflate) {
            super(inflate);
            iv=itemView.findViewById(R.id.child_iv);
            tv1= itemView.findViewById(R.id.child_tv1);
            tv2= itemView.findViewById(R.id.child_tv2);
        }
    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            UserSpecialList.BannerListS bannerList= (UserSpecialList.BannerListS) path;
            Glide.with(context).load(bannerList.getImage_url()).into(imageView);
        }
    }
}
