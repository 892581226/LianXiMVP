package com.example.lianximvp.data.special;

import com.example.lianximvp.ParamsUtils;
import com.example.lianximvp.data.video.UserVideoList;
import com.example.mylibrarymvp.Base.BaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseCallBack;

import java.util.HashMap;

public class SpecialPresenter extends BaseMvpPresenter<ISpecialContract.ISpecialView> implements ISpecialContract.ISpecialPresenter {

    private ISpecialContract.ISpecialModel specialRePository;

    public SpecialPresenter(){
        specialRePository = new SpecialRePository();
    }

    @Override
    public void getListLan(int start, String pointTime) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("start",start+"");
        commonParams.put("point_time",pointTime);
        specialRePository.getListLan(commonParams, new IBaseCallBack<UserSpecialList>() {
            @Override
            public void onSuccessful(UserSpecialList date) {
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
