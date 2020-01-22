package com.example.lianximvp.data.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lianximvp.R;
import com.example.lianximvp.data.User;
import com.example.lianximvp.data.home.HomeActivity;
import com.example.lianximvp.register.RegisterPresenter;
import com.example.lianximvp.data.home.HomeFragment;
import com.example.lianximvp.register.RegisterContract;
import com.example.lianximvp.register.ResgisterFragment;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.example.mylibrarymvp.Utils.SystemFacade;
import com.example.mylibrarymvp.manager.MvpFragmentManger;
import com.example.mylibrarymvp.widget.CountDownView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VerificationLoginFragment extends BaseMvpFragment<RegisterContract.registerPresenter> implements View.OnClickListener,RegisterContract.registerView {
    private EditText mAuthVerLoginEdtPhoneNum;
    private EditText mAuthVerLoginEdtVerificationCode;
    private CountDownView mAuthVerLoginTvGetVerificationCode;
    private Button mAuthVerLoginBtnNextStep;
    private TextView mAuthVerLoginTvCodeLogin;
    private TextView mAuthVerLoginTvPsdLogin;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_verificationlogin;

    }

    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mAuthVerLoginEdtPhoneNum = getView().findViewById(R.id.auth_ver_login_edt_phone_num);
        mAuthVerLoginEdtVerificationCode = getView().findViewById(R.id.auth_ver_login_edt_verification_code2);
        mAuthVerLoginTvGetVerificationCode = getView().findViewById(R.id.auth_ver_login_tv_get_verification_code);
        mAuthVerLoginBtnNextStep = getView().findViewById(R.id.auth_ver_login_btn_next_step2);
        mAuthVerLoginTvCodeLogin = getView().findViewById(R.id.auth_ver_login_tv_code_login);
        mAuthVerLoginTvPsdLogin = getView().findViewById(R.id.auth_ver_login_tv_psd_login);
        mAuthVerLoginTvGetVerificationCode.setOnClickListener(this);
        mAuthVerLoginTvCodeLogin.setOnClickListener(this);
        mAuthVerLoginTvPsdLogin.setOnClickListener(this);
        mAuthVerLoginBtnNextStep.setOnClickListener(this);
        mAuthVerLoginEdtVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAuthVerLoginBtnNextStep.setEnabled(!TextUtils.isEmpty(mAuthVerLoginEdtVerificationCode.getText().toString().trim()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public RegisterContract.registerPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auth_ver_login_tv_get_verification_code:
                String phoneNum = mAuthVerLoginEdtPhoneNum.getText().toString().trim();

                if(SystemFacade.isValidPhoneNumber(phoneNum)){
                    mPresent.getSmCall(phoneNum,"4");
                }else{
                    showToast(R.string.error_invalid_phone_num);
                }
                break;
            case R.id.auth_ver_login_btn_next_step2:
                String phoneNum1 = mAuthVerLoginEdtPhoneNum.getText().toString().trim();
                if(!SystemFacade.isValidPhoneNumber(phoneNum1)){
                    showToast(R.string.error_invalid_phone_num);
                    return;
                }

                String code = mAuthVerLoginEdtVerificationCode.getText().toString().trim();
                if(SystemFacade.isValidSmsCodeNumber(code)){
                    mPresent.verifyCall(phoneNum1,code);
                }else{
                    showToast(R.string.error_invalid_sms_code_num);
                }
                break;
            case R.id.auth_ver_login_tv_code_login:
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), PasswordLoginFragment.class, this, android.R.id.content,null,null);
                break;
            case R.id.auth_ver_login_tv_psd_login:
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), ResgisterFragment.class, this, android.R.id.content,null,null);
                break;
        }
    }

    @Override
    public void onSmsCodeResult(String msg, boolean successful) {
        if(successful){
            showToast("获取验证码成功");
        }else{
            showToast("获取验证码失败" + msg);
        }
    }

    @Override
    public void onVerifySmsCodeResult(String msg, boolean successful) {

    }

    @Override
    public void onVerifyCodeResult(User data, boolean successful ,String msg) {
        if(successful){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);        }else{
            showToast("验证码失败" + msg);
        }
    }

}
