package com.example.lianximvp.data.home;

import com.example.lianximvp.JDDataService;
import com.example.lianximvp.ParamsUtils;
import com.example.lianximvp.data.HttpResult;
import com.example.lianximvp.data.User;
import com.example.mylibrarymvp.Base.IBaseCallBack;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeRePository implements IHomeContract.IHomePsdModel{


    @Override
    public void register(Map<String, String> params, IBaseCallBack<UserColumnList> callBack) {
        JDDataService.getApiService().getColumnList(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserColumnList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<UserColumnList> userHttpResult) {
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
