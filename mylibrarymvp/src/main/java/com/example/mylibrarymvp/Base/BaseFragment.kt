package com.example.mylibrarymvp.Base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.IdRes

import androidx.annotation.StringRes
import androidx.appcompat.widget.ContentFrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.mylibrarymvp.R
import com.example.mylibrarymvp.widget.LoadingView
import com.trello.rxlifecycle2.components.support.RxFragment

abstract class BaseFragment : RxFragment(),BaseLoading {
    override var mLoadingView: LoadingView?=null
    private lateinit var mHostActivity:Activity
    abstract val layoutId: Int
    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        mHostActivity=activity as Activity
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        doOnCreate(inflater,container,savedInstanceState)
        val view:View
        val  rootView =inflater.inflate(layoutId,container,false)
        val parentName=rootView.javaClass.simpleName
        if (parentName==FrameLayout::class.java.simpleName||
                parentName==RelativeLayout::class.java.simpleName||
                parentName==ConstraintLayout::class.java.simpleName||
                parentName==ContentFrameLayout::class.java.simpleName){
            view=rootView
        }else{
            val  frameLayout=FrameLayout(context!!)
            frameLayout.layoutParams=rootView.layoutParams
            frameLayout.addView(rootView)
            view=frameLayout
        }
        if (view.id==View.NO_ID){
            view.id=0x100000
        }
        return view

    }

    protected fun back(){
        activity?.onBackPressed()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view,savedInstanceState)
        loadData()
    }

    protected open fun loadData() {
    }

    protected open fun initView(view: View, savedInstanceState: Bundle?) {
    }

    protected open fun doOnCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {

    }


    override fun <T : View> getViewById(id: Int): T? {
        var  v:T ?=view!!.findViewById(id)
        if (v==null){
            v=mHostActivity.findViewById<T>(id);
        }
        return v
    }
    @JvmOverloads
    protected fun <V : View>bindView(@IdRes id: Int, listener: View.OnClickListener? = null) : V{

        val view = view!!.findViewById<V>(id);

        listener?.apply {
            view.setOnClickListener(this);
        }
        return view

    }

    val action: Action
        get() = Action.Hied
    open val isAddBackStack: Boolean
        get() = true

 /*   val animLeftEnter: Int
        get() = R.anim.anim_left_enter
    val animLeftOut: Int
        get() = R.anim.anim_left_out
    val animRightEnter: Int
        get() = R.anim.anim_right_enter
    val animRightOut: Int
        get() = R.anim.anim_right_out*/
    open fun getEnter() = R.anim.anim_right_enter
    open fun getExit() = R.anim.anim_left_out
    open fun popExit() = R.anim.anim_right_out
    open fun popEnter() = R.anim.anim_left_enter
   protected fun showToast(@StringRes id: Int) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
    }

   protected fun showToast(content: String) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

    enum class Action {
        Hied, Remove, NONE, Detach
    }

}
