package com.example.mymusicplayer.utils

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.Socket
import java.net.URL

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * NetworkUtil
 * @author longyanghe
 * @date 2022-05-30
 */
object NetworkUtil {
     fun request(url:String):String{
//            val url = "https://movie.douban.com/"
         val parse = URL(url)
         val httpURLConnection = parse.openConnection() as HttpURLConnection
         val inputStreamReader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
         return inputStreamReader.readText()
    }
}