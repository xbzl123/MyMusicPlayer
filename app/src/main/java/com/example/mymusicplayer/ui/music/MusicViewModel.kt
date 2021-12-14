package com.example.mymusicplayer.ui.music

import android.util.Log
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.Utils
import com.example.mymusicplayer.viewmodel.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class MusicInfo(var id:Long, var songName:String, var songer:String, var duration:Int, var size:Long,var path:String)

class MusicViewModel : LifecycleViewModel() {

    var nameObserver: ObservableField<String> = ObservableField("test")
    var isPlay:ObservableBoolean = ObservableBoolean(true)
    private val temp = MutableStateFlow("test over!")
    val stateFlow:StateFlow<String> = temp
    fun changeData(content:String){
//        temp.value = content
    }
}