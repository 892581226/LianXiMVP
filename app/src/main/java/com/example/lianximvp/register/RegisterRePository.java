package com.example.lianximvp.register;

import com.example.lianximvp.AppConstant;
import com.example.lianximvp.JDDataService;
import com.example.lianximvp.data.HttpResult;
import com.example.lianximvp.data.User;
import com.example.mylibrarymvp.Base.IBaseCallBack;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterRePository implements com.example.lianximvp.register.RegisterContract.registerModel {
    @Override
    public void getSmCall(Map<String, String> params, IBaseCallBack<String> callBack) {
        JDDataService.getApiService().getVerificationCode(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        if(stringHttpResult.code == 1){
                            callBack.onSuccessful(stringHttpResult.data);
                        }else{
                            callBack.onFail(stringHttpResult.message);
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

    @Override
    public void verifySmCall(Map<String, String> params, IBaseCallBack<String> callBack) {
        JDDataService.getApiService().getCheckVerificationCode(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        if(stringHttpResult.code == 1){
                            callBack.onSuccessful(stringHttpResult.data);
                        }else{
                            callBack.onFail(stringHttpResult.message);
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

    @Override
    public void verifyCall(Map<String, String> params, IBaseCallBack<User> callBack) {
        JDDataService.getApiService().loginCall(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<User> stringHttpResult) {
                        if(stringHttpResult.code == 1){
                            callBack.onSuccessful(stringHttpResult.data);
                        }else{
                            callBack.onFail(stringHttpResult.message);
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
