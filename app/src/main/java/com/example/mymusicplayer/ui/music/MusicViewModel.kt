package com.example.mymusicplayer.ui.music

import android.util.Log
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.Utils
import com.example.mymusicplayer.viewmodel.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class MusicInfo(val id:Int,val songName:String,val songer:String,val timese:String)

class MusicViewModel : LifecycleViewModel() {

    var nameObserver: ObservableField<String> = ObservableField("test")
    private val temp = MutableStateFlow("test over!")
    val stateFlow:StateFlow<String> = temp
    fun changeData(content:String){
//        temp.value = content
    }
}