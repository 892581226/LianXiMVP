package com.example.lianximvp;

import com.example.lianximvp.data.HttpResult;
import com.example.mylibrarymvp.Base.IBaseCallBack;
import com.example.mylibrarymvp.exception.ResultException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/*
 * created by Cherry on 2020-01-03
 **/
public class BaseRepository {


    protected <D> void observer(Observable<HttpResult<D>> observable, Function<HttpResult<D>, ObservableSource<D>> function, IBaseCallBack<D> callBack) {
        observable.flatMap(function).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<D>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(D d) {
                        callBack.onSuccessful(d);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof ResultException){
                            callBack.onFail(String.valueOf((ResultException) e));
                        }else{
                            callBack.onFail(String.valueOf(new ResultException(e)));
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    protected <D> Observable<D>  getConvertObservable(HttpResult<D> httpResult){
        if(httpResult.code == 1){
            if(httpResult.data != null){
                return Observable.just(httpResult.data);
            }else{
                return Observable.error(new ResultException(ResultException.SERVER_ERROR));
            }
        }else{
            return Observable.error(new ResultException(httpResult.message));
        }
    }
}
