package com.example.lianximvp.data.home;

import com.example.lianximvp.ParamsUtils;
import com.example.mylibrarymvp.Base.BaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseCallBack;

;import java.util.HashMap;

public class HomePresenter extends BaseMvpPresenter<IHomeContract.IHomePsdView> implements IHomeContract.IHomePsdPresenter{

    private IHomeContract.IHomePsdModel homeRePository;

    public HomePresenter(){
        homeRePository = new HomeRePository();
    }
    @Override
    public void registers() {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        homeRePository.register(commonParams, new IBaseCallBack<UserColumnList>() {
            @Override
            public void onSuccessful(UserColumnList date) {
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
