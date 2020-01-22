package com.example.lianximvp.data.home;

import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.example.lianximvp.R;
import com.example.lianximvp.data.home.homeChild.HomeChildFragment;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<IHomeContract.IHomePsdPresenter> implements IHomeContract.IHomePsdView {

    private ViewPager viewPager;
    private TabLayout tab;
    private MyAdapter myAdapter;
    private ArrayList<UserColumnList.Lists> name;
    private ArrayList<Fragment> list;
    private DrawerLayout draw;
    private ImageView openNav;
    private NavigationView nav;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public IHomeContract.IHomePsdPresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mPresent.registers();
        viewPager = getView().findViewById(R.id.viewPager);
        tab = getView().findViewById(R.id.tab);
        draw = getView().findViewById(R.id.draw);
        openNav = getView().findViewById(R.id.open_nav);
        nav = getView().findViewById(R.id.nav);
        list = new ArrayList<>();
        name = new ArrayList<>();
        myAdapter = new MyAdapter(getChildFragmentManager(), list,name);
        viewPager.setAdapter(myAdapter);
        tab.setupWithViewPager(viewPager);
        openNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.openDrawer(nav);
            }
        });
    }

    @Override
    public void onRegisterResult(UserColumnList data, String msg) {
        ArrayList<UserColumnList.Lists> list1 = data.getList();
        HomeChildFragment fragment;
        for (int i = 0; i <list1.size() ; i++) {
            fragment=new HomeChildFragment(list1.get(i).getId());
            list.add(fragment);
        }
        name.addAll(list1);
       myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean isAddBackStack() {
        return false;
    }

}
