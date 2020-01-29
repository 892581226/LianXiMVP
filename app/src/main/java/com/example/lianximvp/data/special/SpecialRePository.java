package com.example.lianximvp.data.special;

import com.example.lianximvp.JDDataService;
import com.example.lianximvp.data.HttpResult;
import com.example.mylibrarymvp.Base.IBaseCallBack;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpecialRePository implements ISpecialContract.ISpecialModel {


    @Override
    public void getListLan(Map<String, String> params, IBaseCallBack<UserSpecialList> callBack) {
        JDDataService.getApiService().getZhuanTiListLan(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserSpecialList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<UserSpecialList> userHttpResult) {
                        callBack.onSuccessful(userHttpResult.data);
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
