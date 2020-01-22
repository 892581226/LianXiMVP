package com.example.lianximvp.data.login;

import android.content.Intent;
import android.content.LocusId;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lianximvp.BaseAuthFragment;
import com.example.lianximvp.MainActivity;
import com.example.lianximvp.ModelUtils;
import com.example.lianximvp.R;
import com.example.lianximvp.data.forgetPassword.ForgetPasswordCallFragment;
import com.example.lianximvp.data.home.HomeActivity;
import com.example.lianximvp.data.home.HomeFragment;
import com.example.lianximvp.data.video.VideoFragment;
import com.example.lianximvp.register.ResgisterFragment;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.example.mylibrarymvp.Utils.SystemFacade;
import com.example.mylibrarymvp.manager.MvpFragmentManger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PasswordLoginFragment extends BaseAuthFragment<LoginContract.ILoginPsdPresenter> implements View.OnClickListener,LoginContract.ILoginPsdView {

    private EditText mAuthVerLoginEdtPhoneNum;
    private EditText mAuth_login_edt_psd_password;
    private Button mAuthVerLoginBtnNextStep;
    private TextView mAuth_login_tv_pwd_login;
    private TextView mAuth_login_tv_register_login;
    private TextView forgetPassword;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_passwrodlogin;
    }

    @Override
    public LoginContract.ILoginPsdPresenter createPresenter() {
        return new LoginPresenter();
    }
    @Override
    protected void initView(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mAuthVerLoginEdtPhoneNum = getView().findViewById(R.id.auth_login_edt_phone_password1);
        mAuth_login_edt_psd_password = getView().findViewById(R.id.auth_login_edt_psd_password1);
        mAuthVerLoginBtnNextStep = getView().findViewById(R.id.auth_login_btn_next_step33);
        mAuth_login_tv_pwd_login = getView().findViewById(R.id.auth_login_tv_raw_login);
        mAuth_login_tv_register_login= getView().findViewById(R.id.auth_login_tv_register_login33);
        forgetPassword = getView().findViewById(R.id.auth_password_login_tv_forget_password);
        forgetPassword.setOnClickListener(this);
        mAuth_login_tv_pwd_login.setOnClickListener(this);
        mAuth_login_tv_register_login.setOnClickListener(this);
        mAuthVerLoginBtnNextStep.setOnClickListener(this);
        mAuth_login_edt_psd_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAuthVerLoginBtnNextStep.setEnabled(!TextUtils.isEmpty(mAuth_login_edt_psd_password.getText().toString().trim()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        Class<ReentrantReadWriteLock.ReadLock> readLockClass = ReentrantReadWriteLock.ReadLock.class;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auth_login_btn_next_step33:
                if (ModelUtils.isEMUI() && android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                    mAuth_login_edt_psd_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    mAuth_login_edt_psd_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                String phoneNum = mAuthVerLoginEdtPhoneNum.getText().toString().trim();
                if(!SystemFacade.isValidPhoneNumber(phoneNum)){
                    showToast(R.string.error_invalid_phone_num);
                    return;
                }

                String password = mAuth_login_edt_psd_password.getText().toString().trim();
                if(!password.isEmpty()){
                    mPresent.registers(phoneNum,password);
                }else{
                    showToast(R.string.error_invalid_sms_password_num);
                }
                break;
            case R.id.auth_login_tv_register_login33:
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), ResgisterFragment.class, this, android.R.id.content,null,null);
                break;
            case R.id.auth_login_tv_raw_login:
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), VerificationLoginFragment.class, this, android.R.id.content,null,null);
                break;
            case R.id.auth_password_login_tv_forget_password:
                Bundle bundle = new Bundle();
                bundle.putString("callPhone",mAuthVerLoginEdtPhoneNum.getText().toString().trim());
                MvpFragmentManger.addOrShowFragment(getFragmentManager(), ForgetPasswordCallFragment.class,
                        this, android.R.id.content,null,bundle);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    @Override
    public void onRegisterResult(boolean success, String msg) {
        if (success){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }else {
            showToast(msg);
            Log.i("TAG", "onRegisterResult: "+msg);
        }
    }
    @Override
    public boolean isAddBackStack() {
        return false;
    }

    @Override
    public int getEnter() {
        return 0;
    }
}

//18847157594