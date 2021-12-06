package com.example.mymusicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mymusicplayer.databinding.MainActivityBinding
import com.example.mymusicplayer.ui.music.MusicFragment
import com.example.mymusicplayer.ui.news.NewsFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding:MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this,R.layout.main_activity)
//        val view = binding.container as ViewGroup
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewsFragment.newInstance())
                .commitNow()
        }
    }
}