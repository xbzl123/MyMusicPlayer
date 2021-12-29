package com.example.mymusicplayer.ui.main

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mymusicplayer.BaseFragment
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.MainFragmentBinding
import com.example.mymusicplayer.ui.music.MusicFragment
import java.util.*

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

    override fun onResume() {
        super.onResume()
        mDataBinding.radarview.insertData(intArrayOf(18,2,20,16,5,13))
        val config: Configuration = getResources().getConfiguration()

        val countryCode: Int = config.mcc
        Log.e("Exception","countryCode :"+countryCode)
    }
}