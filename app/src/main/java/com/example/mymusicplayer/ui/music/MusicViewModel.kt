package com.example.mymusicplayer.ui.music

import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.Utils
import com.example.mymusicplayer.viewmodel.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

data class MusicInfo(var id:Long, var songName:String, var songer:String, var duration:Int, var size:Long,var path:String)

@BindingAdapter(value = ["loadpic"],requireAll = false)
fun loadMusicAlbumPic(view:ImageView,path: String){
    view.setImageBitmap(BitmapFactory.decodeFile(path))
}

class MusicViewModel : LifecycleViewModel() {

    var nameObserver: ObservableField<String> = ObservableField("test")
    var isStop:ObservableBoolean = ObservableBoolean(true)
    var path = ""
    @RequiresApi(Build.VERSION_CODES.R)
    var picPath: ObservableField<String> = ObservableField(Environment.getExternalStorageDirectory().absolutePath+"/test1.jpg")

    var musicInfoList = arrayListOf<MusicInfo>()

    private val temp = MutableStateFlow("test over!")
    val stateFlow:StateFlow<String> = temp
    fun changeData(content:String){
        temp.value = content
    }
    var mediaPlayer:MediaPlayer =  MediaPlayer()
    fun playMusic(path: String){
        if (!mediaPlayer.isPlaying){
            if(!path.equals("")){
                mediaPlayer.start()
                isStop.set(false)
                return
            }
            this.path = musicInfoList.get(0).path
            mediaPlayer.apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(path)
                //异步监听加载
                prepareAsync()
            }
            mediaPlayer.setOnPreparedListener({
                mediaPlayer.start()
                isStop.set(false)
            })
        }else{
            mediaPlayer.pause()
            isStop.set(true)
        }
    }
}

