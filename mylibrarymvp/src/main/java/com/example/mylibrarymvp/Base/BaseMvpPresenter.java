package com.example.mylibrarymvp.Base;

public class BaseMvpPresenter<V extends IBaseMvpVIew> implements IBaseMvpPresenter<V> {

    protected V mView;
    @Override
    public void onAttachView(V view) {
        mView=view;
    }

    @Override
    public void onDetachView() {
        mView=null;
    }
}
