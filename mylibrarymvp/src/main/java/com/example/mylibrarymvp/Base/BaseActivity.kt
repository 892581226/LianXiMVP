package com.example.mylibrarymvp.Base

import android.view.View
import android.widget.Toast

import androidx.annotation.StringRes
import com.example.mylibrarymvp.widget.LoadingView

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

open class BaseActivity : RxAppCompatActivity(), BaseLoading {
    override var mLoadingView: LoadingView?=null

    override fun <T : View> getViewById(id: Int): T? {
        return findViewById<T>(id)
    }

    fun showToast(@StringRes id: Int) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }

    fun showToast(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }
}
