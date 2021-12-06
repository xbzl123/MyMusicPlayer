package com.example.mymusicplayer.ui.music

import android.util.Log
import android.widget.Toast
import com.blankj.utilcode.util.Utils
import com.example.mymusicplayer.viewmodel.LifecycleViewModel

class MusicViewModel : LifecycleViewModel() {
    // TODO: Implement the ViewModel

    fun test(){
        Toast.makeText(Utils.getApp(),"test",Toast.LENGTH_LONG).show()
        Log.d("","=============test=")
    }
}