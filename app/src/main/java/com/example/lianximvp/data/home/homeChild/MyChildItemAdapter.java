package com.example.lianximvp.data.home.homeChild;

import android.content.Context;
import android.os.MessageQueue;
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
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public  class MyChildItemAdapter extends RecyclerView.Adapter implements OnBannerListener {
    private Context context;
    private ArrayList<UserColumnListLan.ArticleList> articleLists;
    private ArrayList<UserColumnListLan.BannerList> bannerLists;
    private ArrayList<UserColumnListLan.Flashlist> flashlists;


    public MyChildItemAdapter(Context context, ArrayList<UserColumnListLan.ArticleList> articleLists, ArrayList<UserColumnListLan.BannerList> bannerLists,ArrayList<UserColumnListLan.Flashlist> flashlists) {
        this.context = context;
        this.articleLists = articleLists;
        this.bannerLists = bannerLists;
        this.flashlists=flashlists;
    }

    @Override
    public int getItemViewType(int position) {
        if (flashlists.size()>0) {
            if (position==0){
                return 1;
            }else if (position==1){
                return 7;
            }else if (articleLists.get(position - 2).getView_type().equals("1")) {
                return 2;
            } else if (articleLists.get(position - 2).getView_type().equals("2")) {
                return 3;
            } else if (articleLists.get(position - 2).getView_type().equals("3")) {
                return 4;
            } else if (articleLists.get(position - 2).getView_type().equals("4")) {
                return 5;
            } else {
                return 6;
            }
        }
        if (flashlists.size()==0) {
            if (position==0) {
                return 1;
            } else if (articleLists.get(position - 1).getView_type().equals("1")) {
                return 2;
            } else if (articleLists.get(position - 1).getView_type().equals("2")) {
                return 3;
            } else if (articleLists.get(position - 1).getView_type().equals("3")) {
                return 4;
            } else if (articleLists.get(position - 1).getView_type().equals("4")) {
                return 5;
            } else {
                return 6;
            }
        }

        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.mybanner, parent, false);
            return new MyBanner(inflate);
        }
        if (viewType==2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.myrecycleritem, parent, false);
            return new MyHolder(inflate);
        }
        if (viewType==3) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.my_home_center_image, parent, false);
            return new MyHolderCenter(inflate);
        }
        if (viewType==4) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.my_right_image, parent, false);
            return new MyHolderRight(inflate);
        }
        if (viewType==5) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.my_home_center_image, parent, false);
            return new MyHolderCenter(inflate);
        }
        if (viewType==6) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.my_home_jishi, parent, false);
            return new MyHolderJI(inflate);
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_marquee, parent, false);
        return new MarqueeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (flashlists.size()>0){
            if (itemViewType==1){
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < bannerLists.size() ; i++) {
                    strings.add(bannerLists.get(i).getTheme());
                }
                MyBanner myBanner= (MyBanner) holder;
                myBanner.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        //.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                        .setImageLoader(new MyLoader())
                        .setImages(bannerLists)
                        .setDelayTime(3000)
                        .setBannerTitles(strings)
                         .setOnBannerListener(this)
                        .start();
            }
            if (itemViewType==2) {
                MyHolder myHolder = (MyHolder) holder;
                myHolder.tv1.setText(articleLists.get(position - 2).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 2).getColumn_name());
                Glide.with(context).load(articleLists.get(position - 2).getImage_url()).into(myHolder.iv);
            }
            if (itemViewType==3) {
                MyHolderCenter myHolder = (MyHolderCenter) holder;
                myHolder.tv1.setText(articleLists.get(position - 2).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 2).getColumn_name());
                Glide.with(context).load(articleLists.get(position - 2).getImage_url()).into(myHolder.iv);
            }
            if (itemViewType==4) {
                MyHolderRight myHolder = (MyHolderRight) holder;
                myHolder.tv1.setText(articleLists.get(position - 2).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 2).getColumn_name());
                Glide.with(context).load(articleLists.get(position - 2).getImage_url()).into(myHolder.iv);
            }
            if (itemViewType==5) {
                MyHolderCenter myHolder = (MyHolderCenter) holder;
                myHolder.tv1.setText(articleLists.get(position - 2).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 2).getColumn_name());
                Glide.with(context).load(articleLists.get(position - 2).getImage_url()).into(myHolder.iv);
            }
            if (itemViewType==6) {
                MyHolderJI myHolder = (MyHolderJI) holder;
                myHolder.tv1.setText(articleLists.get(position - 2).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 2).getColumn_name());
            }
            if (itemViewType==7){
                MarqueeHolder marqueeHolder = (MarqueeHolder) holder;
                marqueeHolder.marquee.startFlipping();
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i <flashlists.size() ; i++) {
                    strings.add(flashlists.get(i).getTheme());
                }
                marqueeHolder.marquee.startWithList(strings);
            }
        }
        if (flashlists.size()==0){
            if (itemViewType==1){
                MyBanner myBanner= (MyBanner) holder;
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < bannerLists.size() ; i++) {
                    strings.add(bannerLists.get(i).getTheme());
                }
                myBanner.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setImageLoader(new MyLoader())
                        .setBannerTitles(strings)
                        .setImages(bannerLists)
                        .setDelayTime(3000)
                        .start();
            }
            if (itemViewType==2) {
                MyHolder myHolder = (MyHolder) holder;
                myHolder.tv1.setText(articleLists.get(position - 1).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 1).getColumn_name());
                Glide.with(context).load(articleLists.get(position - 1).getImage_url()).into(myHolder.iv);
            }
            if (itemViewType==3) {
                MyHolderCenter myHolder = (MyHolderCenter) holder;
                myHolder.tv1.setText(articleLists.get(position - 1).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 1).getColumn_name());
                Glide.with(context).load(articleLists.get(position - 1).getImage_url()).into(myHolder.iv);
            }
            if (itemViewType==4) {
                MyHolderRight myHolder = (MyHolderRight) holder;
                myHolder.tv1.setText(articleLists.get(position - 1).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 1).getColumn_name());
                Glide.with(context).load(articleLists.get(position - 1).getImage_url()).into(myHolder.iv);
            }
            if (itemViewType==5) {
                MyHolderCenter myHolder = (MyHolderCenter) holder;
                myHolder.tv1.setText(articleLists.get(position - 1).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 1).getColumn_name());
                Glide.with(context).load(articleLists.get(position - 1).getImage_url()).into(myHolder.iv);
            }
            if (itemViewType==6) {
                MyHolderJI myHolder = (MyHolderJI) holder;
                myHolder.tv1.setText(articleLists.get(position - 1).getTheme());
                myHolder.tv2.setText(articleLists.get(position - 1).getColumn_name());
            }
        }


    }
   /* public  <T>void string(T s){
    } */

    @Override
    public int getItemCount() {
        if (flashlists.size()>0){
            return articleLists.size()+2;
        }
        return articleLists.size()+1;
    }

    @Override
    public void OnBannerClick(int position) {

    }

    class  MyBanner extends RecyclerView.ViewHolder {
        private Banner banner;
        public MyBanner(@NonNull View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.mybanner);
        }

    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv1,tv2;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.child_iv);
            tv1= itemView.findViewById(R.id.child_tv1);
            tv2= itemView.findViewById(R.id.child_tv2);
        }
    }
    class MyHolderCenter extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv1,tv2;
        public MyHolderCenter(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.child_center_iv);
            tv1= itemView.findViewById(R.id.child_center_tv1);
            tv2= itemView.findViewById(R.id.child_center_tv2);
        }
    }
    class MyHolderRight extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv1,tv2;
        public MyHolderRight(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.child_right_iv);
            tv1= itemView.findViewById(R.id.child_right_tv1);
            tv2= itemView.findViewById(R.id.child_right_tv2);
        }
    }

    class MyHolderJI extends RecyclerView.ViewHolder {
        private TextView tv1,tv2;
        public MyHolderJI(@NonNull View itemView) {
            super(itemView);
            tv1= itemView.findViewById(R.id.child_jishi_tv1);
            tv2= itemView.findViewById(R.id.child_jishi_tv2);
        }
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            UserColumnListLan.BannerList bannerList= (UserColumnListLan.BannerList) path;
            Glide.with(context).load(bannerList.getImage_url()).into(imageView);
        }
    }
    public class MarqueeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.marquee)
        MarqueeView marquee;
        public MarqueeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

   // private MyOnBannerClick onBannerClick;

    //interface MyOnBannerClick
}
