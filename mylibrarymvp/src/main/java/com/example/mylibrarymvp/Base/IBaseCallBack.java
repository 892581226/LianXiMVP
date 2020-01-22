package com.example.mylibrarymvp.Base;

public interface IBaseCallBack<T> {

    void onSuccessful(T date);
    void onFail(String msg);
}
