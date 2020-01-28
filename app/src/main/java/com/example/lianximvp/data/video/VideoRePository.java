package com.example.lianximvp.data.video;

import com.example.lianximvp.JDDataService;
import com.example.lianximvp.data.HttpResult;
import com.example.lianximvp.data.home.UserColumnList;
import com.example.lianximvp.data.home.UserColumnListLan;
import com.example.mylibrarymvp.Base.IBaseCallBack;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoRePository implements IVideoContract.IVideoModel{

    @Override
    public void getListLan(Map<String, String> params, IBaseCallBack<UserVideoList> callBack) {
        JDDataService.getApiService().getShiPinListLan(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserVideoList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<UserVideoList> userHttpResult) {
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
