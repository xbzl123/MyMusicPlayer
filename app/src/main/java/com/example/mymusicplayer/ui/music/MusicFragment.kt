package com.example.mymusicplayer.ui.music

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
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

    var musicInfoList = arrayListOf<MusicInfo>()
    override fun onResume() {
        super.onResume()
        searchMp3File()

        val function: (View) -> Unit = {
            if(mViewModel.isPlay.get()){
            playMusic(musicInfoList[0].path)
            }
//            mViewModel.changeData("data")
        }
        mDataBinding.message.setOnClickListener(function)
        GlobalScope.launch (Dispatchers.Main){
            mViewModel.stateFlow.collect {
                mDataBinding.message.text = it
            }
        }
    }

    private fun searchMp3File() {
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
            Log.d("1111", "ssssize = "+musicInfoList[0].path)
        }

    }

    private fun playMusic(path: String) {
        val mediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(path)
            //异步监听加载
            prepareAsync()
        }

        mediaPlayer.setOnPreparedListener({
            it.start()
            mViewModel.isPlay.set(true)
        })
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