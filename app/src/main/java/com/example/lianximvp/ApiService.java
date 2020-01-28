package com.example.lianximvp;

import com.example.lianximvp.data.HttpResult;
import com.example.lianximvp.data.User;
import com.example.lianximvp.data.home.UserColumnList;
import com.example.lianximvp.data.home.UserColumnListLan;
import com.example.lianximvp.data.video.UserVideoList;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import static com.example.lianximvp.AppConstant.Url.CALL_LOGIN_URL;
import static com.example.lianximvp.AppConstant.Url.CHECK_VERIFICATION_CODE;
import static com.example.lianximvp.AppConstant.Url.GET_COLUMN_LIST;
import static com.example.lianximvp.AppConstant.Url.GET_COLUMN_LIST_LAN;
import static com.example.lianximvp.AppConstant.Url.GET_SHIPIN_LIST;
import static com.example.lianximvp.AppConstant.Url.GET_VERIFICATION_CODE;
import static com.example.lianximvp.AppConstant.Url.GET_ZHUANTI_LIST;
import static com.example.lianximvp.AppConstant.Url.PASSWORD_LOGIN_URL;
import static com.example.lianximvp.AppConstant.Url.USER_REGISTER;

public interface ApiService {
    @FormUrlEncoded
    @POST(PASSWORD_LOGIN_URL)
    Observable<HttpResult<User>> login(@FieldMap Map<String,String> params);

    @POST(CALL_LOGIN_URL)
    @FormUrlEncoded
    Observable<HttpResult<User>> loginCall(@FieldMap Map<String,String> params);

    @POST(GET_VERIFICATION_CODE)
    @FormUrlEncoded
    Observable<HttpResult<String>> getVerificationCode(@FieldMap Map<String,String> params);


    @POST(CHECK_VERIFICATION_CODE)
    @FormUrlEncoded
    Observable<HttpResult<String>> getCheckVerificationCode(@FieldMap Map<String,String> params);

    @POST(USER_REGISTER)
    @FormUrlEncoded
    Observable<HttpResult<User>> register(@FieldMap Map<String,String> params);

    @GET(GET_COLUMN_LIST)
    Observable<HttpResult<UserColumnList>> getColumnList(@QueryMap Map<String ,String> params);

    @GET(GET_COLUMN_LIST_LAN)
    Observable<HttpResult<UserColumnListLan>> getColumnListLan(@QueryMap Map<String ,String> params);

    @GET(GET_ZHUANTI_LIST)
    Observable<HttpResult<UserColumnListLan>> getZhuanTiListLan(@QueryMap Map<String ,String> params);

    @GET(GET_SHIPIN_LIST)
    Observable<HttpResult<UserVideoList>> getShiPinListLan(@QueryMap Map<String ,String> params);
}
