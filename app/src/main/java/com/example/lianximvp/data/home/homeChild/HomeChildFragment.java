package com.example.lianximvp.data.home.homeChild;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import com.example.lianximvp.R;
import com.example.lianximvp.data.home.IHomeContract;
import com.example.lianximvp.data.home.UserColumnListLan;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeChildFragment extends BaseMvpFragment<IHomeContract.IHomeLanPsdPresenter> implements IHomeContract.IHomeLanPsdView {

    private String id;
    private RecyclerView recycler;
    private ArrayList<UserColumnListLan.BannerList> bannerLists;
    private ArrayList<UserColumnListLan.ArticleList> articleLists;
    private ArrayList<UserColumnListLan.Flashlist> flashlists;
    private MyChildItemAdapter myChildItem;
    private int intId=0;
    private SmartRefreshLayout smart;
    private int start=0;

    public HomeChildFragment(String id ){
        this.id=id;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_child;
    }

    @Override
    public IHomeContract.IHomeLanPsdPresenter createPresenter() {
        return new HomeChildPresenter();
    }

    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mPresent.registers(id,0,"1","0");
        smart = getView().findViewById(R.id.smart);
        recycler = getView().findViewById(R.id.recycler_item_child);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        bannerLists = new ArrayList<>();
        articleLists = new ArrayList<>();
        flashlists = new ArrayList<>();
        myChildItem = new MyChildItemAdapter(getContext(),articleLists,bannerLists,flashlists);
        recycler.setAdapter(myChildItem);

        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresent.registers(id,0,"1","0");

            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPresent.registers(id,start,"1","0");
            }
        });

    }

    @Override
    public void onRegisterResult(UserColumnListLan data, String msg) {
        int more = data.getMore();
        int start = data.getStart();
        this.start=start;
        ArrayList<UserColumnListLan.BannerList> banner_list = data.getBanner_list();
        ArrayList<UserColumnListLan.ArticleList> article_list = data.getArticle_list();
        ArrayList<UserColumnListLan.Flashlist> flash_list = data.getFlash_list();
        if(data!=null){
            RefreshState state = smart.getState();
            if (state==RefreshState.Refreshing){
                banner_list.clear();
                article_list.clear();
                flash_list.clear();
                smart.finishRefresh(true);
            }else if (state==RefreshState.Loading&&more==1){
                smart.finishLoadMore(true);
            }
            bannerLists.addAll(banner_list);
            articleLists.addAll(article_list);
            flashlists.addAll(flash_list);
            myChildItem.notifyDataSetChanged();
        }
    }
}
