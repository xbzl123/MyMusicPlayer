package com.example.mymusicplayer

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.Utils
import com.bugrui.buslib.LiveDataBus
import com.example.mymusicplayer.databinding.MainActivityBinding
import com.example.mymusicplayer.ui.main.MainFragment
import com.example.mymusicplayer.ui.music.MusicFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding:MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this,R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        Utils.init(this.application)
        handlePermissions()

        listenFragmentChange()
    }

    private fun listenFragmentChange() {
        LiveDataBus.with("changeFragment").observe(this, Observer {
            if (it?.equals("music") == true){
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MusicFragment.newInstance())
                    .commitNow()
            }
        })
    }

    private fun handlePermissions() {
        val registerForActivityResult =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                if(!it[Manifest.permission.ACCESS_FINE_LOCATION]!!
                    || !it[Manifest.permission.READ_EXTERNAL_STORAGE]!!
                    || !it[Manifest.permission.WRITE_EXTERNAL_STORAGE]!!){
                    finish()
                }
            }
        registerForActivityResult.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION
            ,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }
}