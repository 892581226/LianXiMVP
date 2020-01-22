package com.example.mylibrarymvp.manager;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.example.mylibrarymvp.Base.BaseFragment;

public class MvpFragmentManger {

    public static BaseFragment addOrShowFragment(FragmentManager fragmentManager, Class<? extends BaseFragment> next,
                                                 BaseFragment current, int containerId, String tag, Bundle arge){
        if(TextUtils.isEmpty(tag)){
            tag= getFragmentTag(next);
        }
        BaseFragment baseFragment=null;
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        try {
            Fragment fragment= fragmentManager.findFragmentByTag(tag);
            if (fragment==null) {
                baseFragment = next.newInstance();
                baseFragment.setArguments(arge);
                fragmentTransaction.setCustomAnimations(baseFragment.getEnter(), baseFragment.getExit(), baseFragment.popEnter(), baseFragment.popExit());
                fragmentTransaction.add(containerId, baseFragment, tag);
                handLastFragment(fragmentManager, fragmentTransaction, baseFragment, current);

                if (baseFragment.isAddBackStack()) {
                    fragmentTransaction.addToBackStack(tag);
                }
                fragmentTransaction.commit();
            }else {
                baseFragment=(BaseFragment) fragment;
                int count= fragmentManager.getBackStackEntryCount();
                FragmentManager.BackStackEntry stackEntry;
                for (int i = 0; i < count; i++) {
                   stackEntry = fragmentManager.getBackStackEntryAt(i);
                   if(stackEntry.getName().equals(tag)){
                       baseFragment.setArguments(arge);
                       fragmentManager.popBackStackImmediate(tag,0);
                       return baseFragment;
                   }
                }

                if (count>0){
                    fragmentManager.popBackStackImmediate(fragmentManager.getBackStackEntryAt(0).getName(),
                            FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }else {
                    if (!baseFragment.isAdded()){
                        if(baseFragment.getLifecycle().getCurrentState() != Lifecycle.State.INITIALIZED){
                            return null;//fragment初始化，才能点击
                        }
                        fragmentTransaction.add(containerId,baseFragment,tag);
                        if (baseFragment.isAddBackStack()){
                            fragmentTransaction.addToBackStack(tag);
                        }
                    }
                    if (baseFragment.isDetached()){
                        fragmentTransaction.attach(baseFragment);
                    }else if (baseFragment.isHidden()){
                        fragmentTransaction.show(baseFragment);
                    }
                }
                handLastFragment(fragmentManager,fragmentTransaction,baseFragment,current);
                baseFragment.setArguments(arge);
                fragmentTransaction.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseFragment;
    }

    private static void handLastFragment(FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, BaseFragment baseFragment, BaseFragment current) {

        if (current==null||baseFragment.getAction()==BaseFragment.Action.NONE){
            return;
        }
        if (baseFragment.getAction()==BaseFragment.Action.Hied){
            if (!current.isHidden()){
                fragmentTransaction.hide(current);
            }
        }else if (baseFragment.getAction()==BaseFragment.Action.Remove){
            if (current.isAdded()){
                fragmentTransaction.remove(current);
            }
        }else if (baseFragment.getAction()==BaseFragment.Action.Detach){
            if (!current.isDetached()){
                fragmentTransaction.attach(current);
            }
        }

    }


    private static String getFragmentTag(Class<? extends BaseFragment> next) {
        return next.getName();
    }

}
