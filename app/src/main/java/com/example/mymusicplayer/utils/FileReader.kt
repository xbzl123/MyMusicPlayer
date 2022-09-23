package com.example.mymusicplayer.utils

import android.content.Context
import android.util.Log
import com.example.mymusicplayer.data.CityInfo
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * FileReader
 * @author longyanghe
 * @date 2022-09-23
 */
object FileReader {
    fun readTxt(context: Context):List<CityInfo>{
        var result = arrayListOf<CityInfo>()
        try {
            val inputStreamReader = BufferedReader(InputStreamReader(context.assets.open("json.txt")))

            while (true){
                val readLine = inputStreamReader.readLine()
                if (readLine.isNullOrBlank()){
                    break
                }
                val content = "{"+readLine.replace(",","")
                val jsonObject = JSONObject(content)
                val name = jsonObject.names()[0].toString()
                result.add(CityInfo(name,jsonObject[name].toString()))
                Log.d("getAllDevices","stringBuffer = "+content)

            }
        }catch (e:IOException){
            Log.d("getAllDevices","e = "+e.message)
        }
        return result
    }
}