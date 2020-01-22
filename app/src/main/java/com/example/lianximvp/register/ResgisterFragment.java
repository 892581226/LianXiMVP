package com.example.lianximvp.register;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lianximvp.SetPasswordFragment;
import com.example.lianximvp.R;
import com.example.lianximvp.data.User;
import com.example.lianximvp.data.login.PasswordLoginFragment;
import com.example.lianximvp.data.login.VerificationLoginFragment;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.example.mylibrarymvp.Utils.SystemFacade;
import com.example.mylibrarymvp.manager.MvpFragmentManger;
import com.example.mylibrarymvp.widget.CountDownView;
import com.example.mylibrarymvp.widget.EditCleanButton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResgisterFragment extends BaseMvpFragment<RegisterContract.registerPresenter> implements RegisterContract.registerView , View.OnClickListener {
    private EditText mEdtPhoneNum;
    private EditCleanButton mBtnCleanPhoneNum;

    private EditText mEdtVerification;
    private Button mBtnNext;

    private CountDownView mTvGetCode;

    private TextView mTvVCLogin;
    private TextView mTvPsdLogin;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public boolean isAddBackStack() {
        return true;
    }

    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        mBtnCleanPhoneNum = view.findViewById(R.id.auth_register_iv_clean);
        mEdtPhoneNum   = view.findViewById(R.id.auth_register_edt_phone_num);
        mBtnCleanPhoneNum.bindEditText(mEdtPhoneNum);

        mEdtVerification = view.findViewById(R.id.auth_register_edt_verification_code);
        mBtnNext = view.findViewById(R.id.auth_register_btn_next_step);

        mTvGetCode = view.findViewById(R.id.auth_register_tv_get_verification_code);
        mEdtVerification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBtnNext.setEnabled(!TextUtils.isEmpty(mEdtVerification.getText().toString().trim()));

            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        mTvVCLogin = view.findViewById(R.id.auth_register_tv_code_login);
        mTvPsdLogin = view.findViewById(R.id.auth_register_tv_psd_login);
        mTvGetCode.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mTvVCLogin.setOnClickListener(this);
        mTvPsdLogin.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auth_register_tv_get_verification_code: {
                String phoneNum = mEdtPhoneNum.getText().toString().trim();

                if(SystemFacade.isValidPhoneNumber(phoneNum)){
                    mPresent.getSmCall(phoneNum,"1");
                }else{
                    showToast(R.string.error_invalid_phone_num);
                }

                break;
            }
            case R.id.auth_register_btn_next_step: {
                String phoneNum = mEdtPhoneNum.getText().toString().trim();
                if(!SystemFacade.isValidPhoneNumber(phoneNum)){
                    showToast(R.string.error_invalid_phone_num);
                    return;
                }

                String code = mEdtVerification.getText().toString().trim();
                if(SystemFacade.isValidSmsCodeNumber(code)){
                    mPresent.verifySmCall(phoneNum,code);
                }else {
                    showToast(R.string.error_invalid_sms_code_num);
                }

                break;
            }
            case R.id.auth_register_tv_code_login: {
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), VerificationLoginFragment.class, this, android.R.id.content,null,null);
                break;
            }
            case R.id.auth_register_tv_psd_login: {
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), PasswordLoginFragment.class, this, android.R.id.content,null,null);
                break;
            }

        }
    }




    @Override
    public RegisterContract.registerPresenter createPresenter() {
        return new RegisterPresenter();
    }





    @Override
    public void onSmsCodeResult(String msg, boolean success) {
        if(success){
            showToast("获取验证码成功");
        }else{
            showToast("获取验证码失败" + msg);
        }
    }

    @Override
    public void onVerifySmsCodeResult(String msg, boolean success) {
        if(success){
            showToast("验证码成功");
            Bundle bundle = new Bundle();
            bundle.putString("phone",mEdtPhoneNum.getText().toString().trim());
            MvpFragmentManger.addOrShowFragment(getFragmentManager(), SetPasswordFragment.class,this,android.R.id.content,null,bundle);
        }else{
            showToast("验证码失败" + msg);
        }

    }

    @Override
    public void onVerifyCodeResult(User data, boolean successful,String msg) {

    }

}
