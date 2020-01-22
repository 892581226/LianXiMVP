package com.example.mylibrarymvp.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.mylibrarymvp.R;

public class EditTogglePasswordButton extends AppCompatImageView {//控制密码的显示与隐藏
    private EditText mEditText;
    private boolean isShow;

    public EditTogglePasswordButton(Context context) {
        this(context,null);
    }

    public EditTogglePasswordButton(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public EditTogglePasswordButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickListener();
    }

    private void setClickListener() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText!=null){
                    toggle();
                }
            }
        });
    }


    public void setmEditText(EditText editText){
        mEditText=editText;
        if (TextUtils.isEmpty(editText.getText().toString().trim())){
            setVisibility(GONE);
        }else {
            setVisibility(VISIBLE);
        }
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(editText.getText().toString().trim())){
                    setVisibility(GONE);
                }else {
                    setVisibility(VISIBLE);
                }
            }
        });
    }

    private void toggle() {
        if (isShow){
            mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isShow=false;
            setImageResource(R.drawable.ic_mvp_eyes_open);
        }else {
            mEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isShow=true;
            setImageResource(R.drawable.ic_mvp_eyes_close);
        }

    }


}
