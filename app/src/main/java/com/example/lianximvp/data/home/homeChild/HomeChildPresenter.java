package com.example.lianximvp.data.home.homeChild;

import com.example.lianximvp.ParamsUtils;
import com.example.lianximvp.data.home.IHomeContract;
import com.example.lianximvp.data.home.UserColumnListLan;
import com.example.mylibrarymvp.Base.BaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseCallBack;

import java.util.HashMap;

public class HomeChildPresenter extends BaseMvpPresenter<IHomeContract.IHomeLanPsdView> implements IHomeContract.IHomeLanPsdPresenter {

    private IHomeContract.IHomeLanPsdModel homeRePository;

    public HomeChildPresenter(){
        homeRePository = new HomeChildRePository();
    }

    @Override
    public void registers(String id, String start, String random, String pointTime) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",id);
        commonParams.put("start",start);
        commonParams.put("random",random);
        commonParams.put("point_time",pointTime);
        homeRePository.register(commonParams, new IBaseCallBack<UserColumnListLan>() {
            @Override
            public void onSuccessful(UserColumnListLan date) {
                if (mView!=null)
                    mView.onRegisterResult(date,null);
            }

            @Override
            public void onFail(String msg) {
                if (mView!=null)
                    mView.onRegisterResult(null,msg);
            }
        });
    }
}
