package com.example.lianximvp.data.login;

import android.util.Log;

import com.example.lianximvp.JDDataService;
import com.example.lianximvp.data.User;
import com.example.lianximvp.data.HttpResult;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginRePository implements LoginContract.ILoginPsdModel{


    @Override
    public void register(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack) {
        JDDataService.getApiService().login(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<User>>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<User> userHttpResult) {
                        if(userHttpResult.code == 1 ){
                                if (userHttpResult.data!=null)
                                callBack.onSuccessful(userHttpResult.data);

                        }else{
                            callBack.onFail(userHttpResult.message);
                            Log.i("TAG", "onNext: "+"2222222222222222222");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
