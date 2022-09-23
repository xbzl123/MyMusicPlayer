package com.example.mymusicplayer.ui.music

import android.annotation.SuppressLint
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
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.bugrui.buslib.LiveDataBus
import com.example.mymusicplayer.viewmodel.LifecycleViewModel
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

data class MusicInfo(var id:Long, var songName:String, var songer:String, var duration:Int, var size:Long,var path:String)

@BindingAdapter(value = ["loadpic"],requireAll = false)
fun loadMusicAlbumPic(view:ImageView,path: String){
    view.setImageBitmap(BitmapFactory.decodeFile(path))
}

class MusicViewModel : LifecycleViewModel() {
    lateinit var nullPointer:String

    var nameObserver: ObservableField<String> = ObservableField("test")
    var isStop:ObservableBoolean = ObservableBoolean(true)
    var pathObserver: ObservableField<String> = ObservableField("")

    @RequiresApi(Build.VERSION_CODES.R)
    var picPath: ObservableField<String> = ObservableField(Environment.getExternalStorageDirectory().absolutePath+"/test1.jpg")

    var musicInfoList = arrayListOf<MusicInfo>()

    private val temp = MutableStateFlow("test over!")
    val stateFlow:StateFlow<String> = temp
    fun changeData(content:String){
        temp.value = content
        LiveDataBus.send("changeFragment","main")

    }
    var mediaPlayer:MediaPlayer =  MediaPlayer()
    fun playMusic(path: String){
        if (isStop.get()){
            if(!path.equals("")){
                mediaPlayer.start()
                Firebase.analytics.logEvent("playMusic"){
                    param("play","start")
                }
                isStop.set(false)
                return
            }else{
                pathObserver.set(musicInfoList[0].path)
                if(File(pathObserver.get()).exists()){
                    mediaPlayer.apply {
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        setDataSource(pathObserver.get())
                        //异步监听加载
                        prepareAsync()
                    }
                }
            }
            mediaPlayer.setOnPreparedListener{
                mediaPlayer.start()
                isStop.set(false)
            }
        }else{
            mediaPlayer.pause()
            isStop.set(true)
        }
    }
    fun stopMusic(){
        Log.e("setCrashlytics","open success")
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)

    }

    fun selectMusicList(){
        var temp = ""
        temp[1]
    }
}

