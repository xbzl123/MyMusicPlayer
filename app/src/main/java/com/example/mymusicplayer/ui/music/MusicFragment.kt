package com.example.mymusicplayer.ui.music

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.FileUtils
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.blankj.utilcode.util.Utils
import com.example.mymusicplayer.BaseFragment
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.MusicFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//音乐页面
class MusicFragment : BaseFragment<MusicFragmentBinding,MusicViewModel>() {

    companion object {
        fun newInstance() = MusicFragment()
    }
    var musicInfoList = arrayListOf<MusicInfo>()

    override fun onResume() {
        super.onResume()
        searchMp3File()
        val bundle = Bundle()
        bundle.putString("tag","MusicFragment")
        firebaseAnalytics.logEvent("currFragment", bundle)
        val function: (View) -> Unit = {
            mViewModel.changeData("data")
        }
        mDataBinding.message.setOnClickListener(function)
        GlobalScope.launch (Dispatchers.Main){
            mViewModel.stateFlow.collect {
                mDataBinding.message.text = it
            }
        }
        //先授权app文件访问
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
            }
        }
    }

    private fun searchMp3File() {
        firebaseAnalytics.setUserProperty("searchMp3File", "music")
        val query = context?.contentResolver?.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        )
        if (query != null){
            while (query.moveToNext()){
                var musicInfo = MusicInfo(query.getLong(query.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                    ,query.getString(query.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                    ,query.getString(query.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                    ,query.getInt(query.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                    ,query.getLong(query.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))
                    ,query.getString(query.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)))
                musicInfoList.add(musicInfo)
            }
        }
        mViewModel.musicInfoList = musicInfoList
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