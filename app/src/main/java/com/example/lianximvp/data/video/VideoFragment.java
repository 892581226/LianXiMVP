package com.example.lianximvp.data.video;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.lianximvp.R;
import com.example.lianximvp.data.home.UserColumnListLan;
import com.example.lianximvp.data.home.homeChild.MyChildItemAdapter;
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
public class VideoFragment extends BaseMvpFragment<IVideoContract.IVideoPresenter> implements IVideoContract.IVideoView {

    private SmartRefreshLayout smart;
    private RecyclerView recycler;
    private ArrayList<UserVideoList.ArticleList> articleLists;
    private MyVideoItemAdapter myVideoItem;
    private int start;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public IVideoContract.IVideoPresenter createPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mPresent.getListLan(0,"0");
        smart = getView().findViewById(R.id.smartVideo);
        recycler = getView().findViewById(R.id.recycler_item_Video);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        articleLists = new ArrayList<>();
        myVideoItem = new MyVideoItemAdapter(getContext(), articleLists);
        recycler.setAdapter(myVideoItem);

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
    public void onRegisterResult(UserVideoList data, String msg) {
        int more = data.getMore();
        int start = data.getStart();

        this.start=start;
        ArrayList<UserVideoList.ArticleList> article_list = data.getList();
        if(data!=null){
            RefreshState state = smart.getState();
            if (state==RefreshState.Refreshing){
                article_list.clear();
                smart.finishRefresh(true);
            }else if (state==RefreshState.Loading&&more==1){
                smart.finishLoadMore(true);
            }
            articleLists.addAll(article_list);
            myVideoItem.notifyDataSetChanged();
        }
    }
}
