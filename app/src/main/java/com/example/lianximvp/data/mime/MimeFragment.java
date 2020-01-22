package com.example.lianximvp.data.mime;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lianximvp.R;
import com.example.mylibrarymvp.Base.BaseMvpFragment;
import com.example.mylibrarymvp.Base.IBaseMvpPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MimeFragment extends BaseMvpFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mime;
    }

    @Override
    public IBaseMvpPresenter createPresenter() {
        return null;
    }
}
