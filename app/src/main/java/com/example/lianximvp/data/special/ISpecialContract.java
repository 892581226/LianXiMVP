package com.example.lianximvp.data.special;

import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseMvpVIew;

import java.util.Map;

public class ISpecialContract {

    public interface ISpecialView extends IBaseMvpVIew<ISpecialPresenter> {
        void onRegisterResult(UserSpecialList data, String msg);
    }
    public interface ISpecialPresenter extends IBaseMvpPresenter<ISpecialView> {
        void getListLan(int start,String pointTime);
    }
    public interface ISpecialModel{
        void getListLan(Map<String,String> params, IBaseCallBack<UserSpecialList> callBack);
    }

}
