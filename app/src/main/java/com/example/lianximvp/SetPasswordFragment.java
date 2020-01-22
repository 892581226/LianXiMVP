package com.example.lianximvp;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lianximvp.data.User;
import com.example.lianximvp.data.login.PasswordLoginFragment;
import com.example.lianximvp.register.RegisterContract;
import com.example.lianximvp.register.RegisterSetPresenter;
import com.example.lianximvp.data.login.VerificationLoginFragment;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.example.mylibrarymvp.Utils.Logger;
import com.example.mylibrarymvp.manager.MvpFragmentManger;
import com.example.mylibrarymvp.widget.EditCleanButton;
import com.example.mylibrarymvp.widget.EditTogglePasswordButton;
import com.umeng.weixin.umengwx.WeChat;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SetPasswordFragment extends BaseMvpFragment<RegisterContract.IRegisterSetPresenter> implements RegisterContract.IRegisterSetView, View.OnClickListener {

    private static final String TAG = "SetPsdFragment";
    private EditTogglePasswordButton open;
    private EditText et;
    private EditCleanButton clean;
    private Button mBtNext;
    private TextView mTvUserAgreement;
    private TextView mCodeLogin;
    private TextView mPsdLogin;
    private EditText mEdtConfirmPassword;

    @Override
    public boolean isAddBackStack() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setpassword;
    }

    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        open = getView().findViewById(R.id.auth_register_set_iv_toggle_confirm_psd);
        et = getView().findViewById(R.id.auth_register_edt_pwd);
        clean = getView().findViewById(R.id.auth_register_iv_clean);
        mBtNext = getView().findViewById(R.id.auth_register_btn_next_step8);
        mTvUserAgreement = getView().findViewById(R.id.auth_register_set_tv_user_agreement);
        mCodeLogin = getView().findViewById(R.id.auth_register_tv_code_login);
        mPsdLogin = getView().findViewById(R.id.auth_register_tv_psd_login);
        mEdtConfirmPassword = getView().findViewById(R.id.auth_register_edt_wpsd);
        mCodeLogin.setOnClickListener(this);
        mBtNext.setOnClickListener(this);
        mPsdLogin.setOnClickListener(this);
        open.setmEditText(et);
        clean.bindEditText(et);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBtNext.setEnabled(!TextUtils.isEmpty(et.getText().toString().trim()));

            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        setUserAgreement();
    }

    private void setUserAgreement() {
        String content = getString(R.string.text_auth_register_set_user_agreement);

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(content);

        stringBuilder.setSpan(new ClickableSpan(){

            @Override
            public void onClick(@NonNull View widget) {
                showToast("用户协议");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);// 设置可点击文字的颜色
                ds.setUnderlineText(false);// 取消下划线

            }
        }, 9, 13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        stringBuilder.setSpan(new ClickableSpan(){
            @Override
            public void onClick(@NonNull View widget) {
                showToast("隐私声明");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);// 设置可点击文字的颜色
                ds.setUnderlineText(false); // 取消下划线

            }
        }, 14, 18, Spanned.SPAN_INCLUSIVE_INCLUSIVE);



        mTvUserAgreement.setText(stringBuilder);
        // 不设置这个，将不可点击
        mTvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auth_register_btn_next_step8:
                Bundle bundle = getArguments();
                String phone=null;
                if (bundle!=null){
                    phone = bundle.getString("phone");
                }
                if (TextUtils.isEmpty(phone)){
                    throw new NullPointerException("跳转到设置页面需要传入手机号");
                }
                mPresent.register(phone , et.getText().toString().trim(),mEdtConfirmPassword.getText().toString().trim());

                break;
            case R.id.auth_register_tv_code_login:
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), VerificationLoginFragment.class, this, android.R.id.content,null,null);
                break;
            case R.id.auth_register_tv_psd_login:
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), PasswordLoginFragment.class, this, android.R.id.content,null,null);
                break;

        }
    }

    @Override
    public RegisterContract.IRegisterSetPresenter createPresenter() {
        return new RegisterSetPresenter();
    }

    @Override
    public void onRegisterResult(User user, String msg) {
        if(user != null){
            Log.i(TAG, "onRegisterResult: "+"成功");
            Logger.d("%s user = %s",TAG,user);
            MvpFragmentManger.addOrShowFragment(getFragmentManager(), PasswordLoginFragment.class, this, android.R.id.content,null,null);
        }else{
            Logger.d("%s error = %s",msg);
        }
    }

}
