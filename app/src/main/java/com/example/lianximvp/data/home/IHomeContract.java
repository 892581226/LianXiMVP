package com.example.lianximvp.data.home;

import com.example.lianximvp.data.User;
import com.example.lianximvp.data.login.LoginContract;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseMvpVIew;

import java.util.Map;

public interface IHomeContract {
    public interface IHomePsdView extends IBaseMvpVIew<IHomeContract.IHomePsdPresenter> {
        void onRegisterResult(UserColumnList data, String msg);
    }
    public interface IHomePsdPresenter extends IBaseMvpPresenter<IHomeContract.IHomePsdView> {
        void registers();
    }
    public interface IHomePsdModel{
        void register(Map<String,String> params, IBaseCallBack<UserColumnList> callBack);
    }
/*----------------------------------------------------------------------------------*/
    public interface IHomeLanPsdView extends IBaseMvpVIew<IHomeContract.IHomeLanPsdPresenter> {
        void onRegisterResult(UserColumnListLan data, String msg);
    }
    public interface IHomeLanPsdPresenter extends IBaseMvpPresenter<IHomeContract.IHomeLanPsdView> {
        void registers(String id,String start,String random,String pointTime);
    }
    public interface IHomeLanPsdModel{
        void register(Map<String,String> params, IBaseCallBack<UserColumnListLan> callBack);
    }
}
