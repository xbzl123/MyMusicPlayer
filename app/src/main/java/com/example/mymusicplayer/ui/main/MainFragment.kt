package com.example.mymusicplayer.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
//        mDataBinding.verticalbar.setOnTouchListener(object :View.OnTouchListener{
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if(event?.action == MotionEvent.ACTION_MOVE){
//                    mDataBinding.verticalbar.translationY = event?.y
//                }
//                return true
//            }
//        })
        mDataBinding.radarview.startAnimationRatotion()
    }
}