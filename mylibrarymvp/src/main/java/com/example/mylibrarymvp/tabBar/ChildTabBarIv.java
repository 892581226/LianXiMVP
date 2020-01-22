package com.example.mylibrarymvp.tabBar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;

import androidx.appcompat.widget.AppCompatImageView;

public class ChildTabBarIv extends AppCompatImageView implements Checkable {

    int[] CHECKABLE_STATE_SET={android.R.attr.state_checked};
    private boolean isCheckable;

    public ChildTabBarIv(Context context) {
        super(context);
    }

    public ChildTabBarIv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildTabBarIv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState=super.onCreateDrawableState(extraSpace+1);
        if (isCheckable){
            mergeDrawableStates(drawableState,CHECKABLE_STATE_SET);
        }
        return drawableState;
    }

    @Override
    public void setChecked(boolean checked) {
        if (isCheckable!=checked){
            isCheckable=checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return isCheckable;
    }

    @Override
    public void toggle() {
        setChecked(!isCheckable);
    }
}
