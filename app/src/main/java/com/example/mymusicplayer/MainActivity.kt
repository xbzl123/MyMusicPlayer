package com.example.mymusicplayer

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.Utils
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
    }

    private fun handlePermissions() {
        val registerForActivityResult =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                if(!it[Manifest.permission.ACCESS_FINE_LOCATION]!!){
                    finish()
                }
            }
        registerForActivityResult.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    }
}