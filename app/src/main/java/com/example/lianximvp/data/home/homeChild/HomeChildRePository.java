package com.example.lianximvp.data.home.homeChild;

import com.example.lianximvp.JDDataService;
import com.example.lianximvp.data.HttpResult;
import com.example.lianximvp.data.home.IHomeContract;
import com.example.lianximvp.data.home.UserColumnListLan;
import com.example.mylibrarymvp.Base.IBaseCallBack;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeChildRePository implements IHomeContract.IHomeLanPsdModel {

    @Override
    public void register(Map<String, String> params, IBaseCallBack<UserColumnListLan> callBack) {
        JDDataService.getApiService().getColumnListLan(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserColumnListLan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<UserColumnListLan> userColumnListLanHttpResult) {
                            callBack.onSuccessful(userColumnListLanHttpResult.data);
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
