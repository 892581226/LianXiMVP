package com.example.lianximvp.data.forgetPassword;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.lianximvp.R;
import com.example.lianximvp.SetPasswordFragment;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;
import com.example.mylibrarymvp.manager.MvpFragmentManger;
import com.example.mylibrarymvp.widget.CountDownView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordCallFragment extends BaseMvpFragment implements View.OnClickListener {


    private EditText mAuthForgetEdtPhonePassword;
    private EditText mAuthVerForgetEdtVerificationCode2;
    private CountDownView mAuthVerForgetTvGetVerificationCode;
    private Button next;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forget_password_call;
    }

    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mAuthForgetEdtPhonePassword = getView().findViewById(R.id.auth_forget_edt_phone_password);
        mAuthVerForgetEdtVerificationCode2 = getView().findViewById(R.id.auth_ver_forget_edt_verification_code2);
        mAuthVerForgetTvGetVerificationCode = getView().findViewById(R.id.auth_ver_forget_tv_get_verification_code);
        next = getView().findViewById(R.id.auth_forget_btn_next_step);
        mAuthVerForgetTvGetVerificationCode.setOnClickListener(this);
        next.setOnClickListener(this);
        mAuthVerForgetEdtVerificationCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                next.setEnabled(!TextUtils.isEmpty(mAuthVerForgetEdtVerificationCode2.getText().toString().trim()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public IBaseMvpPresenter createPresenter() {
        return null;
    }                                        

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auth_ver_forget_tv_get_verification_code:

                break;
            case R.id.auth_forget_btn_next_step:
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), OverSetPasswordFragment.class,this,android.R.id.content,null,null);
                break;

        }
    }
}
