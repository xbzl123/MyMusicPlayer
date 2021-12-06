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

abstract class BaseFragment<DB : ViewDataBinding,VM : LifecycleViewModel>: Fragment() {
    lateinit var vm:VM
    lateinit var db:DB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("","11=============test=")

        db = DataBindingUtil.inflate(inflater,getContentViewId(), container, false)
        vm = ViewModelProvider(this).get(getViewModelClass())
        Log.d("","22=============test=")

        bindViewModel()
        return db.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("","33=============test=")

    }
    abstract fun getContentViewId(): Int

    abstract fun bindViewModel()

    abstract fun getViewModelClass():Class<VM>
}