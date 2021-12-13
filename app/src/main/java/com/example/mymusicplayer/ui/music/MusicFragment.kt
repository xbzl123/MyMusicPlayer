package com.example.mymusicplayer.ui.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.blankj.utilcode.util.Utils
import com.example.mymusicplayer.BaseFragment
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.MusicFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//音乐页面
class MusicFragment : BaseFragment<MusicFragmentBinding,MusicViewModel>() {

    companion object {
        fun newInstance() = MusicFragment()
    }

    override fun onResume() {
        super.onResume()
        mDataBinding.message.setOnClickListener {
//            mViewModel.changeData("data")
        }
        GlobalScope.launch (Dispatchers.Main){
            mViewModel.stateFlow.collect {
                mDataBinding.message.text = it
            }
        }
    }
    override fun getContentViewId(): Int {
        return R.layout.music_fragment
    }

    override fun bindViewModel() {
        mDataBinding.viewmodel = mViewModel
    }

    override fun getViewModelClass(): Class<MusicViewModel> {
        return MusicViewModel::class.java
    }

}