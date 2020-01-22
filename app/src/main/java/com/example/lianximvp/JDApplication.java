package com.example.lianximvp;

import android.app.Application;

import com.example.lianximvp.converter.MyGsonConverterFactory;
import com.example.mylibrarymvp.oknet.DataService;

public class JDApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataService.init(ApiService.class,AppConstant.BASE_URL, MyGsonConverterFactory.create());
    }
}
