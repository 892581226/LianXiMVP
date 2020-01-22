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
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverSetPasswordFragment extends BaseMvpFragment implements View.OnClickListener {


    private EditText mAuthForgetEdtOverPassword;
    private EditText mAuthVerForgetEdtOverPassword;
    private Button mAuthForgetOverBtnNextStep;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_over_set_password;

    }

    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mAuthForgetEdtOverPassword = getView().findViewById(R.id.auth_forget_edt_over_password);
        mAuthVerForgetEdtOverPassword =getView().findViewById(R.id.auth_ver_forget_edt_over_password);
        mAuthForgetOverBtnNextStep = getView().findViewById(R.id.auth_forget_over_btn_next_step);
        mAuthVerForgetEdtOverPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAuthForgetOverBtnNextStep.setEnabled(!TextUtils.isEmpty(mAuthVerForgetEdtOverPassword.getText().toString().trim()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mAuthForgetOverBtnNextStep.setOnClickListener(this);
    }

    @Override
    public IBaseMvpPresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        
    }
}
