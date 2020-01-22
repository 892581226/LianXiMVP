package com.example.lianximvp;

import com.example.mylibrarymvp.oknet.DataService;


/*
 * created by Cherry on 2019-12-26
 **/
public class JDDataService {

    public static ApiService getApiService(){
       return (ApiService) DataService.getApiService();
    }


}
