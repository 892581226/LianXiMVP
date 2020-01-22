package com.example.lianximvp.register;

import com.example.lianximvp.ParamsUtils;
import com.example.lianximvp.data.User;
import com.example.lianximvp.register.RegisterRePository;
import com.example.lianximvp.register.RegisterContract;
import com.example.mylibrarymvp.Base.BaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;

import java.util.HashMap;

import static com.example.lianximvp.AppConstant.RequestKey.AUTH_REGISTER_MOBILE;
import static com.example.lianximvp.AppConstant.RequestKey.AUTH_REGISTER_TYPE;
import static com.example.lianximvp.AppConstant.RequestKey.VERIFICATION_CODE;

public class RegisterPresenter extends BaseMvpPresenter< RegisterContract.registerView> implements RegisterContract.registerPresenter {
    private RegisterContract.registerModel mReository;
    public RegisterPresenter(){
       mReository= new RegisterRePository();
    }
    @Override
    public void getSmCall(String phoneName,String id) {
        HashMap<String, String> hashMap = ParamsUtils.getCommonParams();
        hashMap.put(AUTH_REGISTER_MOBILE,phoneName);
        hashMap.put(AUTH_REGISTER_TYPE,id);
        mReository.getSmCall(hashMap, new IBaseCallBack<String>() {
            @Override
            public void onSuccessful(String date) {
                if (mView!=null){
                    mView.onSmsCodeResult(date,true);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView!=null){
                    mView.onVerifySmsCodeResult(msg,false);
                }
            }
        });
    }

    @Override
    public void verifySmCall(String phoneName, String code) {
        HashMap<String,String> hashMap = ParamsUtils.getCommonParams();
        hashMap.put(AUTH_REGISTER_MOBILE, phoneName);
        hashMap.put(AUTH_REGISTER_TYPE,"1");
        hashMap.put(VERIFICATION_CODE,code);
        mReository.verifySmCall(hashMap, new IBaseCallBack<String>() {

            @Override
            public void onSuccessful(String date) {
                if(mView != null){
                    mView.onVerifySmsCodeResult(date, true);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onVerifySmsCodeResult(msg, false);
                }
            }
        });
    }

    @Override
    public void verifyCall(String phoneName, String code) {
        HashMap<String,String> hashMap = ParamsUtils.getCommonParams();
        hashMap.put(AUTH_REGISTER_MOBILE, phoneName);
        /*hashMap.put(AUTH_REGISTER_TYPE,"4");*/
        hashMap.put(VERIFICATION_CODE,code);
        mReository.verifyCall(hashMap, new IBaseCallBack<User>() {

            @Override
            public void onSuccessful(User date) {
                if(mView != null){
                    mView.onVerifyCodeResult(date, true,null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onVerifyCodeResult(null, false,msg);
                }
            }
        });
    }


    @Override
    public IBaseMvpPresenter createPresenter() {
        return null;
    }
}
