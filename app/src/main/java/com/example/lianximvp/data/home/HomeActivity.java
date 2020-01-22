package com.example.lianximvp.data.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lianximvp.R;
import com.example.lianximvp.data.home.homeChild.HomeChildFragment;
import com.example.lianximvp.data.mime.MimeFragment;
import com.example.lianximvp.data.special.SpecialFragment;
import com.example.lianximvp.data.video.VideoFragment;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.example.mylibrarymvp.manager.MvpFragmentManger;
import com.example.mylibrarymvp.tabBar.ChildTabBar;
import com.example.mylibrarymvp.tabBar.TabBarConsLayout;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements TabBarConsLayout.MyOnClickLin {

    private TabBarConsLayout tab;
    private FrameLayout framel;

    @Override
    protected void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_home);
        tab = findViewById(R.id.tab_layout);
        framel = findViewById(R.id.frame1);
        tab.setMyOnClick(this);
        tab.select(1);

    }

    @Override
    public void position(int index) {
        switch (index){
            case 1:
              /*  MvpFragmentManger.addOrShowFragment(getSupportFragmentManager(),
                    HomeFragment.class,null,R.id.frame1, HomeFragment.class.getName(),null);*/
                getSupportFragmentManager().beginTransaction().replace(R.id.frame1,
                        new HomeFragment(),HomeFragment.class.getSimpleName()).commit();
                break;
            case 2:
               /* MvpFragmentManger.addOrShowFragment(getSupportFragmentManager(),
                        VideoFragment.class,null, R.id.frame1,VideoFragment.class.getName(),null);*/
                getSupportFragmentManager().beginTransaction().replace(R.id.frame1,
                        new VideoFragment(),VideoFragment.class.getSimpleName()).commit();
                break;
            case 3:
                /*MvpFragmentManger.addOrShowFragment(getSupportFragmentManager(),
                        SpecialFragment.class, null, R.id.frame1,SpecialFragment.class.getName(),null);*/
                getSupportFragmentManager().beginTransaction().replace(R.id.frame1,
                        new SpecialFragment(),SpecialFragment.class.getSimpleName()).commit();
                break;
            case 4:
                /*MvpFragmentManger.addOrShowFragment(getSupportFragmentManager(),
                        MimeFragment.class, null,R.id.frame1,MimeFragment.class.getName(),null);*/
                getSupportFragmentManager().beginTransaction().replace(R.id.frame1,
                        new MimeFragment(),MimeFragment.class.getSimpleName()).commit();
                break;

        }
    }
}
