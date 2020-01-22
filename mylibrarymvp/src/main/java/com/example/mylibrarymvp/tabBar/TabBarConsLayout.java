package com.example.mylibrarymvp.tabBar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class TabBarConsLayout extends ConstraintLayout {
    private MyOnClickLin myOnClick;

    public TabBarConsLayout(Context context) {
        this(context,null);
    }

    public TabBarConsLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabBarConsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setMyOnClick(MyOnClickLin myOnClick) {
        this.myOnClick = myOnClick;
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        View childAt;
        for (int i = 0; i <childCount; i++) {
            childAt = getChildAt(i);
            if (childAt instanceof LinearLayout){
                childAt.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (myOnClick!=null){
                            ChildTabBar childTabBar= (ChildTabBar) v;
                            childTabBar.select(true);
                            unSelectOther(childTabBar);
                            myOnClick.position(indexOfChild(v));
                        }
                    }
                });
            }
        }
    }

    public void select(int position){
        ChildTabBar childAt = (ChildTabBar) getChildAt(position);
        childAt.getOnClickListener().onClick(childAt);
    }

    public void unSelectOther(ChildTabBar childTabBar){
        int childCount = getChildCount();
        View child;
        for (int i = 0; i <childCount ; i++) {
            child = getChildAt(i);
            if (child instanceof ChildTabBar&&child!=childTabBar&&((ChildTabBar) child).isSelect()){
                ((ChildTabBar) child).select(false);
            }
        }
    }

    public interface MyOnClickLin{
        void position(int index);
    }
}
