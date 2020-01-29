package com.example.lianximvp.data.special;


import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lianximvp.R;
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
public class SpecialFragment extends BaseMvpFragment<ISpecialContract.ISpecialPresenter> implements ISpecialContract.ISpecialView {


    private SmartRefreshLayout smart;
    private RecyclerView recycler;
    private ArrayList<UserSpecialList.ArticleListS> articleLists;
    private MySpecialItemAdapter mySpecialItem;
    private ArrayList<UserSpecialList.BannerListS> bannerListS;
    private int start;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_special;
    }

    @Override
    public ISpecialContract.ISpecialPresenter createPresenter() {
        return new SpecialPresenter();
    }


    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mPresent.getListLan(0,"0");
        smart = getView().findViewById(R.id.smartSpecial);
        recycler = getView().findViewById(R.id.recycler_item_special);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        articleLists = new ArrayList<>();
        bannerListS = new ArrayList<>();
        mySpecialItem = new MySpecialItemAdapter(getContext(), articleLists,bannerListS);
        recycler.setAdapter(mySpecialItem);

        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresent.getListLan(0,"0");

            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPresent.getListLan(start,"0");
            }
        });

    }

    @Override
    public void onRegisterResult(UserSpecialList data, String msg) {
        int more = data.getMore();
        int start = data.getStart();
        this.start=start;
        ArrayList<UserSpecialList.BannerListS> banner_list = data.getBanner_list();
        ArrayList<UserSpecialList.ArticleListS> article_list = data.getList();

        if(data!=null){
            RefreshState state = smart.getState();
            if (state==RefreshState.Refreshing){
                banner_list.clear();
                article_list.clear();
                smart.finishRefresh(true);
            }else if (state==RefreshState.Loading&&more==1){
                smart.finishLoadMore(true);
            }
            bannerListS.addAll(banner_list);
            articleLists.addAll(article_list);
            mySpecialItem.notifyDataSetChanged();
        }
    }
}
