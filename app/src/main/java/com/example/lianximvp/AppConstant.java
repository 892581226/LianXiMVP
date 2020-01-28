package com.example.lianximvp;

public interface AppConstant {
    String BASE_URL = "https://www.seetao.com";

    String PLATFORM_ANDROID = "android";
    String LANG = "zh";



    public interface Url{
        String CALL_LOGIN_URL="/api/user/smslogin";
        String PASSWORD_LOGIN_URL="/api/user/login";
        String GET_VERIFICATION_CODE = "/api/sms/sendsms";
        String USER_REGISTER = "/api/user/register";
        String CHECK_VERIFICATION_CODE = "/api/sms/checksmscode";
        String GET_COLUMN_LIST="/api/column/columnlist";
        String GET_COLUMN_LIST_LAN= "/app/v_1_1/article/recommendlist ";
        String GET_SHIPIN_LIST="/app/v_1_1/article/videolist";
        String GET_ZHUANTI_LIST="/app/v_1_1/article/speciallist";

    }


    public interface RequestKey{

        String TIMESTAMP = "timestamp";
        String NONCE = "nonce";
        String LANG = "lang";
        String FROM = "from";
        String SIGNATURE = "signature";

        String AUTH_REGISTER_MOBILE = "mobile";
        String AUTH_REGISTER_TYPE = "type";
        String VERIFICATION_CODE = "sms_code";
        String AUTH_REGISTER_PASSWORD = "password";
        String AUTH_REGISTER_CONFIRM_PASSWORD = "affirm_password";
        String USERNAME_LOGIN="username";
    }
}
