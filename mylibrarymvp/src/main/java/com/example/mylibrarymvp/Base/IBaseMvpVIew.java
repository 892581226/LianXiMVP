package com.example.mylibrarymvp.Base;

import com.example.mylibrarymvp.Base.IBaseMvpPresenter;

public interface IBaseMvpVIew<P extends IBaseMvpPresenter> {

    P createPresenter();
}
