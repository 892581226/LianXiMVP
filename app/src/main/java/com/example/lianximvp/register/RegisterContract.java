package com.example.lianximvp.register;

import com.example.lianximvp.data.User;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;
import com.example.mylibrarymvp.Base.IBaseMvpVIew;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

public interface RegisterContract {

    public interface registerView extends IBaseMvpVIew<registerPresenter> {
        void onSmsCodeResult(String msg,boolean successful);
        void onVerifySmsCodeResult(String msg,boolean successful);
        void onVerifyCodeResult(User data,boolean successful,String msg);
    }

    public interface registerPresenter extends IBaseMvpPresenter<registerView>, IBaseMvpVIew {
        void getSmCall(String phoneName ,String id);
        void verifySmCall(String phoneName,String code);
        void verifyCall(String phoneName,String code);
    }

    public interface registerModel {
        void getSmCall(Map<String ,String> params, IBaseCallBack<String> callBack);
        void verifySmCall(Map<String ,String> params, IBaseCallBack<String> callBack);
        void verifyCall(Map<String ,String> params, IBaseCallBack<User> callBack);
    }


    //设置密码————————————————————————————

    public interface IRegisterSetView extends IBaseMvpVIew<IRegisterSetPresenter>{
        void onRegisterResult(User user,String msg);
    }
    public interface IRegisterSetPresenter extends IBaseMvpPresenter<IRegisterSetView> {
        void register(String phoneNum,String password,String confirmPassword);
    }
    public interface IRegisterSetModel{
        void register(LifecycleProvider provider, Map<String,String> params, IBaseCallBack<User> callBack);
    }
}
