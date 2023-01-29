package com.example.mymusicplayer.ui.custom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val isStart by lazy { MutableLiveData(false) }
    var isStartImp:LiveData<Boolean> = isStart
    fun setStateStates(){
        isStart.value = !isStart.value!!
    }

}
