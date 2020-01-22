package com.example.mylibrarymvp.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseMvpActivity<P extends IBaseMvpPresenter> extends BaseActivity implements IBaseMvpVIew<P> {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= createPresenter();
        if (mPresenter!=null){
            mPresenter.onAttachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDetachView();
        }
    }
}
