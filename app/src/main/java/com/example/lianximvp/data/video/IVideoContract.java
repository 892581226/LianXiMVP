package com.example.lianximvp.data.video;

import com.example.lianximvp.data.home.IHomeContract;
import com.example.lianximvp.data.home.UserColumnListLan;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseMvpVIew;

import java.util.Map;

public interface IVideoContract {

    public interface IVideoView extends IBaseMvpVIew<IVideoContract.IVideoPresenter> {
        void onRegisterResult(UserVideoList data, String msg);
    }
    public interface IVideoPresenter extends IBaseMvpPresenter<IVideoContract.IVideoView> {
        void getListLan(int start,String pointTime);
    }
    public interface IVideoModel{
        void getListLan(Map<String,String> params, IBaseCallBack<UserVideoList> callBack);
    }


}
