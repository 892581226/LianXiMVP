package com.example.mylibrarymvp.tabBar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ChildTabBar extends LinearLayout {
    private OnClickListener onClickListener;
    private ChildTabBarIv mIcon;
    private  TextView mTitle;
    public ChildTabBar(Context context) {
        super(context);
    }

    public ChildTabBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildTabBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.onClickListener = onClickListener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public  void select(boolean select){
        if (mIcon.isChecked()==select){
            return;
        }
        mIcon.setChecked(select);
        if (select){
            mTitle.setTextColor(Color.BLACK);
        }else {
            mTitle.setTextColor(Color.GRAY);
        }
    }
    public  void setTitle(String title){
        mTitle.setText(title);
    }

    public boolean isSelect(){
        return mIcon.isChecked();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        View child;
        for (int i = 0; i <childCount ; i++) {
            child = getChildAt(i);
            if (child instanceof ChildTabBarIv) {
                mIcon= (ChildTabBarIv) child;
            }else {
                mTitle= (TextView) child;
            }
        }
    }
}
