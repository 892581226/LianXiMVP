package com.example.lianximvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import com.example.lianximvp.data.login.PasswordLoginFragment;
import com.example.lianximvp.register.ResgisterFragment;
import com.example.mylibrarymvp.manager.MvpFragmentManger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MvpFragmentManger.addOrShowFragment(getSupportFragmentManager(), PasswordLoginFragment.class,null,android.R.id.content,null,null);
    }
}