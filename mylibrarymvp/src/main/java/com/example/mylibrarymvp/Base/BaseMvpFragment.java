package com.example.mylibrarymvp.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseMvpFragment<P extends IBaseMvpPresenter> extends BaseFragment implements IBaseMvpVIew<P> {

    protected P mPresent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresent = createPresenter();
        if (mPresent!=null){
            mPresent.onAttachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresent!=null){
            mPresent.onDetachView();
        }
    }
}
