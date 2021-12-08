package com.example.mymusicplayer.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymusicplayer.BaseFragment
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.MainFragmentBinding

//主页
class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>() {


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun getContentViewId(): Int {
        return R.layout.main_fragment
    }

    override fun bindViewModel() {
        mDataBinding.viewmodel = mViewModel
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}