package com.example.mymusicplayer.http

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.HTTP
import retrofit2.http.Path

interface WeatherApi {
//    @GET("aomen.xml")
    @HTTP(method = "GET", path = "{city}.xml", hasBody = false)
    fun getCtiyWeather(@Path("city") city:String):Call<ResponseBody>
}