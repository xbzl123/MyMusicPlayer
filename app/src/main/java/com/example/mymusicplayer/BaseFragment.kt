package com.example.mymusicplayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymusicplayer.viewmodel.LifecycleViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

abstract class BaseFragment<DB : ViewDataBinding,VM : LifecycleViewModel>: Fragment() {
    lateinit var mViewModel : VM
    lateinit var mDataBinding : DB
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        firebaseAnalytics = Firebase.analytics
        mDataBinding = DataBindingUtil.inflate(inflater,getContentViewId(), container, false)
        mViewModel = ViewModelProvider(this).get(getViewModelClass())
        bindViewModel()
        return mDataBinding.root
    }

    abstract fun getContentViewId(): Int

    abstract fun bindViewModel()

    abstract fun getViewModelClass():Class<VM>
}