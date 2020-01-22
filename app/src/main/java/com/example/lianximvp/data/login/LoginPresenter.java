package com.example.lianximvp.data.login;

import com.example.lianximvp.ParamsUtils;
import com.example.lianximvp.data.User;
import com.example.mylibrarymvp.Base.BaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;

import static com.example.lianximvp.AppConstant.RequestKey.AUTH_REGISTER_PASSWORD;
import static com.example.lianximvp.AppConstant.RequestKey.USERNAME_LOGIN;

public class LoginPresenter extends BaseMvpPresenter<LoginContract.ILoginPsdView> implements LoginContract.ILoginPsdPresenter {

    private LoginContract.ILoginPsdModel mReository;

    public LoginPresenter(){
        mReository =new LoginRePository();
    }


    @Override
    public void registers(String phoneNum, String password) {
        HashMap<String, String> hashMap = ParamsUtils.getCommonParams();
        hashMap.put(USERNAME_LOGIN,phoneNum);
        hashMap.put(AUTH_REGISTER_PASSWORD,password);
        mReository.register((LifecycleProvider) mView,hashMap, new IBaseCallBack<User>() {
            @Override
            public void onSuccessful(User data) {
                if (mView!=null){
                    if(data!=null)
                        mView.onRegisterResult(true,data.toString());
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView!=null){
                    mView.onRegisterResult(false,msg);
                }
            }
        });
    }
}
