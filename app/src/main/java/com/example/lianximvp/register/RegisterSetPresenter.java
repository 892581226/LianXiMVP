package com.example.lianximvp.register;

import com.example.lianximvp.AppConstant;
import com.example.lianximvp.ParamsUtils;
import com.example.lianximvp.data.User;
import com.example.mylibrarymvp.Base.BaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

public class RegisterSetPresenter extends BaseMvpPresenter<RegisterContract.IRegisterSetView> implements RegisterContract.IRegisterSetPresenter{
    private RegisterContract.IRegisterSetModel mRepository;


    public RegisterSetPresenter(){
        mRepository = new RegisterSetRepository();
    }
    @Override
    public void register(String phoneNum, String password, String confirmPassword) {

        Map<String,String> params = ParamsUtils.getCommonParams();

        params.put(AppConstant.RequestKey.AUTH_REGISTER_MOBILE, phoneNum);
        params.put(AppConstant.RequestKey.AUTH_REGISTER_PASSWORD, password);
        params.put(AppConstant.RequestKey.AUTH_REGISTER_CONFIRM_PASSWORD, confirmPassword);

        mRepository.register((LifecycleProvider) mView, params, new IBaseCallBack<User>() {

            @Override
            public void onSuccessful(User date) {
                if(mView  != null){
                    mView.onRegisterResult(date, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView  != null){
                    mView.onRegisterResult(null , msg);
                }
            }
        });
    }
}
