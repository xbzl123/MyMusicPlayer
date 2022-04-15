package com.example.mymusicplayer

import android.Manifest
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.Utils
import com.bugrui.buslib.LiveDataBus
import com.example.mymusicplayer.databinding.MainActivityBinding
import com.example.mymusicplayer.ui.main.MainFragment
import com.example.mymusicplayer.ui.music.MusicFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

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
            }else{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
            }
        })
    }

    private fun handlePermissions() {
        val registerForActivityResult =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                if(!it[Manifest.permission.ACCESS_FINE_LOCATION]!!
                    || !it[Manifest.permission.ACCESS_COARSE_LOCATION]!!
                    || !it[Manifest.permission.READ_EXTERNAL_STORAGE]!!
                    || !it[Manifest.permission.WRITE_EXTERNAL_STORAGE]!!){
                    finish()
                }
            }
        registerForActivityResult.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION
            ,Manifest.permission.ACCESS_COARSE_LOCATION
            ,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }

    override fun onResume() {
        super.onResume()

    }
}