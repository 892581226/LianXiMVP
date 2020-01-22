package com.example.lianximvp.data.login;

import com.example.lianximvp.data.User;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseMvpVIew;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

public interface LoginContract {

    public interface ILoginPsdView extends IBaseMvpVIew<LoginContract.ILoginPsdPresenter> {
        void onRegisterResult(boolean success, String msg);
    }
    public interface ILoginPsdPresenter extends IBaseMvpPresenter<LoginContract.ILoginPsdView> {
        void registers(String phoneNum,String password);
    }
    public interface ILoginPsdModel{
        void register(LifecycleProvider provider, Map<String,String> params, IBaseCallBack<User> callBack);
    }
}
