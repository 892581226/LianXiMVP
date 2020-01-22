package com.example.mylibrarymvp.Base;

public interface IBaseMvpPresenter<V extends IBaseMvpVIew> {
    void onAttachView(V view);
    void onDetachView();
}
