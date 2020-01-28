package com.example.lianximvp.data.video;

import com.example.lianximvp.ParamsUtils;
import com.example.mylibrarymvp.Base.BaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseCallBack;

import java.util.HashMap;

public class UserPresenter extends BaseMvpPresenter<IVideoContract.IVideoView> implements IVideoContract.IVideoPresenter {

    private IVideoContract.IVideoModel videoRePository;

    public UserPresenter(){
        videoRePository = new VideoRePository();
    }

    @Override
    public void getListLan(int start, String pointTime) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("start",start+"");
        commonParams.put("point_time",pointTime);
        videoRePository.getListLan(commonParams, new IBaseCallBack<UserVideoList>() {
            @Override
            public void onSuccessful(UserVideoList date) {
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
