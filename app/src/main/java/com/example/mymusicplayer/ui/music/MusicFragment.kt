package com.example.mymusicplayer.ui.music

import com.example.mymusicplayer.BaseFragment
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databing.MusicBinding

//音乐页面
class MusicFragment : BaseFragment<MusicBinding,MusicViewModel>() {

    companion object {
        fun newInstance() = MusicFragment()
    }

    override fun getContentViewId(): Int {
        return R.layout.music_fragment
    }

    override fun bindViewModel() {

    }

    override fun getViewModelClass(): Class<MusicViewModel> {
        return MusicViewModel::class.java
    }


}